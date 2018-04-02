<%@ page import="cn.edu.nju.software.models.Member" %>
<%@ page import="cn.edu.nju.software.models.OrderInfo" %>
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
    ArrayList orderInfos = (ArrayList) request.getAttribute("orderInfos");
    Member m = (Member) session.getAttribute("member");
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
    <jsp:useBean id="item" class="cn.edu.nju.software.models.OrderInfo" scope="page"/>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>订单号</th>
            <th>订单时间</th>
            <th>活动名称</th>
            <th>类型</th>
            <th>数量</th>
            <th>价格</th>
            <th>订单状态</th>
            <th></th>
            <th>退订</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Object o : orderInfos) {
                OrderInfo order = (OrderInfo) o;
                pageContext.setAttribute("item", order);
        %>
        <tr>
            <td>
                <jsp:getProperty name="item" property="orderid"/>
            </td>
            <td>
                <jsp:getProperty name="item" property="booktime"/>
            </td>
            <td>
                <jsp:getProperty name="item" property="activityname"/>
            </td>
            <td>
                <jsp:getProperty name="item" property="type"/>
            </td>
            <td>
                <jsp:getProperty name="item" property="quantity"/>
            </td>
            <td>
                <jsp:getProperty name="item" property="price"/>
            </td>
            <td><%
                switch (order.getStatus()) {
                    case 0:
                        out.println("已支付");
                        break;
                    case 1:
                        out.println("待支付");
                        break;
                    case 2:
                        out.println("已取消");
                        break;
                    case 3:
                        out.println("已退订");
                        break;
                    default:
                        out.println("已过期");
                }
            %></td>
            <td><%
                if (order.getStatus() == 1) {
            %>
                <p>
                    <button class="btn btn-default" value="<jsp:getProperty name="item" property="orderid"/>"
                            onclick="pay(this.value)">支付
                    </button>
                </p>
                <%
                    }
                %></td>
            <td><%
                if (order.getStatus() == 0) {
            %>
                <p>
                    <button class="btn btn-default" value="<jsp:getProperty name="item" property="orderid"/>"
                            onclick="quit(this.value)">退订
                    </button>
                </p>
                <%
                    }
                %>
            </td>
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

    function pay(orderid) {
        window.location.href = "/pay.action?orderid=" + orderid;
    }

    function quit(orderid) {
        $.post("/quit.action", {orderid: orderid}, function (data) {
            alert("退订成功！");
        });
    }

</script>
</body>
</html>
