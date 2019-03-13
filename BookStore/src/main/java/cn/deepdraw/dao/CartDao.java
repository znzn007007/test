package cn.deepdraw.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.entity.Cart;

@Repository
@Transactional
public class CartDao extends BaseDao<Cart> {

	public Cart getByUserId(int userId) {

		String hql = "from Cart where user_id = ?0";
		return findOne(hql, userId);
	}

	public void deleteItem(int id) {

		String hql = "delete CartItem where id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}
}
