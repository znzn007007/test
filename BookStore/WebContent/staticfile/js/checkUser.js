function checkUser() {
	var isValid = true; // 控制表单是否提交的变量

	/* 非空检验 */
	isValid = isValid && checkName();
	isValid = isValid && checkNull("password", "密码不能为空！");
	isValid = isValid && checkPwd();
	isValid = isValid && checkNull("email", "邮箱不能为空!");
	isValid = isValid && checkNull("tel", "电话不能为空!");

	/* 根据isValid的值，判断是否提交表单 */
	return isValid;
}

function checkName() {

	var name = $("#username").val();
	if (name == "") {

		setMsg("username", "用户不能为空！");
		return false;
	}

	$.ajax({
		type : "POST",
		url : "checkUsername",
		data : "username=" + name,
		success : function (data) {

			setMsg("username", data.msg);
			return data.state;
		}
	});
}

function checkPwd() {

	var pwd2 = $("#password2").val();
	var pwd = $("#password").val();

	if (!checkNull("password2", "确认密码不能为空！")) {

		return false;
	}

	if (pwd == pwd2) {

		setMsg("password2", "可以使用~~~");
		return true;
	} else {

		setMsg("password2", "两次输入密码不一样~~~");
		return false;
	}

}

/* 检验邮箱格式是否正确 */
function checkEmail() {

	var email = $("#email").val();
	var regex = new RegExp(
			"^^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$");

	if ((email != "") && (!regex.test(email))) {

		/* 邮箱不符合格式 */
		setMsg("email", "邮箱不符合格式")
		return false;
	} else {

		return true;
	}
}

/* 检验文本长度 */
function checkLength(id, length) {

	var valueLenth = $("#" + id).val().length;
	var name = $("#" + id).parent().prev().val();

	if (valueLenth = 0) {

		return false;
	}

	if (valueLenth > length) {

		setMsg(id, name + "长度不能超过" + length);
		return false;
	} else {

		return true;
	}
}

/* 检验非空的方法 */
function checkNull(id, msg) {

	var value = $("#" + id).val();

	if (value == "") {
		setMsg(id, msg);
		return false;
	} else {
		setMsg(id, "");
		return true;
	}
}

/* 给出提示消息的方法 */
function setMsg(id, msg) {

	// document.getElementsByName(id)[0].innerHtml = msg;
	$("." + id).html(msg).css("color", "red");
	console.log(msg);
}