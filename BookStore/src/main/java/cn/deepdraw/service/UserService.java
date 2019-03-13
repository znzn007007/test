package cn.deepdraw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.deepdraw.dao.UserDao;
import cn.deepdraw.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao dao;

	public boolean login(User user) {

		return dao.find(user.getUsername(), user.getPassword()) != null;
	}

	public boolean register(User user) {

		if (getByUsername(user.getUsername()) != null) {

			return false;
		}
		dao.save(user);
		return true;
	}

	public User getByUsername(String username) {

		return dao.getByUsername(username);
	}
}
