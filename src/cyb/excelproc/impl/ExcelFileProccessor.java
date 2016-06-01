package cyb.excelproc.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cyb.excelproc.IFileProcessor;
import cyb.excelproc.exceptions.FileProcessingException;

public abstract class ExcelFileProccessor implements IFileProcessor {
	private static Logger log = Logger.getLogger(ExcelFileProccessor.class);
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
				         		break;
				            case Cell.CELL_TYPE_NUMERIC:
				                 data.append(""+cell.getNumericCellValue());
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
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<File> writeToFiles(String filePath, Map<String, String> dataMap) throws FileProcessingException,FileNotFoundException {
		List<File> files = new LinkedList<>();
		File directory = new File(filePath);
		System.out.println(filePath);
	
		//checking if given path is a directory
		if(!directory.isDirectory()){
			throw new FileProcessingException("please provide a directory path");
		}
		
		directory = new File(filePath+File.separator+"target");
		
		//creating directory if doesnot exists to store the results
		if(!directory.exists()){
			directory.mkdirs();
		}
		
		for(String fileName:dataMap.keySet()){
			String path = directory.getAbsolutePath()+File.separator+fileName+".txt";
			files.add(new File(path));
			PrintWriter fileWriter = new PrintWriter(path);
			fileWriter.write(dataMap.get(fileName));
			fileWriter.close();
		}
		return files;
	}
}
