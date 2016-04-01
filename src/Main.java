import org.apache.log4j.Logger;

import elasticUtils.ElasticConfiguration;
import elasticUtils.ElasticConfigurationReader;
import elasticUtils.IndexUtils;
import elasticUtils.indexOperations;


public class Main {

	public static void main(String[] args) {

		Logger logger = Logger.getLogger("Main");
		logger.info("Started Main");
		ElasticConfigurationReader configuration = new ElasticConfigurationReader();
		ElasticConfiguration config = configuration.readConfiguration(
				"/Users/abbhave/Office/Workspace/Eclipse_Workspace/Java/ElasticUtils/src/elasticConfiguration.yaml");
		String fileName = "/Users/abbhave/Office/Workspace/Eclipse_Workspace/Java/ElasticUtils/src/Status.csv";
		indexOperations indexOperations = new indexOperations(config);
		IndexUtils indexUtils = new IndexUtils();
		boolean indexExists = indexOperations.indexExists(config.getIndexName());
		System.out.println("Index Exists " + indexExists);
		try {
			indexOperations.deleteIndex("abbhave");
			indexUtils.CSVtoIndex(fileName, "abbhave");
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
