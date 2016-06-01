package cyb.excelproc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelFileConcatinator extends ExcelFileProccessor {

	@Override
	public Map<String, String> processData(List<String> dataList) {
		StringBuilder sb = new StringBuilder();
		for(String data : dataList){
			sb.append(data);
		}
		
		HashMap<String,String> output = new HashMap<>();
		output.put("output",sb.toString());
		
		return output;
	}
}
