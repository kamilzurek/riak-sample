package ztbd.riak.repo.riak;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.ListKeys;
import com.basho.riak.client.api.commands.kv.MultiFetch;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.api.commands.mapreduce.BucketMapReduce;
import com.basho.riak.client.api.commands.mapreduce.MapReduce;
import com.basho.riak.client.core.RiakFuture;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.functions.Function;
import com.basho.riak.client.core.util.BinaryValue;
import com.fasterxml.jackson.databind.JsonNode;

import ztbd.riak.model.Book;
import ztbd.riak.repo.BookRepo;
import ztbd.riak.repo.template.DataSourceRiakTemplate;

@Repository("riakBookRepo")
public class RiakBookRepo implements BookRepo {

	@Autowired
	private DataSourceRiakTemplate riakTemplate;

	@Override
	public void saveBook(Book book) {
		StoreValue storeCmd = new StoreValue.Builder(book).build();
		try {
			riakTemplate.getDataSource().execute(storeCmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Book getBookById(Object id) {
		String key = String.valueOf(id);
		Location location = new Location(new Namespace(Book.BUCKET), BinaryValue.create(key));
		FetchValue fetchCmd = new FetchValue.Builder(location).build();
		try {
			return riakTemplate.getDataSource().execute(fetchCmd).getValue(Book.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> getAllBooks() {
		Namespace ns = new Namespace(Book.BUCKET);
		ListKeys keysCmd = new ListKeys.Builder(ns).build();
		List<Book> books = new LinkedList<>();
		try {
			ListKeys.Response resp = riakTemplate.getDataSource().execute(keysCmd);
			MultiFetch mfcmd = new MultiFetch.Builder().addLocations(resp).build();
			MultiFetch.Response mfResp = riakTemplate.getDataSource().execute(mfcmd);
			for (RiakFuture<FetchValue.Response, Location> f : mfResp)
			{
			     FetchValue.Response valResp = f.get();
			     books.add(valResp.getValue(Book.class));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return books;
	}

	@Override
	public Map<String, Integer> groupByCategory() {
		Namespace ns = new Namespace(Book.BUCKET);
		BucketMapReduce mr = new BucketMapReduce.Builder()
				.withNamespace(ns)
				.withMapPhase(Function.newAnonymousJsFunction("function(v) { var parsed_data = JSON.parse(v.values[0].data); var data = {}; data[parsed_data.category] = 1; return [data]; }"))
				.withReducePhase(Function.newAnonymousJsFunction("function(v) { var totals = {}; for (var i in v) { for(var category in v[i]) { if( totals[category] ) totals[category] += v[i][category]; else totals[category] = v[i][category]; } } return [totals]; }"), true)
				.build();

		Map<String, Integer> groups = new HashMap<>();
		try {
			MapReduce.Response resp = riakTemplate.getDataSource().execute(mr);
			Iterator<Map.Entry<String, JsonNode>> it = resp.getResultForPhase(1).get(0).fields();
			while (it.hasNext()) {
				Map.Entry<String, JsonNode> el = it.next();
				groups.put(el.getKey(), el.getValue().asInt());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groups;
	}

}
