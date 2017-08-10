  $(function(){
	  $("#editBtn").click(function(){
		 $("#editForm").submit(); 
	  });
	  
	  $("#editForm").validate({
		   errorElement : 'span',
			errorClass : 'text-danger',
			rules : {
				company : {
					required : true
				},
				dept : {
					required : true
				}

			},

			messages : {
				company : {
					required : "请选择员工公司",
				},
				dept : {
					required : "请选择员工部门",
				}
			},
			submitHandler:function(){
				$.post("/emp/edit",$("#editForm").serialize()).done(function(data){
					if(data.state == 'success') {
						layer.alert("修改成功",function(){
							window.location.href="/emp/list";
						});
					}else{
						layer.alert(data.message);
					}
				}).error(function(){
					layer.alert("系统异常");
				})
			}
		  
		  
	  });
	  
	  
  });