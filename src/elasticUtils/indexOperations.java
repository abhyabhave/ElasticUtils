package elasticUtils;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;

public class indexOperations {

	static Logger logger = Logger.getLogger("indexUtils");
	static IndicesAdminClient indexAdminClient = null;
	static Client client = null;

	public indexOperations(ElasticConfiguration config) {
		client = ElasticConnectionManager.createConnection(config);
		indexAdminClient = client.admin().indices();
	}

	public static boolean indexExists(String indexName) {
		logger.info("Checking if inedx " + indexName + " exists");
		IndicesExistsRequest request = new IndicesExistsRequest(indexName);
		IndicesExistsResponse response = indexAdminClient.exists(request).actionGet();
		if (response.isExists()) {
			logger.info("Found index. Index exists");
			return true;
		}
		logger.info("Could not find index");
		return false;
	}

	public static boolean createIndex(String indexName) {
		logger.info("Creating an index with name " + indexName);
		CreateIndexRequest request = new CreateIndexRequest(indexName);
		CreateIndexResponse response = indexAdminClient.create(request).actionGet();
		if (!response.isAcknowledged()) {
			logger.error("Could not create the index " + indexName);
			return false;
		}
		logger.info("Created index " + indexName);
		return true;
	}

	public static boolean deleteIndex(String indexName) {
		logger.info("Deleting the index " + indexName);
		DeleteIndexRequest request = new DeleteIndexRequest(indexName);
		DeleteIndexResponse response = indexAdminClient.delete(request).actionGet();
		if (!response.isAcknowledged()) {
			logger.error("Could not delete the index " + indexName);
			return false;
		}
		logger.info("Deleted index " + indexName);
		return true;
	}

	public static boolean appendIndex(String indexName, String string) {
		logger.info("Appending data to index " + indexName);
		UpdateRequest request = new UpdateRequest();
		request.index(indexName);
		request.doc(string);
		try {
			logger.info("Updating the index");
			client.update(request).get();
		} catch (InterruptedException e) {
			logger.error("Cloud not update the index", e);
			return false;
		} catch (ExecutionException e) {
			logger.error("Cloud not update the index", e);
			return false;
		}

		return true;
	}
	
	public static boolean appendIndex(String indexName, HashMap<String, String> source){
		logger.info("Appending data to index "+indexName+ " via map.");
		UpdateRequest request = new UpdateRequest();
		request.index(indexName);
		request.doc(source);
		try{
			logger.info("Updating the index using Maps");
			client.update(request).get();
		}
		catch(InterruptedException e) {
			logger.error("Could not update the index ", e);
			return false;
		}
		catch (ExecutionException e) {
			logger.error("Cloud not update the index ", e);
			return false;
		}
		return true;
	}
}
