<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <title>Document</title>
</head>
<body>
<div class="container">

    <form id="addform">
        <div class="form-group">
            <label>书籍名称</label>
            <input type="text" class="form-control" name="bookname">
        </div>
        <div class="form-group">
            <label>作者</label>
            <input type="text" class="form-control" name="author">
        </div>
        <div class="form-group">
            <label>价格</label>
            <input type="text" class="form-control" name="publish">
        </div>
        <div class="form-group">
            <label>数量</label>
            <input type="text" class="form-control" name="number">
        </div>
        <div class="form-group">
            <label>剩余数量</label>
            <input type="text" class="form-control" name="nowNumber">
        </div>
        <button class="btn btn-primary" id="button" type="button">添加</button>
    </form>

</div>
            <script src="/static/js/jquery.js"></script>
            <script src="/static/bootstrap/js/bootstrap.js"></script>
            <script src="/static/layer/layer.js"></script>
            <script>
                $(function(){
                    $("#button").click(function(){

                        $.ajax({
                            url : "/book/add",
                            type :"post",
                            data : $("#addform").serialize(),
                            success :function(data){
                                console.log(data);
                                if( data.state == "success"){
                                    layer.alert("添加成功",function () {
                                        location.href="/book"
                                    });

                                }else {
                                    layer.alert("失败")
                                }
                            },
                            error:function () {
                                layer.alert("服务器有误")
                            }




                        })



                    })




                })


            </script>
</body>
</html>