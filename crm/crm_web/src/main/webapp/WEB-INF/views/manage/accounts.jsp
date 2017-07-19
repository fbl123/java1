<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>凯盛软件CRM-首页</title>
    <%@ include file="../base/base-css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/tree/css/metroStyle/metroStyle.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <%@include file="../base/base-side.jsp"%>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-md-2">
                    <div class="box">
                        <div class="box-body">
                            <input type="hidden" id="deptId" value="">
                            <button class="btn btn-default" id="addDeptBtn">添加部门</button>
                            <ul id="ztree" class="ztree"></ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-10">
                    <!-- Default box -->
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">员工管理</h3>
                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool"  title="Collapse" id="addAccountBtn">
                                    <i class="fa fa-plus"></i> 添加员工</button>
                            </div>
                        </div>
                        <div class="box-body">
                            <table class="table" id="accountTable">
                                <thead>
                                <tr>
                                    <th>姓名</th>
                                    <th>部门</th>
                                    <th>手机</th>
                                    <th>#</th>
                                </tr>
                                </thead>
                               <%-- <tbody class="table">
                                    <c:if test="${not empty list}">
                                        <c:forEach items="${list}" var="account">
                                            <tr>
                                                <td>${account.userName}</td>
                                                <td><c:forEach items="${account.deptName}" var="name">
                                                    ${name.deptName}
                                                </c:forEach></td>
                                                <td>${account.mobile}</td>
                                                <td><a href='javascript:;' rel=${account.id} class='delLink'><i class='fa fa-trash text-danger'></i></a></td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </tbody>--%>
                            </table>
                        </div>
                    </div>
                    <!-- /.box -->
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <div class="modal fade" id="addAccountModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加新员工</h4>
                </div>
                <div class="modal-body">
                    <form id="addAccountForm">
                        <div class="form-group">
                            <label>姓名</label>
                            <input type="text" class="form-control" name="userName">
                        </div>
                        <div class="form-group">
                            <label>密码（默认密码123123）</label>
                            <input type="password" class="form-control" name="password" value="123123">
                        </div>
                        <div class="form-group">
                            <label>手机号码</label>
                            <input type="text" class="form-control" name="mobile">
                        </div>
                        <div class="form-group">
                            <label>部门</label>
                            <div id="deptArea"></div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="saveAccountBtn">保存</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->


    <%@ include file="../base/base-footer.jsp"%>


</div>
<!-- ./wrapper -->

<%@include file="../base/base-js.jsp"%>
<script src="/static/plugins/tree/js/jquery.ztree.all.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/validate/jquery.validate.js"></script>
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script>
    $(function(){
        var dataTable = $("#accountTable").DataTable({
            paging: false,
            "ordering": false,
            "searching": false,
            "processing": true,
            "serverSide": true,
            "ajax": {
                url : "/acc/load.json",
                data : function(data) {
                    data.deptId = $("#deptId").val();
                }
            },
            "columns":[
                {"data":"userName"},
                {"data":function (row) {
                    var result = "";
                    for(var i = 0;i < row.deptName.length;i++) {
                        result += row.deptName[i].deptName + " &nbsp;&nbsp;";
                    }
                    return result;
                }},
                {"data":"mobile"},
                {"data":function (row) {
                    return "<a href='javascript:;' rel='"+row.id+"' class='delLink'><i class='fa fa-trash text-danger'></i></a>";
                }}
            ],
            language:{
                "info":  "显示 _START_ 到 _END_ 共 _TOTAL_ 条数据",
            }
        });


        $(document).delegate(".delLink","click",function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除吗?",function (index) {
                layer.close(index);
                $.post("/acc/del?id="+id).done(function(data){
                    if(data.state == "success") {
                        layer.msg("删除成功");
                        dataTable.ajax.reload();
                    } else {
                        layer.msg(data.message);
                    }
                }).error(function(){
                    layer.msg("服务器异常");
                });
            });
        });







        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            async:{
                enable:true,
                url:"/manage/account/depts.json"
            },
            callback:{
                onClick:function(event,treeId,treeNode,clickFlag){
//                    alert(treeNode.id + treeNode.name + treeNode.pId);
                    $("#deptId").val(treeNode.id);
                    dataTable.ajax.reload();
                }
            }
        };

       var tree = $.fn.zTree.init($("#ztree"), setting);


        //添加部门
        $("#addDeptBtn").click(function () {
            layer.prompt({"title":"请输入部门名称"},function(text,index){
                layer.close(index);//关闭对话框
                $.post("/manage/account/dept/new",{"deptName":text,"pId":10000}).done(function(data){
                    if(data.state == "success") {
                        layer.msg("添加成功");
                        tree.reAsyncChildNodes(null, "refresh");
                    } else {
                        layer.msg(data.message);
                    }
                }).error(function(){
                    layer.msg("服务器异常");
                });
            });
        });

        //添加员工

        $("#addAccountBtn").click(function () {

            $("#deptArea").html("");
            //加载部门
            $.ajax({
                url:'/manage/account/depts.json',
                type:'post',
                success:function(data){
                    if(data.length<=1){
                        layer.msg("清先创建部门")
                        return;
                    }
                    for(var i=0;i<data.length;i++){
                        var obj=data[i];
                        if(obj.id!=10000){
                            var html='<label class="chackbox-inline"><input type="checkbox" name="deptId" value="'+obj.id+'">'+obj.name+'</label>';
                            $(html).appendTo($("#deptArea"));
                            /*显示模态框*/
                            $("#addAccountModal").modal({
                                show:true,
                                backdrop:'static'
                            });
                        }

                    }

                },
                error:function(){
                    layer.alert("服务器异常");
                }


            })

        })
        $("#saveAccountBtn").click(function () {
            $("#addAccountForm").submit();
        })
        $("#addAccountForm").validate(  {
            errorClass:"text-danger",
            errorElement:"span",
            rules:{
                userName:{
                    required:true
                },
                password:{
                    required:true
                },
                mobile:{
                    required:true
                },
                deptId:{
                    required:true
                }
            },
            messages:{
                userName:{
                    required:"请输入账号"
                },
                password:{
                    required:"请输入密码"
                },
                mobile:{
                    required:"请输入手机号码"
                },
                deptId:{
                    required:"至少选择一个部门"
                }
            },

            submitHandler:function () {
                
                $.ajax({
                    url:"/acc/add",
                    type:"post",
                    data:$("#addAccountForm").serialize(),
                    success:function(data){
                        if(data.state=='success'){
                            //重置表单
                            $("#addAccountForm")[0].reset();
                            //隐藏模态框
                            $("#addAccountModal").modal('hide');
                            layer.msg("添加成功");
                            dataTable.ajax.reload();
                        }else{
                            layer.msg(data.message);
                        }



                    },
                    error:function () {
                        layer.alert("服务器异常")
                    }
                })
            }



        })

    });


</script>
</body>
</html>