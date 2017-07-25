<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>凯盛软件CRM-待办事项</title>
    <%@ include file="../base/base-css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <jsp:include page="../base/base-side.jsp">
        <jsp:param name="active" value="_wait"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">待办事项</h3>

                    <div class="box-tools pull-right">
                        <a href="/task/new" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增任务</a>
                        <c:choose>
                            <c:when test="${not (param.show == 'all')}">
                                <a href="/task?show=all" class="btn btn-primary btn-sm"><i class="fa fa-eye"></i> 显示所有任务</a>
                            </c:when>
                            <c:otherwise>
                                <a href="/task" class="btn btn-primary btn-sm"><i class="fa fa-eye"></i> 显示未完成任务</a>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
                <div class="box-body">

                    <ul class="todo-list">
                        <c:forEach items="${IncidentList}" var="task">
                            <li class="${task.state ? 'done' : ''}">
                                <input type="checkbox" class="task_checkbox" ${task.state ? 'checked' : ''} value="${task.id}">
                                <span class="text">${task.name}</span>
                                <c:choose>
                                    <c:when test="${not empty task.customer and not empty task.custId}">
                                        <a href="/customer/my/${task.custId}"><i class="fa fa-user-o"></i> ${task.customer.custName}</a>
                                    </c:when>
                                    <c:when test="${not empty task.sale and not empty task.saleId}">
                                        <a href="/record/my/${task.saleId}"><i class="fa fa-money"></i> ${task.sale.saleName}</a>
                                    </c:when>
                                </c:choose>
                                <small class="label label-danger"><i class="fa fa-clock-o"></i> ${task.endTime}</small>
                                <div class="tools">
                                    <i class="fa fa-edit"></i>
                                    <i class="fa fa-trash-o del_task" rel="${task.id}"></i>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <!-- /.box-body -->
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
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {
        //删除
        $(".del_task").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除吗",function () {
                window.location.href = "/task/"+id+"/del";
            });
        });
        
        //修改状态
        $(".task_checkbox").click(function () {
            var id = $(this).val();
            var checked = $(this)[0].checked;
            if(checked) {
                window.location.href = "/task/"+id+"/state/done";
            } else {
                window.location.href = "/task/"+id+"/state/undone"
            }
        });
    });
</script>

</body>
</html>