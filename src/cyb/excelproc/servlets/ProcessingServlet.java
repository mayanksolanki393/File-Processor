package cyb.excelproc.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import cyb.excelproc.IFileProcessor;
import cyb.excelproc.dao.IActionDao;
import cyb.excelproc.dao.ITransactionDao;
import cyb.excelproc.dao.impl.ActionDaoImpl;
import cyb.excelproc.dao.impl.TransactionDaoImpl;
import cyb.excelproc.entities.Action;
import cyb.excelproc.entities.File;
import cyb.excelproc.entities.Transaction;
import cyb.excelproc.entities.User;
import cyb.excelproc.filters.PreProcessingFilter;
import cyb.excelproc.entities.File.FileType;
import cyb.excelproc.impl.ExcelFileComparator;
import cyb.excelproc.impl.ExcelFileConcatinator;
import cyb.excelproc.impl.ExcelFileFilter;
import cyb.excelproc.impl.ExcelFileProccessor;


@WebServlet("/ProcessData")
public class ProcessingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    HashMap<String, IFileProcessor> excelProcMap;
    String filePath;
    private static Logger log = Logger.getLogger(ProcessingServlet.class);
    
	@Override
	public void init() throws ServletException {
		filePath = getServletContext().getRealPath("");
		excelProcMap = new HashMap<>();
		excelProcMap.put("Filter", new ExcelFileFilter());	
		excelProcMap.put("Concat", new ExcelFileConcatinator());
		excelProcMap.put("Compare", new ExcelFileComparator());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession();
			//get transaction from session
			Transaction transaction = (Transaction) session.getAttribute("transaction");
			
			System.out.println("Inside Process Servlet"+transaction);
			//get list of input streams of the file uploaded
			List<InputStream> inputStreamList = (List<InputStream>) request.getAttribute("fileStreams");
			
			IFileProcessor fp = excelProcMap.get(transaction.getAction().getActionName());
			
			//StringBuilder sb = new StringBuilder();
			if(fp!=null){
				//get List of data from streams
				List<String> dataList = fp.parseStreams(inputStreamList);
				
				//process data and generate map of <filename,data>
				Map<String,String> output = fp.processData(dataList);
				
				//write data to file and get List<File> for further processing
				List<java.io.File> outputFilelist = fp.writeToFiles(filePath,encodeTransactionId(output, transaction)) ;
				
				//set the generated outptu on request for further processing
				request.setAttribute("result",output);
				request.setAttribute("outputFiles", outputFilelist);
				
				//sending the data to next page for further processing
				RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
				rd.forward(request, response);
			}
			else{
				response.sendRedirect("processorNotFound.jsp");
			}
		}
		catch(Exception ex){
			log.error(ex);
			ex.printStackTrace();
			response.sendRedirect("index.jsp");
		}
	}

	private Map<String,String> encodeTransactionId(Map<String,String> map,Transaction trans){
		//creating new map
		Map<String,String> encodedMap = new HashMap<String,String>();
		
		//getting filenames and encoding transaction id into them
		for(String unencoded:map.keySet()){
			encodedMap.put(unencoded+"_"+trans.getTnxId(), map.get(unencoded));
		}
		return encodedMap;
	}
}
