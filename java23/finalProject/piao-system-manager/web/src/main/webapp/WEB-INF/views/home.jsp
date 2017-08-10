<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>综合管理系统-首页</title>
    <%@ include file="base/base-css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <jsp:include page="base/base-side.jsp">
        <jsp:param name="active" value="home"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

           <!-- DIY -->
            Hello, <shiro:principal property="name"/>
            <hr>
            <shiro:hasRole name="综合办公">
                <h3>综合办公</h3>
            </shiro:hasRole>
            <shiro:hasRole name="管理员">
                Hello,Admin
            </shiro:hasRole>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@ include file="base/base-footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="base/base-js.jsp"%>

</body>
</html>
