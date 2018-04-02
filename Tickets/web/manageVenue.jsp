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
    Venue venue = (Venue)session.getAttribute("venue");
    pageContext.setAttribute("item", venue);
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
                <li><a href="<s:url action="venueStatistics"/>">场馆统计</a></li>
                <li><a href="<s:url action="planActivities"/>">发布计划</a></li>
                <li class="active"><a href="<s:url action="manageVenue"/>">场馆管理<span class="sr-only">(current)</span></a></li>
                <li><a href="<s:url action="checkTickets"/>">检票登记</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/index.jsp">退出</a></li>
            </ul>
            <p class="navbar-text pull-right">欢迎您，<%=venue.getPlace()%>！</p>
        </div>
    </div>
</nav>

<jsp:useBean id="item" class="cn.edu.nju.software.models.Venue" scope="page"/>

<div style="padding-top: 100px">
    <div class="col-md-10">
        <div class="thumbnail">
            <div class="well">
                <form id="updateVenueForm" method="post" class="form-horizontal">
                    <div class="form-group">
                        <label for="venueid" class="col-sm-3 control-label">场馆识别码</label>
                        <div class="col-sm-8">
                            <input id="venueid" type="text" class="form-control" name="venueid" value="<jsp:getProperty name="item" property="venueid"/>" disabled="disabled">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="place" class="col-sm-3 control-label">地点</label>
                        <div class="col-sm-8">
                            <input id="place" type="text" class="form-control" name="place" value="<jsp:getProperty name="item" property="place"/>" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-sm-3 control-label">描述</label>
                        <div class="col-sm-8">
                            <textarea id="description" class="form-control" rows="5" name="description" required><jsp:getProperty name="item" property="description"/></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="seatnum" class="col-sm-3 control-label">座位数</label>
                        <div class="col-sm-8">
                            <input id="seatnum" type="text" class="form-control" name="seatnum" value="<jsp:getProperty name="item" property="seatnum"/>" required
                                   onchange="checkSeatNums()">
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <input id="register-venue-submit" type="submit" class="btn btn-primary" value="提交修改">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="dist/js/jquery.min.js"></script>
<script src="dist/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf-8">

    function checkSeatNums() {
        var seatnum = document.getElementById("seatnum");
        if(seatnum.value <= 0 || seatnum.value >= 999999) {
            seatnum.setCustomValidity("请确保内容合法!");
        } else {
            seatnum.setCustomValidity("");
        }
    }

    $(document).ready(function() {
        $("#updateVenueForm").submit(function(){
            $.post("/updateVenue.action",
                $('#updateVenueForm').serialize(),
                function(){
                    $("#updateVenueForm").text("修改成功！已提交经理审核。");
                });
            return false;
        });
    });

</script>

</body>
</html>
