package cn.deepdraw.unit.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

import cn.deepdraw.controller.UserController;
import cn.deepdraw.entity.User;
import cn.deepdraw.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@Mock
	MockHttpSession session;

	@Mock
	private User user;

	@Mock
	private ModelMap model;

	@Mock
	private UserService service;

	@InjectMocks
	private UserController controller;

	MockMvc mockMvc;

	@Before
	public void initMokcMvc() {

		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void toLogin_happyPath() throws Exception {

		MvcResult mvcResult = mockMvc.perform(get("/login")).andReturn();
		Assert.assertEquals("user/login", mvcResult.getModelAndView().getViewName());
	}

	@Test
	public void toRegister_happyPath() throws Exception {

		MvcResult mvcResult = mockMvc.perform(get("/register")).andReturn();
		assertEquals("user/register", mvcResult.getModelAndView().getViewName());
	}

	@Test
	public void login_happyPath_mvc() throws Exception {

		when(service.login(any())).thenReturn(true);
		when(service.getByUsername("username")).thenReturn(user);

		mockMvc.perform(post("/login").param("username", "username").param("password", "password").session(session))
				.andExpect(status().isOk()).andExpect(forwardedUrl("user/welcome")).andDo(print()).andReturn();
	}

	@Test
	public void login_happyPath() throws Exception {

		when(service.login(user)).thenReturn(true);
		when(user.getUsername()).thenReturn("username");
		when(service.getByUsername(user.getUsername())).thenReturn(user);

		assertEquals("user/welcome", controller.login(user));
		verify(session).setAttribute("user", user);
	}

	@Test
	public void login_shouldFail_whenUserExisted_mvc() throws Exception {

		when(service.login(user)).thenReturn(false);

		mockMvc.perform(post("/login").param("username", "username").param("password", "password").session(session))
				.andExpect(status().isOk()).andExpect(forwardedUrl("user/login")).andDo(print()).andReturn();
	}

	@Test
	public void login_shouldFail_whenUserExisted() throws Exception {

		when(service.login(user)).thenReturn(false);

		assertEquals("user/login", controller.login(user));
	}

	@Test
	public void register_happyPath_mvc() throws Exception {

		when(service.register(any())).thenReturn(true);

		mockMvc.perform(post("/register").param("username", "username").param("password", "password")
				.param("email", "email").param("tel", "tel")).andExpect(status().isOk())
				.andExpect(forwardedUrl("user/login")).andDo(print()).andReturn();
	}

	@Test
	public void register_happyPath() {

		when(service.register(user)).thenReturn(true);

		assertEquals("user/login", controller.register(user));
	}

	@Test
	public void register_shouldToFail_whenRegisterFailed_mvc() throws Exception {

		when(service.register(any())).thenReturn(false);

		mockMvc.perform(post("/register").param("username", "username").param("password", "password")
				.param("email", "email").param("tel", "tel")).andExpect(status().isOk())
				.andExpect(forwardedUrl("user/fail")).andDo(print()).andReturn();
	}

	@Test
	public void register_shouldToFail_whenRegisterFailed() {

		when(service.register(user)).thenReturn(false);

		assertEquals("user/fail", controller.register(user));
	}

	@Test
	public void logout_HappyPath_mvc() throws Exception {
		mockMvc.perform(get("/logout")).andExpect(status().isOk()).andExpect(forwardedUrl("user/login")).andDo(print())
				.andReturn();

	}

	@Test
	public void logout_HappyPath() {

		assertEquals("user/login", controller.logout());
		verify(session).invalidate();
	}

}
