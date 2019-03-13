package cn.deepdraw.integration.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.deepdraw.dao.UserDao;
import cn.deepdraw.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:TestSpring.xml")
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	@Test
	public void save_happyPath() {

		User user = new User();
		user.setUsername("zhangsan");
		userDao.save(user);
		User user2 = userDao.getByUsername("zhangsan");

		assertNotNull(user2);
	}

	@Test
	public void getByUsername_happyPath() {

		User user = new User();
		user.setUsername("zhangsan");
		userDao.save(user);
		User user2 = userDao.getByUsername("zhangsan");

		assertNotNull(user2);
	}

	@Test
	public void getByUsername_shouldBeNull_whenUserNotExis() {

		User user = userDao.getByUsername("zhangsan");

		assertNull(user);
	}

	@Test
	public void find_happyPath() {

		User user1 = new User();
		user1.setUsername("zhangsan");
		user1.setPassword("111");
		userDao.save(user1);
		User user2 = userDao.find("zhangsan", "111");

		assertNotNull(user2);
	}

	@Test
	public void find_shouldBeNull_whenUserNotExist() {

		User user = userDao.find("zhangsan", "111");

		assertNull(user);
	}

}
