  $(function(){
	  
	  	$(".del").click(function(){
	  		var id = $(this).attr("rel");
	  		layer.confirm("确定要删除么？",function(){
	  			$.get("/emp/del",{"id":id}).done(function(data){
	  				
	  				if(data.state == 'success') {
	  					layer.msg('删除成功');
	  					window.history.go(0);
	  				} else{
	  					layer.msg(data.message);
	  				}
	  				
	  			}).error(function(){
	  				layer.msg("服务器异常");
	  			});
	  			
	  		});
	  	});

  });
  
