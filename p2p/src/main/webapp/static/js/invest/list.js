$(function(){
	
	//续约
	//点击续约按钮，发起ajax请求到后台获得客户、项目、投资等信息回显到modal框，再显示出modal框
	  $(".concat").click(function(){
		  var id = $(this).attr("rel");
		  $.get("/invest/concat",{"id":id}).done(function(data){
				if(data.state == 'success') {
					//数据回显 data.cust.name
					$("#cust").val(data.cust.id);
					$("#custName").val(data.cust.name + "-" + data.cust.tel);
					$("#pro").val(data.pro.id);
					$("#proName").val(data.pro.projectName);
					$("#remainMoney").val(data.pro.remainMoney);
					$("#investMoney").val(data.invest.investMoney);
					$("#rate").val(data.invest.rate);
					$("#month").val(data.invest.month);
					var signDate = data.invest.endDate;
					$("#signDate").val(signDate);
					var endDate = moment(signDate).add(data.invest.month,"months").format("YYYY-MM-DD");
					$("#endDate").val(endDate);
					//显示modal框
					 $("#concatModal").modal({
				           show:true,
				           backdrop:'static'
				     });
					
				} else{
					layer.msg(data.message);
				}
			}).error(function(){
				layer.msg("系统异常");
			});
		  
		  
	       
	    });
	
	//点击续约按钮，发起ajax请求
	  $("#concatBtn").click(function(){
		  $.ajax({
			 url:"/invest/add",
			 type:"post",
			 data:$("#concatForm").serialize(),
			 success:function(data){
				 if(data.state == 'success') {
						layer.alert("续约成功",function(){
							window.history.go(0);
						});
					} else{
						layer.msg(data.message);
					}
			 },
			 error:function(){
				 layer.msg("系统异常");
			 }
		  });
	  });
	  
	  
	
	$(".del").click(function(){
		alert(123);
		var id = $(this).attr("rel");
		layer.confirm("确定删除么？",function(){
			$.get("/invest/del",{"id":id}).done(function(data){
				if(data.state == 'success') {
					layer.msg("删除成功");
					window.history.go(0);
					
				} else{
					layer.msg(data.message);
				}
			}).error(function(){
				layer.msg("系统异常");
			});
			
		})
		
		
	});
	
	$(".unuse").click(function(){
		var id = $(this).attr("rel");
		layer.confirm("确定解约么？",function(){
			$.get("/invest/unuse",{"id":id}).done(function(data){
				if(data.state == 'success') {
					layer.msg("解约成功");
					window.history.go(0);
				} else{
					layer.msg(data.message);
				}
			}).error(function(){
				layer.msg("系统异常");
			});
			
		})
		
		
	});
	
	
});