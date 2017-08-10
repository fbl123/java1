<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>查询投资信息</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@ include file="../include/css.jsp" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
  <%@ include file="../include/header.jsp" %>
<%@ include file="../include/sider.jsp" %>
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
          <form action="/invest/list" class="form-inline">
             <select name="key" id="key" class="form-control">
                <option value="">--请选择查询条件--</option>
                <!-- 
                 <option value="cust.name">客户姓名</option>
                 <option value="pro.projectName">项目名称</option>
                  -->
                <option value="name">客户姓名</option>
                <option value="projectName">项目名称</option>
              </select>
            <input type="text" value="${value}" class="form-control" name="value" id="value" placeholder="关键字"/>
            <button class="btn btn-primary">搜索</button>
          </form>
        </div>

      </div>
      <!-- Default box -->
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
	        <h5 class="pull-left">投资信息列表</h5>
	        <a href="/invest/add" class="btn btn-success pull-right">新增投资</a>
        </div>
        <div class="box-body">
          
          <table class="table" id="cust_table">
            <thead>
            <tr>
			    <th>客户名称</th>
				<th>项目名称</th>
				<th>投资金额</th>
				<th>回报率</th>
				<th>期限(月)</th>
				<th>签订日期</th>
				<th>到期日期</th>
				<th>业务员</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
            </thead>
            <tbody>
            	<c:forEach items="${page.items}" var="invest">
						<tr>
							<td>${invest.name}</td>
							<td>${invest.projectName}</td>
							<td>${invest.investMoney}</td>
							<td>${invest.rate}</td>
							<td>${invest.month}</td>
							<td>${invest.signDate}</td>
							<td>${invest.endDate}</td>
							<td>${invest.realName}</td>
							<c:choose>
								<c:when test="${invest.state == 1}"><td>正常</td></c:when>
								<c:when test="${invest.state == 2}"><td>已解约</td></c:when>
								<c:otherwise>
									<td>已结束</td>
								</c:otherwise>
							</c:choose>
							<!--  根据当前状态显示操作 -->
							<c:choose>
							<c:when test="${invest.state == 1}">
								<td>
									<a href="javascript:;" rel="${invest.id}" class="del">删除</a>
									<a href="javascript:;" rel="${invest.id}" class="unuse">解约</a>
									<a href="javascript:;" rel="${invest.id}" class="concat">续约</a>
								</td>
							</c:when>
							<c:otherwise>
								<td>-</td>
							</c:otherwise>
							</c:choose>
						</tr>
				</c:forEach>
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

<div class="modal fade" id="concatModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">续约</h4>
        </div>
        <div class="modal-body">
          <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">续约投资信息</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal" id="concatForm">
              <div class="box-body">
                <div class="form-group">
                  <label class="col-sm-3 control-label">客户名称：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
	                  <input type="hidden" class="form-control" id="cust" name="cust" placeholder="">
	                  <input type="text" readonly class="form-control" id="custName" name="custName" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">项目名称：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                  		<input type="hidden" class="form-control" id="pro" name="pro" placeholder="">
	                    <input type="text" readonly class="form-control" id="proName" name="proName" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">剩余金额：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" readonly class="form-control" id="remainMoney" name="remainMoney" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">投资金额：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" readonly class="form-control" id="investMoney"  name="investMoney" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">回报率：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                      <input type="text" readonly class="form-control" id="rate" name="rate"  placeholder="">
                  </div>
                </div>
                   <div class="form-group">
                  <label class="col-sm-3 control-label">期限：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" readonly class="form-control" id="month" name="month"  placeholder="">
                  </div>
                </div>
                 <div class="form-group">
                  <label class="col-sm-3 readonly control-label">签订日期：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" readonly class="form-control" name="signDate"  id="signDate" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3  control-label">到期日期：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" readonly class="form-control" id="endDate" name="endDate" placeholder="">
                  </div>
                </div>
                
              </div>
              <!-- /.box-body -->
              <div class="box-footer">
                 <div class="col-sm-offset-3 col-sm-8">
                    <button type="button" id="concatBtn" class="btn btn-primary">续约</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                 </div>
              </div>
              <!-- /.box-footer -->
            </form>        
      </div>
      <!-- /.box -->
          
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</div>
<!-- ./wrapper -->
<%@ include file="../include/js.jsp" %>
<script src="/static/js/invest/list.js"></script>
<script src="/static/plugins/moment/moment.js"></script>
<script>
$(function(){
	$("#key").val("${key}");
	  $("#pagination").twbsPagination({
		 totalPages:"${page.pageTotal}",
		 visiblePages:3,
		 href:"/invest/list?page={{number}}&key=${key}&value=" + encodeURIComponent("${value}"),
		 first: "首页",
		 prev: "上一页",
		 next:"下一页",
		 last:"末页"
	 });  
	
});
</script>
</body>
</html>
    