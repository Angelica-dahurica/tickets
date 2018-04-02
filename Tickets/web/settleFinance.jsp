<%@ page import="java.util.List" %>
<%@ page import="cn.edu.nju.software.models.Activity" %>
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
    List activities = (List) request.getAttribute("activities");
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
                <li><a href="<s:url action="systemStatistics"/>">系统统计</a></li>
                <li><a href="<s:url action="checkInfo"/>">审批信息</a></li>
                <li><a href="<s:url action="settleFinance"/>">财务结算</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/index.jsp">退出</a></li>
            </ul>
        </div>
    </div>
</nav>

<div style="margin-top: 100px">
    <jsp:useBean id="item" class="cn.edu.nju.software.models.Activity" scope="page"/>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>活动号</th>
            <th>活动名称</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Object o : activities) {
                Activity activity = (Activity) o;
                pageContext.setAttribute("item", activity);
        %>
        <tr>
            <td>
                <jsp:getProperty name="item" property="activityid"/>
            </td>
            <td>
                <jsp:getProperty name="item" property="name"/>
            </td>
            <td><p><button class="btn btn-default" value="<jsp:getProperty name="item" property="activityid"/>"
                            onclick="settle(this.value)">结算
                    </button>
                </p>
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
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript">

    function settle(activityid) {
        $.post("/settle.action", {activityid: activityid}, function(){
                alert("结算成功！");
            });
        return false;
    }

</script>

</body>
</html>