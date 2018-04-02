<%@ page import="cn.edu.nju.software.models.Member" %>
<%@ page import="java.util.List" %>
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
    Member m = (Member) session.getAttribute("member");
    List records = (List) request.getAttribute("records");
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
                <li><a href="<s:url action="showActivities"/>">购票</a></li>
                <li><a href="<s:url action="manageAccount"/>">账号管理</a></li>
                <li><a href="<s:url action="queryRecord"/>">记录查询</a></li>
                <li><a href="<s:url action="checkOrders"/>">查看订单</a></li>
            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="演唱会">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/index.jsp">退出</a></li>
                <li><a onclick="cancelQualification()">注销</a></li>
            </ul>
            <p class="navbar-text pull-right">欢迎您，<%=m.getName()%>！</p>
        </div>
    </div>
</nav>

<div style="margin-top: 100px">
    <jsp:useBean id="item" class="cn.edu.nju.software.models.Record" scope="page"/>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>订单号</th>
            <th>活动号</th>
            <th>活动名称</th>
            <th>价格</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Object o : records) {
                Record record = (Record) o;
                pageContext.setAttribute("item", record);
        %>
        <tr>
            <td>
                <jsp:getProperty name="item" property="orderid"/>
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
            <td><%
                switch (record.getOperate()) {
                    case 0:
                        out.println("退订");
                        break;
                    case 1:
                        out.println("已支付");
                        break;
                    case 2:
                        out.println("支付返还");
                        break;
                    default:
                        out.println("不清楚欸~");
                }
            %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>


<script src="dist/js/jquery.min.js"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>

</body>
</html>