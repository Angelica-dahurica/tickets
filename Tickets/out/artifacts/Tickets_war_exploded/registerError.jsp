<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tickets</title>
    <link href="./dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="alert alert-danger" role="alert">
    验证失败，请重新检查验证码，并再次注册。3s后跳回首页，如果没有跳转请点击
    <a href="<%=request.getContextPath()%>/start.action">这里</a>
</div>
<% response.setHeader("refresh", "3;URL="+request.getContextPath()+"/start.action"); %>
</body>
</html>