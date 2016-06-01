package cyb.excelproc.utilities;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerUtils {
	private LoggerUtils(){}
	
	public static void loggerInit(String path){
		PropertyConfigurator.configure(path+File.separator+"WEB-INF"+File.separator+"log4j.properties");
		Logger.getLogger(LoggerUtils.class).info("Logger initialised");
	}
}
