package cyb.excelproc.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cyb.excelproc.utilities.EntityManagerUtils;
import cyb.excelproc.utilities.LoggerUtils;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  {
    	System.setProperty("logdir", System.getenv("logdir"));
        LoggerUtils.loggerInit(event.getServletContext().getRealPath(""));
    	EntityManagerUtils.getFactory();
        System.out.println("context initilized");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
        EntityManagerUtils.closeFactory();
    }
	
}
