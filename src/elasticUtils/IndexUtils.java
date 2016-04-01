package elasticUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;


public class IndexUtils {

	 Logger logger = Logger.getLogger("IndexUtils");
	
	public IndexUtils() {

	}

	public  boolean CSVtoIndex(String fileName, String indexName) {
		File file = new File(fileName);
		if(!FileHelper.fileExists(file)) {
			logger.error("File does not exists");
			return false;
		}
		Map<String, String> json = new HashMap<String, String>();
		FileReader reader;
		String line = null;
		List<String> headers = null;
		List<String> data = null;
		try {
			reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);
			line = buffer.readLine();
			headers = Arrays.asList(line.split(","));
			data = null;
			logger.info("The file has "+headers.size()+ " columns. Scanned them in as headers");
			while((line = buffer.readLine())!=null) {
			 	int coutner = 0;
				data = Arrays.asList(line.split(","));
				for(int i=0; i <headers.size(); i++ ) {
					json.put(headers.get(i), data.get(i));
				}
				indexOperations.insertIndex(indexName, json);
					String mapasJson = new ObjectMapper().writeValueAsString(json);
					if(!indexOperations.indexExists(indexName)){
						indexOperations.createIndex(indexName);
					}
					logger.info("********************************************************************************************************************************");
					logger.info(mapasJson);
					//indexOperations.insertIndex(indexName, mapasJson);
					indexOperations.appendIndex(indexName, mapasJson);
				}
			logger.info("********************************************************************************************************************************");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
		
	}
}

