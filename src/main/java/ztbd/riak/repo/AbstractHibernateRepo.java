package ztbd.riak.repo;

import java.lang.reflect.ParameterizedType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractHibernateRepo<T> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> type;

    @SuppressWarnings("unchecked")
	public AbstractHibernateRepo() {
    	this.type = (Class<T>)
                ((ParameterizedType)getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
	}

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void persist(T entity) {
        getSession().persist(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    public void update(T entity) {
    	getSession().update(entity);
    }

    @SuppressWarnings("unchecked")
	public T getById(Long id) {
    	return (T) getSession().get(type, id);
    }

}
