package ztbd.riak.repo.hibernate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import ztbd.riak.model.Book;
import ztbd.riak.repo.AbstractHibernateRepo;
import ztbd.riak.repo.BookRepo;

@Repository("hibernateBookRepo")
public class HibernateBookRepo extends AbstractHibernateRepo<Book> implements BookRepo {

	@Override
	public void saveBook(Book book) {
		persist(book);
	}

	@Override
	public Book getBookById(Object id) {
		Long pk = (Long) id;
		long st = System.nanoTime();
		Book book = getById(pk);
		System.out.println(System.nanoTime() - st);
		return book;
	}

	@Override
	public List<Book> getAllBooks() {
		return getSession().createQuery("from Book b").list();
	}

	@Override
	public Map<String, Integer> groupByCategory() {
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("category"), "name");
		projectionList.add(Projections.rowCount(), "count");

		Criteria c = getSession().createCriteria(Book.class);
		c.setProjection(projectionList);
		c.setResultTransformer(new AliasToBeanResultTransformer(GroupDto.class));

		Map<String, Integer> group = new HashMap<>();
		List<GroupDto<String>> list = c.list();
		for (GroupDto<String> dto : list) {
			group.put(dto.getName(), dto.getCount().intValue());
		}
		return group;
	}

	@Override
	public Map<String, Integer> groupBySubCategory() {
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("subcategory"), "name");
		projectionList.add(Projections.rowCount(), "count");

		Criteria c = getSession().createCriteria(Book.class);
		c.setProjection(projectionList);
		c.setResultTransformer(new AliasToBeanResultTransformer(GroupDto.class));

		Map<String, Integer> group = new HashMap<>();
		List<GroupDto<String>> list = c.list();
		for (GroupDto<String> dto : list) {
			group.put(dto.getName(), dto.getCount().intValue());
		}
		return group;
	}

	@Override
	public Map<String, Integer> groupByYear() {
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("issue_date"), "name");
		projectionList.add(Projections.rowCount(), "count");

		Criteria c = getSession().createCriteria(Book.class);
		c.setProjection(projectionList);
		c.setResultTransformer(new AliasToBeanResultTransformer(GroupDto.class));

		Map<String, Integer> group = new HashMap<>();
		List<GroupDto<String>> list = c.list();
		for (GroupDto<String> dto : list) {
			group.put(dto.getName(), dto.getCount().intValue());
		}
		return group;
	}

	@Override
	public Map<String, Integer> groupByRate() {
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("rating"), "name");
		projectionList.add(Projections.rowCount(), "count");

		Criteria c = getSession().createCriteria(Book.class);
		c.setProjection(projectionList);
		c.setResultTransformer(new AliasToBeanResultTransformer(GroupDto.class));

		Map<String, Integer> group = new HashMap<>();
		List<GroupDto<BigDecimal>> list = c.list();
		for (GroupDto<BigDecimal> dto : list) {
			group.put(dto.getName().toString(), dto.getCount().intValue());
		}
		return group;
	}

	@Override
	public List<Book> searchByDescription(String desc) {
		long t = System.currentTimeMillis();
		Query q = getSession().createQuery("select b from Book b where b.desc like :keyword");
		q.setParameter("keyword", "%" + desc + "%");
		List<Book> list = q.list();
		System.out.println("time: " + (System.currentTimeMillis() - t));
		return list;
	}

	public static class GroupDto<T> {
		private T name;
		private Long count;

		public GroupDto() {
		}

		public T getName() {
			return name;
		}

		public void setName(T name) {
			this.name = name;
		}

		public Long getCount() {
			return count;
		}

		public void setCount(Long count) {
			this.count = count;
		}
	}

}
