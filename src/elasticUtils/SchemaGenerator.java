package elasticUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

public class SchemaGenerator {
	
	Logger logger = Logger.getLogger("SchemaGenerator");

	public SchemaGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	SchemaDefination schema = new SchemaDefination();
	
	public void createElasticSchema(String fileName) {
		
		try {
			FileReader reader = new FileReader(fileName);
			BufferedReader buffer = new BufferedReader(reader);
			//Reading the headers line and discarding
			buffer.readLine();
			String line;
			while((line = buffer.readLine()) != null) {
			//CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.parse(in))	
			}
			
		} catch (FileNotFoundException e1) {
			logger.info("Could not find the said CSV file for import ",e1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Yaml yaml = new Yaml();
		try{
			schema = yaml.loadAs(new FileInputStream(new File(fileName)), SchemaDefination.class);
		}
		catch(Exception e) {
			
		}
	}
}
