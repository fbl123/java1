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
    <%@include file="../base/base-side.jsp"%>

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">添加销售机会</h3>

                    <%--<div class="box-tools pull-right">--%>
                        <%--<button type="button" class="btn btn-box-tool">--%>
                            <%--<i class="fa fa-plus"></i> 添加机会--%>
                        <%--</button>--%>
                    <%--</div>--%>
                </div>
                <div class="box-body">
                    <form  method="post">
                        <div class="form-group">
                            <label>机会名称</label>
                            <input type="text" class="form-control" name="saleName">
                        </div>
                        <div class="form-group">
                            <label>关联客户</label>
                            <select name="customerId" id="" class="form-control">
                                <option value=""></option>
                                <c:forEach var="customer" items="${customerList}">
                                    <option value="${customer.id}">${customer.custName}</option>
                                </c:forEach>

                            </select>
                        </div>
                        <div class="form-group">
                            <label>机会价值</label>
                            <input type="text" class="form-control" name="worth">
                        </div>
                        <div class="form-group">
                            <label>当前进度</label>
                            <select name="progress" class="form-control">
                                <option value=""></option>
                                <c:forEach items="${progressList}" var="progress">
                                    <option value="${progress}">${progress}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>详细内容</label>
                            <textarea name="description" class="form-control"></textarea>
                        </div>
                        <button class="btn btn-primary">保存</button>
                    </form>
                </div>
                <!-- /.box-body -->
                <%--<div class="box-footer">--%>
                    <%--<button class="btn btn-primary">保存</button>--%>
                <%--</div>--%>
            </div>
            <!-- /.box -->
           <!-- DIY -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@ include file="../base/base-footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="../base/base-js.jsp"%>

</body>
</html>
