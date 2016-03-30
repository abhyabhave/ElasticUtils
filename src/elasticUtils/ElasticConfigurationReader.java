package elasticUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

public class ElasticConfigurationReader {
	Logger logger = Logger.getLogger("ElasticConfigurationReader");
	ElasticConfiguration configuration = new ElasticConfiguration();

	public ElasticConfiguration readConfiguration(String fileName) {

		Yaml yaml = new Yaml();
		try {
			
			
			configuration = yaml.loadAs(new FileInputStream(new File(fileName)), ElasticConfiguration.class);
			logger.info("Found configuration file.");
		} catch (FileNotFoundException e) {
			logger.error("Did not find configuration.yaml file. Will prompt for configurations", e);
			Scanner scanner = new Scanner(System.in);
			System.out.println("Please enter the hostname for the elastic engine: ");
			configuration.setHostName(scanner.next());
			System.out.println("Please provide the port for the elastic engine [Default 9300] ");
			configuration.setPort(scanner.nextInt());
			System.out.println("Please provide the clusterName to conenct to ");
			configuration.setClusterName(scanner.next());
			System.out.println("Please provide the indexName to operate on ");
			configuration.setIndexName(scanner.next());
			System.out.println("Please provide the operation to run on the index [Rebuild, Append, Delete, Create]");
			configuration.setOperation(scanner.next());
		} 
		finally {
			logger.info("Loaded configuration: ");
			logger.info("hostName : " + configuration.getHostName());
			logger.info("Port : " + configuration.getPort());
			logger.info("clusterName : " + configuration.getClusterName());
			logger.info("indexName : " + configuration.getIndexName());
			logger.info("Operation : " + configuration.getOperation());
			return configuration;
		}
	}
}
