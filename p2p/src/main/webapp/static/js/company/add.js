$(function() {
	
	$("#addBtn").click(function() {
		$("#addForm").submit();
	});

	$("#addForm").validate({
		errorElement : "span",
		errorClass : "text-danger",
		rules : {
			compName : {
				required : true,
				remote:"/com/validate"
			},
			phone :{
				required : true
			},
			city : {
				required : true
			},
		},
		messages : {
			compName : {
				required : "请输入公司名称",
				remote: "公司名已存在"
			},
			phone :{
				required : "请输入公司电话"
			},
			city : {
				required : "请输入公司所在城市"
			},
		},
		submitHandler : function() {
			$.ajax({
				url : "/com/add",
				type : "post",
				data : $("#addForm").serialize(),
				beforeSend : function(data) {
					$("#addBtn").text("提交中..").attr("disabled", "disabled");
				},
				success : function(data) {
					if (data.state == 'success') {
						layer.msg("添加成功");
						window.location.href = "/com/list";
					} else {
						layer.alert(data.message);
					}
				},
				error : function() {
					layer.alert("服务器错误");
				},
				complete : function() {
					$("#addBtn").text("新增").removeAttr("disabled");

				}
			});
		}
	});

})