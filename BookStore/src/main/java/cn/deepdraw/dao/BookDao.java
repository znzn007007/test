package cn.deepdraw.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.bean.Page;
import cn.deepdraw.entity.Book;

@Repository
@Transactional
public class BookDao extends BaseDao<Book> {

	public List<Book> getBooks(Page page) {

		String hql = "from Book";

		Query<Book> query = getSession().createQuery(hql);
		query.setFirstResult(page.getStart());
		query.setMaxResults(page.getPageSize());
		return query.list();
	}

	public Long count() {

		String hql = "select count(*) from Book";

		Query query = getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	public Long countBySearch(String goal, String text) {

		String hql = "select count(*) from Book where " + goal + " like ?0";

		Query query = getSession().createQuery(hql);
		query.setParameter(0, "%" + text + "%");
		return (Long) query.uniqueResult();
	}

	public List<Book> search(String goal, String keyword, Page page) {

		String hql = "from Book where " + goal + " like ?0";

		Query query = getSession().createQuery(hql);
		query.setParameter(0, "%" + keyword + "%");
		query.setFirstResult(page.getStart());
		query.setMaxResults(page.getPageSize());
		return query.list();
	}

}
