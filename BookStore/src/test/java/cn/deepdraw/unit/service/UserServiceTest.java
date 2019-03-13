package cn.deepdraw.unit.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import cn.deepdraw.dao.UserDao;
import cn.deepdraw.entity.User;
import cn.deepdraw.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserDao dao;

	@Mock
	private User user;

	@InjectMocks
	private UserService service;

	@Test
	public void login_happyPath() {

		when(user.getUsername()).thenReturn("username");
		when(user.getPassword()).thenReturn("password");
		when(dao.find("username", "password")).thenReturn(user);

		assertTrue(service.login(user));
	}

	@Test
	public void login_shouldBeNull_whenUserNotExist() {

		when(user.getUsername()).thenReturn("username");
		when(user.getPassword()).thenReturn("password");
		when(dao.find("username", "password")).thenReturn(null);

		assertFalse(service.login(user));
	}

	@Test
	public void save_happyPath() {

		when(user.getUsername()).thenReturn("username");
		when(user.getPassword()).thenReturn("password");
		when(user.getEmail()).thenReturn("email");
		when(user.getTel()).thenReturn("tel");
		when(dao.getByUsername("username")).thenReturn(null);
		dao.save(user);

		verify(dao).save(user);
	}

	@Test
	public void save_shouldBeNull_whenUserIsExisted() {

		when(user.getUsername()).thenReturn("username");
		when(dao.getByUsername("username")).thenReturn(user);

		assertFalse(service.register(user));
	}
}
