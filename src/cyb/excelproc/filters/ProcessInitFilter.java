package cyb.excelproc.filters;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cyb.excelproc.dao.ITransactionDao;
import cyb.excelproc.dao.impl.TransactionDaoImpl;
import cyb.excelproc.entities.File;
import cyb.excelproc.entities.Transaction;
import cyb.excelproc.entities.User;
import cyb.excelproc.entities.File.FileType;

/**
 * Servlet Filter implementation class TransacionInit
 */
@WebFilter("/welcome.jsp")
public class ProcessInitFilter implements Filter {
	ITransactionDao transactionDao;
	Logger log; 
    public ProcessInitFilter() {
    	try{
    		transactionDao = new TransactionDaoImpl();
    		log = Logger.getLogger(this.getClass());
    	}
    	catch(Exception ex){
    		log.error(ex);
    		ex.printStackTrace();
    	}
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
		try{
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			log.info("Transaction init called");
			if(req.getSession().getAttribute("user") != null && req.getSession().getAttribute("transaction")==null){
				log.info("creating new transaction");
				//creating transaction object
				Transaction trans = new Transaction();
				
				//setting basic values
				trans.setTnxCreationTime(new Date());
				trans.setTnxLastModificationTime(new Date());
				trans.setUser((User) req.getSession().getAttribute("user"));
				
				//saving transaction to generate transaction id
				trans = transactionDao.addTransaction(trans);
				
				//putting transaction into session for further processing
				req.getSession().setAttribute("transaction", trans);
				
				//calling next filter/servlet
				chain.doFilter(request, response);
			}
			else{
				if(req.getSession().getAttribute("user") != null){
					chain.doFilter(request, response);
				}	
				//sending user to login page if user is not set
				else{
				resp.sendRedirect("index.jsp");
				}
			}
		}
		catch(Exception ex){
			log.error(ex);
			ex.printStackTrace();
		}		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
