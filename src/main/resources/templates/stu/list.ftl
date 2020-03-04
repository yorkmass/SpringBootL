<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="${request.contextPath}/js/jquery-2.0.3.min.js"></script>
    <script>
        $(function(){
            $("#itimg").click(function () {
                alert("我的头像");
            });
        });
    </script>
</head>
<body>
欢迎《${username}》
<#if (age<18)>-小朋友
    <#elseif (age <30)>帅哥/美女
    <#else >大叔
</#if>

登录系统<br>
学生列表
<table border="1">
    <tr>
        <td>ID</td>
        <td>名字</td>
        <td>性别</td>
    </tr>
    <#list stuList?sort_by("id") as stu>
        <tr>
            <td>${stu.id}</td>
            <td>${stu.username}</td>
            <td>${stu.gender}</td>
        </tr>
    </#list>
</table>
<img id="itimg" src="${request.contextPath}/img/1.jpg" width="200px" height="200px">
</body>
</html>