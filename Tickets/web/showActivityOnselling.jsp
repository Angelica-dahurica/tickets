<%@ page import="cn.edu.nju.software.models.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%!
    private Ticket find(List tickets, int i, int j, double price) {
        for(Object o : tickets) {
            Ticket t = (Ticket) o;
            if(t.getRow() == i && t.getCol() == j && t.getPrice() == price) {
                return t;
            }
        }
        return null;
    }
        %>
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
    ArrayList tickets = (ArrayList) request.getAttribute("tickets");
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
                    <button class="btn btn-default btn-block" value="<%=item.getActivityid()%>">
                        在线选座
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
            <form action="<s:url action="onsellTickets"/>" method="post" class="form-horizontal">

            <div class="modal-body">
                <div>
                    <img class="img-responsive" alt="" src="img/screen.png">
                </div>

                <jsp:useBean id="seat" class="cn.edu.nju.software.models.Seat" scope="page"/>
                <%
                    int total = 0;
                    for(Object o: seats) {
                        seat = (Seat) o;

                %>
                <label class="col-sm-12 control-label">
                    票价：<%=seat.getPrice()%>
                    类型：<%
                    switch (seat.getType()) {
                        case 0:
                            out.println("内场");
                            break;
                        case 1:
                            out.println("看台");
                            break;
                        default:
                            out.println("找不到了");
                    }
                    %>
                </label>

                <%
                    int row = seat.getNum()%20==0 ? seat.getNum()/20 : seat.getNum()/20+1;
                    int seatnum = seat.getNum();
                    for(int i = 1; i <= row; i++){
                %>
                <ul class="list-inline">
                    <%
                        for(int j = 1; j <= 20 && seatnum > 0; j++){
                    %>
                    <li value="<%=(i-1)*20+j%>" onclick="chooseSeat(<%=total%>, this.value,
                    <%=seat.getPrice()%>,
                    <jsp:getProperty name="item" property="activityid"/>)">
                        <%
                            Ticket t = find(tickets, i, j, seat.getPrice());
                            if(t != null) {
                                if(t.getStatus() == 0){ //售出
                        %>
                        <div>
                            <img id="<%=(i-1)*20+j+total%>" class="img-responsive" alt="" src="img/seat2.png" style="width: 14px">
                        </div>
                        <%
                                }else if(t.getStatus() == 1 && t.getLockperson().equals(m.getEmail())){  //锁定且是自己锁定的
                        %>
                        <div>
                            <img id="<%=(i-1)*20+j+total%>" class="img-responsive" alt="" src="img/seat4.png" style="width: 14px">
                        </div>
                        <%
                                } else if(t.getStatus() == 1) { //别人锁定
                                %>
                        <div>
                            <img id="<%=(i-1)*20+j+total%>" class="img-responsive" alt="" src="img/seat3.png" style="width: 14px">
                        </div>
                        <%
                                } else {
                                %>
                        <div>
                            <img id="<%=(i-1)*20+j+total%>" class="img-responsive" alt="" src="img/seat1.png" style="width: 14px">
                        </div>
                        <%
                                }
                        %>
                    </li>
                    <%
                                seatnum--;
                                }
                            }
                    %>
                </ul>
                <%
                        }
                        total += seat.getNum();
                    }
                %>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="payment-btn" class="btn btn-primary">付款</button>
            </div>
            </form>
        </div>
    </div>
</div>

<script src="dist/js/jquery.min.js"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript">
    function chooseSeat(total, seatnum, price, activityid){
        var id = "#"+(seatnum+total);
        if($(id).attr("src")=="img/seat1.png"){
            $.post("/lockSeat.action",
                {
                    row: seatnum%20==0 ? seatnum/20 : seatnum/20+1,
                    col: seatnum%20==0 ? 20 : seatnum%20,
                    price: price,
                    activityid: activityid
                },
                function(data, status){
                    $(id).attr("src","img/seat4.png")
                });
        }else if($(id).attr("src")=="img/seat4.png"){
            $.post("/unlockSeat.action",
                {
                    row: seatnum%20==0 ? seatnum/20 :seatnum/20+1,
                    col: seatnum%20==0 ? 20 : seatnum%20,
                    price: price,
                    activityid: activityid
                },
                function(data, status){
                    $(id).attr("src","img/seat1.png")
                });
        }
    }
</script>
</body>
</html>
