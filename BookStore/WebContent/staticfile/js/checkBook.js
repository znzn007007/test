/* 表单检验的方法 */
function checkBook() {
	var isValid = true; // 控制表单是否提交的变量

	/* 非空检验 */
	isValid = isValid && checkNull("name", "书名不能为空");
	isValid = isValid && checkNull("author", "作者不能为空");
	isValid = isValid && checkNull("isbn", "isbn不能为空");
	isValid = isValid && checkNull("price", "价格不能为空");
	isValid = isValid && checkNull("public_date", "出版日期不能为空");
	isValid = isValid && checkNull("introduction", "简介不能为空");

	/* 长度检验 */
	isValid = isValid && checkLength("name", 20);
	isValid = isValid && checkLength("author", 10);
	isValid = isValid && checkLength("isbn", 20);
	isValid = isValid && checkLength("introduction", 1000);

	/* 检验isbn是否正确 */
	isValid = isValid && checkISBN();

	/* 检验图片 */
	isValid = isValid && checkImg();
	// console.log(document.getElementById("cover").value);
	console.log($("#cover").val());

	/* 根据isValid的值，判断是否提交表单 */
	return isValid;
}

/* 检验图片的方法 */
function checkImg() {

	var img = $("#cover").val();

	if (img == "") {

		setMsg("cover", "请添加封面！！");
		return false;
	} else {

		var index = img.indexOf(".");
		var suffix = img.substring(index);

		if (isImg(suffix)) {

			setMsg("cover", "不是指定图片格式,重新选择");
			$("#cover").val("");
			return false;
		} else {

			return true;
		}
	}
}

/* 检验是否为图片格式 */
function isImg(suffix) {

	return suffix != ".bmp" && suffix != ".png" && suffix != ".gif"
			&& suffix != ".jpg" && suffix != ".jpeg";
}

/* 检验isbn是否正确 */
function checkISBN() {

	var isbn = $("#isbn").val();
	var regex = new RegExp("^\\w*$");

	if ((isbn != "") && (!regex.test(isbn))) {

		/* isbn不符合格式 */
		setMsg("isbn", "isbn不符合格式")
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
