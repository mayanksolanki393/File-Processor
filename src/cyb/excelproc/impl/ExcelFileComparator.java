package cyb.excelproc.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cyb.excelproc.exceptions.FileProcessingException;

public class ExcelFileComparator extends ExcelFileProccessor{

	private static Logger log = Logger.getLogger(ExcelFileComparator.class);
	
	@Override
	public List<String> parseStreams(List<InputStream> files) {
		List<String> list = new LinkedList<>();
		
		for(InputStream file : files){
			StringBuilder data = new StringBuilder();
	
			try {
				// Create Workbook instance holding reference to .xlsx file
				XSSFWorkbook workbook = new XSSFWorkbook(file);
	
				// Get first/desired sheet from the workbook
				XSSFSheet sheet = workbook.getSheetAt(0);
	
				// Iterate through each rows one by one
				Iterator<Row> rowIterator = sheet.iterator();
	
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();
	
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						/**
						 * Calling parse cell for each cell
						 */
						switch (cell.getCellType()) 
				         {
				         	case Cell.CELL_TYPE_STRING:
				         		data.append(cell.getStringCellValue());
				         		data.append(" ");
				         		break;
				            case Cell.CELL_TYPE_NUMERIC:
				                 data.append(""+cell.getNumericCellValue());
				                 data.append(" ");
				                 break;
				            default:
				            	 break;
				         }
					}
				}
				list.add(data.toString());
				file.close();
			} catch (Exception e) {
				log.error(e);
				
			}
		}
		return list;
	}
	
	@Override
	public Map<String, String> processData(List<String> data) throws FileProcessingException{
		
		if(data.size()!=2){
			throw new FileProcessingException("Can compare only two files");
		}
		
		HashMap<String, String> output = new HashMap<>();
		
		String str1 = data.get(0);
		String str2 = data.get(1);
		
		if(str1.equals(str2)){
			output.put("output", "Given files have same content");
		}
		else{
			String[] str1s = str1.split(" ");
			for(String str : str1s){
				if(str2.contains(str)){
					str1 = str1.replace(str,"");
					str2 = str2.replace(str,"");
				}
				str1 = str1.trim();
				str2 = str2.trim();
			}
			output.put("output","Given files dont have same content");
			output.put("difference",str1+str2);
		}
		return output;		
	}

	
}
