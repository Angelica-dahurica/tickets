<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.edu.nju.software.models.Record" %>
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

<%
    ArrayList recordsMinus = (ArrayList) request.getAttribute("recordsMinus");
    ArrayList recordsAdd = (ArrayList) request.getAttribute("recordsAdd");
%>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <img alt="Brand" src="img/brand.jpg" style="height: 20px; border-radius: 5px; margin-bottom: 15px">
            </a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="<s:url action="systemStatistics"/>">网站统计<span
                        class="sr-only">(current)</span></a></li>
                <li><a href="<s:url action="checkInfo"/>">审批信息</a></li>
                <li><a href="<s:url action="settleFinance"/>">财务结算</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/index.jsp">退出</a></li>
            </ul>
        </div>
    </div>
</nav>

<div style="padding-top: 50px;">
    <ul class="nav nav-tabs">
        <li role="presentation"><a href="<s:url action="systemStatistics"/>">场馆统计</a></li>
        <li role="presentation"><a href="<s:url action="memberStatistics"/>">会员统计</a></li>
        <li role="presentation"><a href="<s:url action="financeStatistics"/>">财务状况</a></li>
    </ul>
</div>

<div style="margin-top: 100px">
    <jsp:useBean id="item" class="cn.edu.nju.software.models.Record" scope="page"/>
    <jsp:useBean id="re" class="cn.edu.nju.software.models.Record" scope="page"/>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>收支</th>
            <th>活动号</th>
            <th>活动名称</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Object o : recordsMinus) {
                Record record = (Record) o;
                pageContext.setAttribute("item", record);
        %>
        <tr>
            <td>
                收入
            </td>
            <td>
                <jsp:getProperty name="item" property="activityid"/>
            </td>
            <td>
                <jsp:getProperty name="item" property="activityname"/>
            </td>
            <td>
                <jsp:getProperty name="item" property="price"/>
            </td>
            <td>
        </tr>
        <%
            }
        %>
        <%
            for (Object o : recordsAdd) {
                Record record = (Record) o;
                pageContext.setAttribute("re", record);
        %>
        <tr>
            <td>
                支出
            </td>
            <td>
                <jsp:getProperty name="re" property="activityid"/>
            </td>
            <td>
                <jsp:getProperty name="re" property="activityname"/>
            </td>
            <td>
                <jsp:getProperty name="re" property="price"/>
            </td>
            <td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>


<script src="dist/js/jquery.min.js"></script>
<script src="dist/js/bootstrap.min.js"></script>

</body>
</html>
