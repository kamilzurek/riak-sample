package ztbd.riak.repo.template;

import java.net.UnknownHostException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.RiakNode;

@Component
public class DataSourceRiakTemplateImpl implements DataSourceRiakTemplate {

	private RiakClient client = null;

	@PostConstruct
	public void init() {
		System.out.println("Setting cluster...");
		try {
			RiakNode node = new RiakNode.Builder().withRemoteAddress("127.0.0.1").withRemotePort(8087).build();
			RiakCluster cluster = RiakCluster.builder(node).build();
			cluster.start();
			client = new RiakClient(cluster);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@Override
	public RiakClient getDataSource() {
		return client;
	}

	@PreDestroy
	public void finish() {
		if (client != null) {
			client.shutdown();
		}
	}

}
