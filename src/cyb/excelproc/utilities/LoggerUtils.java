package cyb.excelproc.utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerUtils {
	private LoggerUtils(){}
	static{
		PropertyConfigurator.configure("log4j.properties");
	}
}
