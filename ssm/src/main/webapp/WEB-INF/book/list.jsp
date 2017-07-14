<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="/static/bootstrap/css/bootstrap.css" rel="stylesheet">
    <title>Document</title>
</head>
<body>
    <div class="container">
        <table class="table">
            <thead>
              <tr>
                <th>书名</th>
                <th>作者</th>
            </tr>
            </thead>
            <c:forEach items="${bookList}" var="book">
                <tr>
                    <td>${book.bookname}</td>
                    <td>${book.author}</td>
                </tr>


            </c:forEach>

        </table>

        <button id="button">添加书籍</button>

    </div>

    <script src="/static/js/jquery.js"></script>
    <script src="/static/bootstrap/js/bootstrap.js"></script>
    <script src="/static/layer/layer.js"></script>
    <script>
        $(function(){
            $("#button").click(function(){

                location.href = "/book/add";
//                $.ajax({
//                    url : "/book/josn",
//                    type : "get",
//                    success : function(data){
//                        console.log(data);
//                        if(data.state == "success"){
//
//                            layer.alert("成功");
//                        }
//                    },
//                    error : function () {
//                        alert("服务器繁忙")
//                    }
//                })
            })
        })


    </script>
</body>


</html>