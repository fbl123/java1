<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>查询分公司信息</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<%@include file="../include/css.jsp" %>
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
						<form action="/com/list" class="form-inline">
							<select name="key" id="key" class="form-control">
								<option value="">--请选择--</option>
								<option value="companyname">公司名称</option>
								<option value="tel">电话</option>
								<option value="city">区域</option>
							</select> <input type="text" value="${value}" class="form-control" name="value" id="value"
								placeholder="关键字" />
							<button type="submit" class="btn btn-primary">搜索</button>
						</form>
					</div>

				</div>
				<!-- Default box -->
				<div class="box box-solid box-primary">
				 <div class="box-header with-border">
			         <h5 class="pull-left">查询公司信息</h5>
         			<a href="/com/add" class="btn btn-success pull-right">新增公司</a>
				 </div>
				
					<div class="box-body">

						<table class="table" id="cust_table">
							<thead>
								<tr>
									<th>公司名称</th>
									<th>电话</th>
									<th>地址</th>
									<th>区域</th>
									<th>备注</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${companyPage.items}" var="com">
								
									<tr>
									<td>${com.name}</td>
									<td>${com.tel}</td>
									<td>${com.address}</td>
									<td>${com.city}</td>
									<td>${com.remark}</td>
									<td><a href="javascript:;" rel="${com.id}" class="del">删除</a> 
								</tr>
								</c:forEach>
								
							</tbody>
						</table>
					 <div class="pagination pagination-mini pagination-centered">
			            <ul id="pagination" style="margin-bottom:20px;"></ul>
			          </div>

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
			<strong>Copyright &copy; 2014-2016 <a href="">凯盛软件</a>.
			</strong> All rights reserved.
		</footer>

	</div>
	<!-- ./wrapper -->
	<%@ include file="../include/js.jsp" %>
	<script src="/static/js/company/list.js"></script>
	<script>
		$(function() {
			$("#key").val("${key}");
			
			$("#pagination").twbsPagination({
	            totalPages:"${companyPage.pageTotal}",
	            visiblePages:5,
	            first:'首页',
	            last:'末页',
	            prev:'上一页',
	            next:'下一页',
	            href: '/com/list?p={{number}}'
	        });
		});
	</script>
</body>
</html>
