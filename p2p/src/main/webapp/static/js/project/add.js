$(function() {
	
	$("#addBtn").click(function() {
		$("#addForm").submit();
	});

	$("#addForm").validate({
		errorElement : "span",
		errorClass : "text-danger",
		rules : {
			projectName : {
				required : true,
				//remote:"/pro/validate/name"
			},
			sumMoney : {
				required : true,
				number: true,
				min:5
			},
			repayrate :{
				required : true
			},
			months : {
				required : true
			},
			beginDate :{
				required : true
			}
		},
		messages : {
			projectName : {
				required : "请输入项目名称",
				//remote: "项目名称已存在"
			},
			sumMoney : {
				required : "请输入项目金额",
				number: "shuzi",
				min: "z5"
			},
			repayrate :{
				required : "请输入项目回报率"
			},
			months : {
				required : "请输入项目周期"
			},
			beginDate :{
				required : "请输入项目开始时间"
			}
		
		},
		submitHandler : function() {
			$.ajax({
				url : "/pro/add",
				type : "post",
				data : $("#addForm").serialize(),
				beforeSend : function(data) {
					$("#addBtn").text("提交中..").attr("disabled", "disabled");
				},
				success : function(data) {
					if (data.state == 'success') {
						layer.alert("添加成功",function(){
							window.history.go(0);
						});
						//window.location.href = "/pro/list";
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