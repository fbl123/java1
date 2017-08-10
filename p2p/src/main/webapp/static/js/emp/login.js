$(function(){
		$("#loginBtn").click(function(){
			$("#loginForm").submit();
		});
		$("#loginForm").validate({
			errorElement:'span',
			errorClass:'text-danger',
			rules:{
				userName :{
					required:true
				},
				password:{
					required:true
				}
				
			},
			messages:{
				userName :{
					required: "请输入用户名"
				},
				password:{
					required: "请输入密码"
				}
				
				
			},
			submitHandler:function(){
				$.ajax({
					url:"/login",
					type:"post",
					data:$("#loginForm").serialize(),
					beforeSend:function(){
						$("#loginBtn").text("登录中...").attr("disabled","disabled");
					},
					success:function(data){
						if(data.state == 'success') {
							if(data.data) {
								window.location.href=data.data;
							} else{
								window.location.href="/home";
							}
						}else{
							layer.alert(data.message);
						}
					},
					error:function(){
						layer.alert("系统异常")
					},
					complete:function(){
						$("#loginBtn").text("登录").removeAttr("disabled");
					}
					
				});
			}
			
		});
		
		
	});