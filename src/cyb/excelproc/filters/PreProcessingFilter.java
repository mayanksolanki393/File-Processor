package cyb.excelproc.filters;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import cyb.excelproc.dao.IActionDao;
import cyb.excelproc.dao.ITransactionDao;
import cyb.excelproc.dao.impl.ActionDaoImpl;
import cyb.excelproc.dao.impl.TransactionDaoImpl;
import cyb.excelproc.entities.Action;
import cyb.excelproc.entities.File;
import cyb.excelproc.entities.File.FileType;
import cyb.excelproc.entities.Transaction;

/**
 * Servlet Filter implementation class TransactionProcessingFilter
 */

@WebFilter("/ProcessData")
public class PreProcessingFilter implements Filter {
	
	ITransactionDao transactionDao;
	IActionDao actionDao;
	private static Logger log = Logger.getLogger(PreProcessingFilter.class);
	
    public PreProcessingFilter() {
        // TODO Auto-generated constructor stub
    }

	public void init(FilterConfig fConfig) throws ServletException {
		try{
			transactionDao = new TransactionDaoImpl();
			actionDao = new ActionDaoImpl();
		}
		catch(Exception ex){
			log.error(ex);
			ex.printStackTrace();
		}
	}

	
	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try{
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession session = req.getSession();
			System.out.println("Preprocessor called");
			if(session.getAttribute("transaction")!=null){
				//getting transaction object from session
				Transaction transaction = (Transaction) session.getAttribute("transaction");
			
				List<InputStream> list = processForm(req,transaction);
				System.out.println("List "+list.size());
				req.setAttribute("fileStreams",list);
				
				//setting new/updated details on transaction
				transaction.setTnxLastModificationTime(new Date());
				
				//persisting transaction on database
				transactionDao.setTransaction(transaction);
				
				chain.doFilter(request, response);
			}
			else{
				resp.sendRedirect("index.jsp");
			}
		}
		catch(Exception ex){
			log.error(ex);
			ex.printStackTrace();
		}
		
	}
	
	public List<InputStream> processForm(HttpServletRequest request,Transaction transactions){
		List<InputStream> inputStreamList = new LinkedList<>();
		
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();
					
		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = request.getServletContext();
		java.io.File repository = (java.io.File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
					
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
					
		try{
			// Parse the request
			List<FileItem> items = upload.parseRequest(request);
				
			//getting refrence of individual form elements
			Iterator<FileItem> iter = items.iterator();
					
			//iterating on the form elements
			while (iter.hasNext()) {
				//if form element is uploded file then add it to InputStream list
				FileItem item = iter.next();
				if (item.isFormField()){
					if(item.getFieldName().equals("actionId")){
						//getting actionId from form
						Action action = new Action(Long.parseLong(item.getString()));
						
						//getting action object form dao
						action = actionDao.getActionById(Long.parseLong(item.getString()));
						transactions.setAction(action);
					}
				}
				else{
					//adding stream to file
					inputStreamList.add(item.getInputStream());
					
					//creating a file and adding to transactions
					File file = new File(transactions, item.getName(), FileType.INPUT);
					transactions.addFile(file);
				}
			}
			return inputStreamList;
		}
		catch(Exception ex){
			log.error(ex);
			ex.printStackTrace();
			return null;
		}
	}
}
