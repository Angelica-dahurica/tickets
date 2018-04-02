<%@ page import="cn.edu.nju.software.models.Activity" %>
<%@ page import="cn.edu.nju.software.models.Member" %>
<%@ page import="cn.edu.nju.software.models.Seat" %>
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
    Activity activity = (Activity) request.getAttribute("activity");
    session.setAttribute("activity", activity);
    Venue venue = (Venue) request.getAttribute("venue");
    ArrayList seats = (ArrayList) request.getAttribute("seats");
    Member m = (Member) session.getAttribute("member");
    pageContext.setAttribute("item", activity);
    pageContext.setAttribute("v", venue);
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

<div style="padding-top: 50px;">
    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="#">所有活动</a></li>
        <li role="presentation"><a href="#">音乐会</a></li>
        <li role="presentation"><a href="#">舞蹈</a></li>
        <li role="presentation"><a href="#">戏剧</a></li>
        <li role="presentation"><a href="#">体育赛事</a></li>
    </ul>
</div>

<div style="margin-top: 100px">
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
            <jsp:useBean id="v" class="cn.edu.nju.software.models.Venue" scope="page"/>
            <p>场馆地点：
                <jsp:getProperty name="v" property="place"/>
            </p>
            <p>场馆描述：
                <jsp:getProperty name="v" property="description"/>
            </p>
            <p>
                <a data-toggle="modal" data-target="#reverseModal">
                    <button class="btn btn-default btn-block" value="<%=item.getActivityid()%>"
                        >预定
                    </button>
                </a>
            </p>
        </div>
    </div>

</div>

<div class="modal fade" id="reverseModal" tabindex="-1" role="dialog" aria-labelledby="reverseModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                选择类型
            </div>
            <form action="<s:url action="bookTickets"/>" method="post" class="form-horizontal">
                <div class="modal-body">
                    <ul class="list-inline" style="width:560px; height: 36px;" id="unorderlist">
                    <%
                        int i = 0;
                        for (Object anAlist : seats) {
                            Seat seat = (Seat) anAlist;
                            pageContext.setAttribute("s", seat);
                            i++;
                    %>
                    <jsp:useBean id="s" class="cn.edu.nju.software.models.Seat" scope="page"/>
                        <li>
                            <input type="button" onclick="add(this, <jsp:getProperty name="s" property="price"/>, <%=i%>)"
                                   value="&nbsp;&nbsp;&nbsp;<jsp:getProperty name="s" property="price"/> （<%
                            switch (s.getType()){
                                case 0: out.print("内场");  break;
                                case 1: out.print("看台");  break;
                                default:    break;
                            }%>）">
                        </li>
                    <%
                        }
                    %>
                    </ul>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <input type="submit" class="btn btn-primary" value="购买">
                </div>
            </form>
        </div>
    </div>
</div>

<script src="dist/js/jquery.min.js"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript">

    function add(obj, price, i) {
        obj.disabled = true;
        document.getElementById("unorderlist").insertAdjacentHTML("afterend",
            '<div class="input-group">' +
            '<label class="input-group-addon">已选：</label>' +
            '<input type="text" class="form-control" name="price" value="' + price + '">' +
            '<span class="input-group-btn">' +
            '<input class="btn btn-default" type="button" value="-" onclick="numDec(' + i + ')">' +
            '</span>' +
            '<input type="text" value="0" class="form-control" name="quantity" id="quantity' + i + '">' +
            '<span class="input-group-btn">' +
            '<input class="btn btn-default" type="button" value="+" onclick="numAdd('+ i + ')" >' +
            '</span>' +
            '</div>');

    }

    function numAdd(i) {
        var quantity = document.getElementById("quantity" + i).value;
        if(parseInt(quantity) < 20) {
            document.getElementById("quantity" + i).value = parseInt(quantity) + 1;
        }
    }

    function numDec(i){
        var quantity = document.getElementById("quantity" + i).value;
        if(parseInt(quantity) > 0){
            document.getElementById("quantity" + i).value = parseInt(quantity) - 1;
        }
    }

</script>
</body>
</html>
