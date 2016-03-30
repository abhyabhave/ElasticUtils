import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;

import elasticUtils.ElasticConfiguration;
import elasticUtils.ElasticConfigurationReader;
import elasticUtils.ElasticConnectionManager;
import elasticUtils.IndexUtils;
import elasticUtils.indexOperations;
import elasticUtils.indexUtils;

public class Main {

	public static void main(String[] args) {

		
		 
		Logger logger = Logger.getLogger("Main");
		logger.info("Started Main");
		ElasticConfigurationReader configuration = new ElasticConfigurationReader();
		ElasticConfiguration config = configuration.readConfiguration("/Users/abbhave/Office/Workspace/Eclipse_Workspace/Java/ElasticUtils/src/elasticConfiguration.yaml");
		String fileName = 
		indexOperations indexOperations = new indexOperations(config);
		IndexUtils indexUtils = new IndexUtils();
		boolean indexExists = indexOperations.indexExists(config.getIndexName());
		System.out.println("Index Exists "+indexExists);
		try {
			
			boolean deleteIndex = indexOperations.deleteIndex(config.getIndexName());
			System.out.println("Deleted index "+deleteIndex);
			indexExists = indexOperations.indexExists(config.getIndexName());
			System.out.println("Index Exists "+indexExists);
			boolean createIndex = indexOperations.createIndex(config.getIndexName());
			System.out.println("Create Index "+createIndex);
			indexExists = indexOperations.indexExists(config.getIndexName());
			System.out.println("Index Exists "+indexExists);
			indexUtils.CSVtoIndex(fileName, "abbhave")
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
