$(function() {
	$("#addBtn").click(function() {
		$("#addForm").submit();
	});
	
	$("#addForm").validate({
		errorElement : 'span',
		errorClass : 'text-danger',
		rules : {
			userName : {
				required : true
			},
			idNo : {
				required : true,
				rangelength : [ 5, 5 ],
				//remote : "/validate/idNo"
			},
			tel : {
				required : true,
				rangelength : [ 5, 5 ],
				remote : "/validate/tel"
			},
			company : {
				required : true
			},
			dept : {
				required : true
			}

		},

		messages : {
			userName : {
				required : "请输入员工姓名"
			},
			idNo : {
				required : "请输入员工身份证号",
				rangelength : "长度不正确",
				remote : "该身份证号码已经存在"
			},
			tel : {
				required : "请输入员工电话号码",
				rangelength : "长度不正确",
				remote : "该电话号码已经存在"
			},
			company : {
				required : "请选择员工公司",
			},
			dept : {
				required : "请选择员工部门",
			}
		},
		submitHandler:function(){
			$.ajax({
				url:"/emp/add",
				type:"post",
				data:$("#addForm").serialize(),
				beforeSend:function(){
					$("#addBtn").text("提交中...").attr("disabled","disabled");
				},
				success:function(data){
					if(data.state == 'success') {
						layer.alert('新增成功',function(){
							window.location.href="/emp/list";
						});
					} else{
						layer.alert(data.message);
					}
				},
				error:function(){
					alert("服务器异常");
				},
				complete:function(){
					var btn = $("#addBtn").text("新增").removeAttr("disabled");
				}
			})
		}

	});

});
