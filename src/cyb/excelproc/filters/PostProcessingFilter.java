package cyb.excelproc.filters;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import cyb.excelproc.dao.ITransactionDao;
import cyb.excelproc.dao.impl.TransactionDaoImpl;
import cyb.excelproc.entities.File;
import cyb.excelproc.entities.File.FileType;
import cyb.excelproc.entities.Transaction;

/**
 * Servlet Filter implementation class PostProcessingFilter
 */
@WebFilter(urlPatterns="/result.jsp",dispatcherTypes=DispatcherType.FORWARD)
public class PostProcessingFilter implements Filter {
	ITransactionDao transactionDao;
	private static Logger log = Logger.getLogger(PostProcessingFilter.class);
    public PostProcessingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		Transaction transaction = (Transaction) req.getSession().getAttribute("transaction");
		
		List<java.io.File> fileList = (List<java.io.File>) req.getAttribute("outputFiles");
		
		for(java.io.File file : fileList){
			File myFile = new File(transaction, file.getPath(), FileType.OUTPUT);
			transaction.addFile(myFile);
		}
		
		transaction.setTnxLastModificationTime(new Date());
		try{
			transactionDao.setTransaction(transaction);
		}
		catch(Exception ex){
			log.error(ex);
			ex.printStackTrace();
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		try{
			transactionDao = new TransactionDaoImpl();
		}
		catch(Exception ex){
			log.error(ex);
			ex.printStackTrace();
		}
	}

}
