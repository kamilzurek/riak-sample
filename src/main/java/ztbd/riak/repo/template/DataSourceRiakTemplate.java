package ztbd.riak.repo.template;

import com.basho.riak.client.api.RiakClient;

public interface DataSourceRiakTemplate {

	RiakClient getDataSource();

}
