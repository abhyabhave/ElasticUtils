package elasticUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class ElasticConnectionManager {

	static Logger logger = Logger.getLogger("ElasticUtils.ConnectionManager");

	public  ElasticConnectionManager() {
		// TODO Auto-generated constructor stub
	}

	public static Client createConnection(ElasticConfiguration config) {
		Settings settings = Settings.settingsBuilder().put("cluster.name", config.getClusterName()).build();
		Client client = null;
		try {
			client = TransportClient.builder().settings(settings).build().addTransportAddress(
					new InetSocketTransportAddress(InetAddress.getByName(config.getHostName()), config.getPort()));
			logger.info("Connected to the elasticSearch server.");
		} catch (UnknownHostException e) {
			logger.error("Cloud not connect to the elasticSearch Server", e);

		} finally {
			return client;
		}

	}

}
