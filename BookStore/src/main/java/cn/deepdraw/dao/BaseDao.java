package cn.deepdraw.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseDao<T> {

	@Autowired
	private SessionFactory factory;

	protected Session getSession() {

		return factory.getCurrentSession();
	}

	public void save(T t) {

		this.getSession().save(t);
	}

	public void delete(T t) {

		this.getSession().delete(t);
	}

	public void update(T t) {

		this.getSession().update(t);
	}

	public void merge(T t) {

		this.getSession().merge(t);
	}

	public T get(Class<T> clazz, int id) {

		return getSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public T findOne(String hql, Object... params) {

		Query query = getSession().createQuery(hql);
		if (params != null && params.length > 0) {

			for (int i = 0; i < params.length; i++) {

				query.setParameter(i, params[i]);
			}
		}

		return (T) query.uniqueResult();
	}

	public List<T> findList(String hql, Object... params) {

		Query query = getSession().createQuery(hql);
		if (params != null && params.length > 0) {

			for (int i = 0; i < params.length; i++) {

				query.setParameter(i, params[i]);
			}
		}

		return query.list();
	}
}
