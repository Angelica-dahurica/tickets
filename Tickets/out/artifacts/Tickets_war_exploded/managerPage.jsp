<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.edu.nju.software.models.Venue" %>
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
    ArrayList venueList = (ArrayList) request.getAttribute("venueList");
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
    <%
        int i = 0;
        for (Object anAlist : venueList) {
            Venue venue = (Venue) anAlist;
            pageContext.setAttribute("item", venue);
            i++;
            if (i % 4 == 0) {
    %>
    <div class="row">
        <%
            }
        %>
        <div class="col-md-3">
            <div class="thumbnail">
                <div class="well">
                    <jsp:useBean id="item" class="cn.edu.nju.software.models.Venue" scope="page"/>
                    <p>场馆地点：
                        <jsp:getProperty name="item" property="place"/>
                    </p>
                    <p>场馆描述：
                        <jsp:getProperty name="item" property="description"/>
                    </p>
                </div>
            </div>
        </div>
        <%
            if (i % 4 == 0) {
        %>
    </div>
    <%
            }
        }
    %>
</div>

<script src="dist/js/jquery.min.js"></script>
<script src="dist/js/bootstrap.min.js"></script>

</body>
</html>
