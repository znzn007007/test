$.validator.setDefaults({
	submitHandler: function (form) {
		form.submit();
	}
});

$(function () {
	$("#bookForm").validate({
		rules: {
			name: {
				required: true,
				maxlength: 20
			},
			author: {
				required: true,
				maxlength: 10
			},
			isbn: {
				required: true,
				isbn: true,
				minlength: 5,
				maxlength: 5
			},
			price: {
				required: true,
				number: true
			},
			publishDate: {
				required: true,
				dateISO: true
			},
			introduction: {
				required: true,
				maxlength: 1000
			},
			coverImg: {
				required: true,
				checkImg: true
			}
		},
		messages: {
			name: {
				required: "书名不能为空",
				maxlength: "书名不得超过20字符"
			},
			author: {
				required: "作者不能为空",
				minlength: "作者不得超过10字符"
			},
			isbn: {
				required: "isbn不能为空",
				minlength: "isbn不能少于5字符",
				maxlength: "isbn不能超过5字符"
			},
			price: {
				required: "价格不能为空"
			},
			publishDate: {
				required: "出版日期不能为空"
			},
			introduction: {
				required: "简介不能为空",
				maxlength: "简介不得超过1000字符"
			},
			coverImg: {
				required: "请添加封面！！"
			}
		}
	});
});