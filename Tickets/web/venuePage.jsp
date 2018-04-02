<%@ page import="cn.edu.nju.software.models.Activity" %>
<%@ page import="cn.edu.nju.software.models.Venue" %>
<%@ page import="java.util.ArrayList" %>
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
    Venue venue = (Venue) session.getAttribute("venue");
    ArrayList activityList = (ArrayList) request.getAttribute("activities");
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
                <li class="active"><a href="<s:url action="venueStatistics"/>">场馆统计<span class="sr-only">(current)</span></a></li>
                <li><a href="<s:url action="planActivities"/>">发布计划</a></li>
                <li><a href="<s:url action="manageVenue"/>">场馆管理</a></li>
                <li><a href="<s:url action="checkTickets"/>">检票登记</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/index.jsp">退出</a></li>
            </ul>
            <p class="navbar-text pull-right">欢迎您，<%=venue.getPlace()%>！</p>
        </div>
    </div>
</nav>

<div style="padding-top: 50px;">
    <ul class="nav nav-tabs">
        <li role="presentation"><a href="<s:url action="venueStatistics"/>">已发布的活动</a></li>
        <li role="presentation"><a href="<s:url action="venueRecord"/>">财务情况</a></li>
    </ul>
</div>

<div style="margin-top: 100px">
    <%
        int i = 0;
        for (Object anAlist : activityList) {
            Activity activity = (Activity) anAlist;
            pageContext.setAttribute("item", activity);
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
                    <jsp:useBean id="item" class="cn.edu.nju.software.models.Activity" scope="page"/>
                    <p>活动名称：
                        <jsp:getProperty name="item" property="name"/>
                    </p>
                    <p>活动时间：
                        <jsp:getProperty name="item" property="time"/>
                    </p>
                    <p>活动类型：
                        <%
                            switch (item.getType()) {
                                case 0:
                                    out.println("音乐会");
                                    break;
                                case 1:
                                    out.println("舞蹈");
                                    break;
                                case 2:
                                    out.println("戏剧");
                                    break;
                                case 3:
                                    out.println("体育赛事");
                                    break;
                                default:
                                    out.println("普通活动");
                            }
                        %>
                    </p>
                    <p>活动描述：
                        <jsp:getProperty name="item" property="description"/>
                    </p>
                    <p>活动状态：
                        <%
                            switch (item.getStatus()) {
                                case 0:
                                    out.println("预售");
                                    break;
                                case 1:
                                    out.println("售票中");
                                    break;
                                case 2:
                                    out.println("售后");
                                    break;
                                default:
                                    out.println("找不到了");
                            }
                        %>
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
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
</body>
</html>