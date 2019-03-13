package cn.deepdraw.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepdraw.entity.User;
import cn.deepdraw.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin() {

		return "user/login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String toRegister() {

		return "user/register";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user) { // 用来处理用户的登陆请求

		if (service.login(user)) {

			User user2 = service.getByUsername(user.getUsername());

			session.setAttribute("user", user2);
			return "user/welcome";
		}
		return "user/login";
	}

	@RequestMapping(value = "checkUsername", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkUsername(String username) {

		User user = service.getByUsername(username);
		return user == null;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(User user) {

		if (service.register(user)) {

			return "user/login";
		}
		return "user/fail";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {

		session.invalidate();
		return "user/login";
	}
}
