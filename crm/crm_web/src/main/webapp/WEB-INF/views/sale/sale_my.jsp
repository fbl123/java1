<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/fun    ctions" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>凯盛软件CRM-首页</title>
    <%@ include file="../base/base-css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <%--<%@include file="../base/base-side.jsp"%>--%>


        <jsp:include page="../base/base-side.jsp">
            <jsp:param name="active" value="sale_my"/>
        </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">我的销售机会</h3>

                    <div class="box-tools pull-right">
                        <a href="/sales/new" class="btn btn-success btn-sm">
                            <i class="fa fa-plus"></i> 添加机会
                        </a>

                    </div>
                </div>

                <div class="box-body">
                    <c:if test="${not empty message}">
                        <div class="alert alert-info">${message}</div>
                    </c:if>
                    <table class="table">
                        <thead>
                        <tr>
                            <td>机会名称</td>
                            <td>关联客户</td>
                            <td>机会价值</td>
                            <td>当前进度</td>
                            <td>最后跟进时间</td>
                            <td>
                                #
                            </td>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${pageInfo.list}" var="sale">
                                <tr rel="${sale.id}">
                                    <td>${sale.saleName}</td>
                                    <td>${sale.customer.custName}</td>
                                    <td>${sale.worth} (万元)</td>
                                    <td>${sale.progress}</td>
                                    <td><fmt:formatDate value="${sale.lastTime}"/></td>
                                    <td></td>
                                </tr>

                            </c:forEach>

                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
                <!-- /.box-body -->
                <c:if test="${pageInfo.pages > 1}" >
                    <div class="box-footer">
                        <ul id="pagination-demo" class="pagination-sm pull-right"></ul>
                    </div>
                </c:if>
            </div>
            <!-- /.box -->


        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@ include file="../base/base-footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="../base/base-js.jsp"%>
<script>
    $(function () {
        <c:if test="${pageInfo.pages > 1}" >
        //分页
        $('#pagination-demo').twbsPagination({
        totalPages: ${pageInfo.pages},
        visiblePages: 7,
        first:'首页',
        last:'末页',
        prev:'上一页',
        next:'下一页',
        href:"?p={{number}}&&keyword=${keyword}"
        });
        </c:if>
    })
</script>

</body>
</html>
