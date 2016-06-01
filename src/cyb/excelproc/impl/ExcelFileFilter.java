package cyb.excelproc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelFileFilter extends ExcelFileProccessor {

	@Override
	public Map<String, String> processData(List<String> dataList) {
	      StringBuilder sb = new StringBuilder();
		  for(String someData:dataList){
			  sb.append(someData);
		  }
		  String data = sb.toString();
		  
		  // Creating a Pattern objects
	      Pattern patAlpha = Pattern.compile("[A-Za-z]{1,}");
	      Pattern patNum = Pattern.compile("[0-9]{1,}");
	      Pattern patAlphaNum = Pattern.compile("[^A-Za-z0-9]{1,}");

	      // Now creating matcher objects.
	      Matcher alphaMatcher = patAlpha.matcher(data);
	      Matcher numMatcher = patNum.matcher(data);
	      Matcher alphaNumMatcher = patAlphaNum.matcher(data);
	      
	      //String buffers to temporarily store data
	      StringBuilder buffAlpa = new StringBuilder();
	      StringBuilder buffNum = new StringBuilder();
	      StringBuilder buffAlphaNum = new StringBuilder();
	      
	      //finding matchs
	      while(alphaMatcher.find( )) {
	    	  buffAlpa.append(alphaMatcher.group(0));
	      } 
	      
	      while(numMatcher.find( )) {
	    	  buffNum.append(numMatcher.group(0));
	      }
	      
	      while(alphaNumMatcher.find( )) {
	    	  buffAlphaNum.append(alphaNumMatcher.group(0));
	      }
	      
	      //creating map
	      HashMap<String,String> dataMap = new HashMap<String, String>();
	      dataMap.put("Alphapets", buffAlpa.toString());
	      dataMap.put("Numbers", buffNum.toString());
	      dataMap.put("Alphanumeric", buffAlphaNum.toString());
	      
	      //returning data
	      return dataMap;
	}


}
