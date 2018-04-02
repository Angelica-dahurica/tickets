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
<div class="alert alert-success" role="alert">
    相关信息已经提交系统经理进行审核。您的识别码为<%=request.getAttribute("venueid")%>，审核通过后即可通过此识别码及密码登录。
    点击<a href="<%=request.getContextPath()%>/start.action">这里</a>跳回首页
</div>
</body>
</html>