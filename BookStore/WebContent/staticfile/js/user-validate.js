$.validator.setDefaults({
	submitHandler: function (form) {
		form.submit();
	}
});

$(function () {
	$("#registerForm").validate({
		rules: {
			username: {
				required: true,
				rangelength: [ 5, 10 ],
				remote: {
					url: "checkUsername",
					type: "post",
					dataType: "json",
					data: {
						username: function () {
							return $("#username").val();
						}
					}
				}
			},
			password: {
				required: true,
				minlength: 5
			},
			password2: {
				required: true,
				minlength: 5,
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
			},
			tel: {
				required: true,
				minlength: 11,
				maxlength: 11
			}
		},
		messages: {
			username: {
				required: "请输入用户名",
				rangelength: "用户名长度在5到10个字符",
				remote: "用户名已存在"
			},
			password: {
				required: "请输入密码",
				minlength: "密码长度不能小于 5 个字母"
			},
			password2: {
				required: "请输入密码",
				minlength: "密码长度不能小于 5 个字母",
				equalTo: "两次密码输入不一致"
			},
			email: "请输入一个正确的邮箱",
			tel: "请输入一个正确的电话号码"
		}
	});

	$("#loginForm").validate({
		rules: {
			username: {
				required: true,
				rangelength: [ 5, 10 ],
			},
			password: {
				required: true,
				minlength: 5
			}
		},
		messages: {
			username: {
				required: "请输入用户名",
				rangelength: "用户名长度在5到10个字符"
			},
			password: {
				required: "请输入密码",
				minlength: "密码长度不能小于 5 个字母"
			}
		}
	});
});