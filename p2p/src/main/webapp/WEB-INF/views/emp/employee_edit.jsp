<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>修改员工信息</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
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

      <!-- Default box -->
              
      <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">修改员工信息</h3>
            </div>
            <!-- /.box-header -->
           	<c:if test="${not empty message }">
           		<div class="alert alert-danger">${message}</div>	
           	</c:if>
            
            	
            
            <!-- form start -->
            <form class="form-horizontal" id="editForm">
              <div class="box-body">
                <div class="form-group">
                  <label class="col-sm-2 control-label">员工姓名：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="hidden" value="${emp.id }" name="id"/>
                    <input type="text" disabled class="form-control" value="${emp.realName }" id="userName" name="userName" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">手机号码：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" disabled class="form-control" value="${emp.tel}" id="tel" name="tel" placeholder="">
                  </div>
                </div>
               
               <div class="form-group">
                  <label class="col-sm-2 control-label">状态：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                      <select name="state" id="state" class="form-control">
                          <option value="0">禁用</option>
                          <option value="1">正常</option>
                          <option value="2">停薪留职</option>
                      </select>
                  </div>
                </div>
               
               
                <div class="form-group">
                  <label class="col-sm-2 control-label">所属公司：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                      <select name="company" id="company" class="form-control">
                          <option value="">--请选择公司--</option>
                         <c:forEach items="${comList}" var="com">
		                      <option value="${com.id}">${com.name}</option>
		                 </c:forEach>
                      </select>
                  </div>
                  <div class="col-sm-2">
                      <a href="#">新增公司</a>
                  </div>
                </div>
                 <div class="form-group">
                  <label class="col-sm-2 control-label">所属部门：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <select name="dept" id="dept" class="form-control">
                      <option value="">--请选择部门--</option>
                      <c:forEach items="${deptList}" var="deptName">
                      <option value="${deptName}">${deptName}</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                
            
              </div>
              <!-- /.box-body -->
              <div class="box-footer">
                 <div class="col-sm-offset-2 col-sm-8">
                    <button type="button" id="editBtn" class="btn btn-primary">修改</button>
                    <button type="reset" class="btn btn-default">重置</button>
                 </div>
              </div>
              <!-- /.box-footer -->
            </form>        
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
</div>
<!-- ./wrapper -->

<%@ include file="../include/js.jsp" %>
<script src="/static/js/emp/edit.js"></script>
<script>
  $(function(){
	$("#company").val("${emp.companyId}");
	$("#dept").val("${emp.deptName}");
	$("#state").val("${emp.state}");
    
  });


</script>
</html>
    