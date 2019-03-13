package cn.deepdraw.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.entity.User;

@Repository
@Transactional
public class UserDao extends BaseDao<User> {

	public User find(String username, String password) {

		String hql = "from User where username = ?0 and password = ?1";

		return findOne(hql, username, password);
	}

	public User getByUsername(String username) {

		String hql = "from User where username = ?0";

		return findOne(hql, username);
	}

}
