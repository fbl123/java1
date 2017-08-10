$(function(){
	var flag = true;
	
	$("#cust").select2();
	  
	$("#pro").select2();

	//失去焦点的时候触发该事件
	$("#investMoney").blur(function(){
		var invest = $(this).val();//11111
		var remain = $("#remainMoney").val();//100000
		if(parseInt(invest) > parseInt(remain)) {
			layer.msg("您输入的金额不能大于剩余金额");
			flag = false;
		} else{
			flag = true;
		}
		
	});
	
	
	$("#pro").change(function(){
		var id = $(this).val();   			
			$.get("/pro/info",{"id":id}).done(function(data){
				if(data.state == 'success') {
					//获取data中的值，用于显示到页面上
					var obj = data.data;
					$("#remainMoney").val(obj.remainMoney);
					$("#rate").val(obj.repayRate);
					$("#month").val(obj.month);
					
				} else{
					layer.msg(data.message);
				}
			}).error(function(){
				layer.msg("系统异常");
			});
		});
	 
	$("#addBtn").click(function(){
		if(flag) {
			$("#addForm").submit();
		} else{
			layer.msg("您输入的金额不能大于剩余金额");
		}
	});
	
	$("#addForm").validate({
		errorElement:'span',
		errorClass:'text-danger',
		rules:{
			cust:{
				required: true
			},
			pro:{
				required: true
			},
			investMoney:{
				required: true,
				digits:true,
				min:1000
			},
			signDate:{
				required: true
			}
		},
		messages:{
			cust:{
				required: "请选择客户名称"
			},
			pro:{
				required: "请选择项目名称"
			},
			investMoney:{
				required: "请输入投资金额",
				digits:"请输入整数",
				min:"请输入大于1000的金额"
			},
			signDate:{
				required: "请选择签订日期"
			}
			
		},
		submitHandler:function(){
			$.ajax({
				url:"/invest/add",
				type:"post",
				data:$("#addForm").serialize(),
				beforeSend:function(){
					$("#addBtn").text("提交中...").attr("disabled","disabled");
				},
				success:function(data){
					if(data.state == 'success') {
						layer.alert("新增成功",function(){
							window.location.href="/invest/list";
						});
					}else{
						layer.msg(data.message);
					}
				},
				error:function(){
					layer.msg("系统异常");
				},
				complete:function(){
					$("#addBtn").text("新增").removeAttr("disabled");
				}
				
			});
		}
	});
	
	
})