package cyb.excelproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import cyb.excelproc.exceptions.FileProcessingException;

public interface IFileProcessor {
	public List<String> parseStreams(List<InputStream> finp) throws FileProcessingException;
	public Map<String,String> processData(List<String> data) throws FileProcessingException;
	public List<File> writeToFiles(String filePath,Map<String,String> dataMap) throws FileProcessingException,FileNotFoundException;
}
