<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>查询项目信息</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="../include/css.jsp" %>
  	<!-- datepicker -->
  <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
  
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

  <%@ include file="../include/header.jsp"%>
<%@ include file="../include/sider.jsp"%>

  <!-- =============================================== -->

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  
    <!-- Main content -->
    <section class="content">
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
          <h3 class="box-title">搜索</h3>
        </div>
        <div class="box-body">
          <form action="/emp/list" class="form-inline">
            
             <select name="key" id="key" class="form-control">
                <option value="">--请选择查询条件--</option>
                <option value="real_name">项目名称</option>
                <option value="tel">项目编号</option>
              </select>
            <input type="text" class="form-control" name="value" id="value" value="${value}" placeholder="关键字"/>
            <button class="btn btn-primary">搜索</button>
          </form>
        </div>

      </div>
      <!-- Default box -->
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
         <h5 class="pull-left">查询员工信息</h5>
         <a href="javascript:;" id="addPro" class="btn btn-success pull-right">新增项目</a>
        </div>
        <div class="box-body">
          
          <table class="table" id="cust_table">
            <thead>
            <tr>
							<th>项目名称</th>
							<th>总金额</th>
							<th>周期</th>
							<th>回报率</th>
							<th>创建时间</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
            </thead>
            <tbody>
            	<c:choose>
            		<c:when test="${empty page.items}">
            			<tr>
            				<td colspan="7">
            					<h4>暂无数据</h4>
            				</td>
            			</tr>
            		</c:when>
            		<c:otherwise>
            			<c:forEach items="${page.items}" var="pro">
           					<tr>
							<td>${pro.projectName}</td>
							
							<td><fmt:formatNumber value="${pro.sumMoney}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber> </td>
							<td>${pro.month}</td>
							<td>${pro.repayRate}</td>
							<td>${fn:substringBefore(pro.createTime,'.0')}</td>
							<td>${pro.startDate}</td>
							<td>${pro.endDate}</td>
							<td>${pro.state == 1 ? "正常" :"已完成"}</td>
							<td>
							<c:if test="${pro.state == 1 }">
								<a href="/invest/add?proId=${pro.id}">投资</a>
							</c:if>
							</td>
						</tr>
           			</c:forEach>
            		
            		</c:otherwise>
            		
            		</c:choose>
            		
            		
						
            </tbody>
          </table>
           <ul id="pagination" class="pagination"></ul>
          
			

        </div>
        <!-- /.box-body -->
      </div>
      <!-- /.box -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.3.8
    </div>
    <strong>Copyright &copy; 2014-2016 <a href="">凯盛软件</a>.</strong> All rights
    reserved.
  </footer>

<div class="modal fade" id="addModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">新增项目信息</h4>
         <!--  <h3 class="box-title">新增项目信息</h3> -->
        </div>
        <div class="modal-body">
         <!-- form start -->
            <form class="form-horizontal" id="addForm">
              <div class="box-body">
                <div class="form-group">
                  <label class="col-sm-3 control-label">项目名称：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id=" " name="projectName" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">总金额：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" name="sumMoney" id="sumMoney" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">回报率：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                      <input type="text" class="form-control" id="repayrate" name="repayrate" placeholder="">
                  </div>
                </div>
                   <div class="form-group">
                  <label class="col-sm-3 control-label">项目周期：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="months" name="months" placeholder="">
                  </div>
                </div>
                 <div class="form-group">
                  <label class="col-sm-3 control-label">开始日期：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="beginDate" name="beginDate" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">结束日期：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="endDate" name="endDate" placeholder="">
                  </div>
                </div>
              </div>
              <!-- /.box-body -->
              <div class="box-footer">
                 <div class="col-sm-offset-3 col-sm-8">
                    <button type="button" id="addBtn" class="btn btn-primary">新增</button>
                    <button type="reset" class="btn btn-default">重置</button>
                 </div>
              </div>
              <!-- /.box-footer -->
            </form>   
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</div>
<!-- ./wrapper -->
<%@ include file="../include/js.jsp" %>
<script src="/static/js/emp/list.js"></script>
<script src="/static/js/project/add.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="/static/plugins/moment/moment.js"></script>
<script>
$(function(){
	
	  $("#pagination").twbsPagination({
		 totalPages:"${page.pageTotal}",
		 visiblePages:3,
		 href:"/pro/list?page={{number}}&key=${key}&value=${value}",
		 first: "首页",
		 prev: "上一页",
		 next:"下一页",
		 last:"末页"
	 });  
	
	  
	  $("#addPro").click(function(){
		 
		  $("#addModal").modal({
	           show:true,
	           backdrop:'static'
	       });
		  
	  });
	  
	  $("#beginDate").datepicker({
          format: "yyyy-mm-dd",
          language: "zh-CN",
          autoclose: true,
          startDate : moment().format("YYYY-MM-DD")
    }).on('changeDate', function(e) {
  	  //获得周期控件的值
  	  var month = $("#months").val();
  	  var date = moment(e.format('yyyy-mm-dd')).add(month,'months').format("YYYY-MM-DD");
  	  $("#endDate").val(date);
    });
	  
	  //先选择时间控件后输入周期的时候，也要改变结束时间的值
	$("#months").change(function(){
		 //获得周期控件的值
	  	  var month = $("#months").val();
	      var beginDate = $("#beginDate").val();
	      if(beginDate){
		  	  var date = moment(beginDate).add(month,'months').format("YYYY-MM-DD");
		  	  $("#endDate").val(date);
	      }
	});
	  
});



</script>
</body>
</html>
