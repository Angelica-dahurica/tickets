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
    <link rel="stylesheet" href="dist/css/bootstrap-datetimepicker.min.css">
</head>

<body>
<%
    Venue venue = (Venue) session.getAttribute("venue");
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
                <li class="active"><a href="<s:url action="planActivities"/>">发布计划<span class="sr-only">(current)</span></a></li>
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

<div style="padding-top: 100px" class="col-md-10">
    <form action="<s:url action="planActivities"/>" id="planActivitiesForm" method="post" class="form-horizontal">
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">活动名称</label>
            <div class="col-sm-7">
                <input id="name" type="text" class="form-control" name="name" required>
            </div>
        </div>

        <div class="form-group">
            <label for="time" class="col-sm-2 control-label">活动时间</label>
            <div class="col-sm-7">
                <div class="input-group date form_datetime">
                    <input type="text" id="time" name="time" value="" class="form-control" readonly>
                    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">活动类型</label>
            <div class="col-sm-7">
                <b><label class="radio radio-inline col-sm-2">
                    <input type="radio" name="type" value="0" checked>音乐会</label>
                </b>
                <b><label class="radio radio-inline col-sm-2">
                    <input type="radio" name="type" value="1">舞蹈</label>
                </b>
                <b><label class="radio radio-inline col-sm-2">
                    <input type="radio" name="type" value="2">戏剧</label>
                </b>
                <b><label class="radio radio-inline col-sm-2">
                    <input type="radio" name="type" value="3">体育赛事</label>
                </b>
            </div>
        </div>

        <div class="form-group">
            <label for="description" class="col-sm-2 control-label">活动描述</label>
            <div class="col-sm-7">
                <textarea id="description" rows="4" class="form-control" name="description" required></textarea>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">活动状态</label>
            <div class="col-sm-7">
                <b><label class="radio radio-inline col-sm-2">
                    <input type="radio" name="status" value="0" checked>预售</label>
                </b>
                <b><label class="radio radio-inline col-sm-2">
                    <input type="radio" name="status" value="1">售票中</label>
                </b>
                <b><label class="radio radio-inline col-sm-2">
                    <input type="radio" name="status" value="2" >已过期</label>
                </b>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">座位类型</label>
            <div class="col-sm-7">
                <div class="input-group">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button" onclick="add(this)" >+</button>
                    </span>
                </div>
            </div>
        </div>

        <div class="modal-footer col-sm-9">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <input id="plan-activities-submit" type="submit" class="btn btn-primary" value="发布">
        </div>
    </form>
</div>

<script src="dist/js/jquery.min.js"></script>
<script src="dist/js/bootstrap.min.js"></script>
<script src="dist/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" charset="utf-8">

    $(".form_datetime").datetimepicker({
        format: "dd mm yyyy - hh:ii",
        autoclose: true
    });

    $(document).ready(function() {
        $("#planActivitiesForm").submit(function(){
            $.post("/publishActivities.action",
                $('#planActivitiesForm').serialize(),
                function(){
                    $("#planActivitiesForm").text("发布成功！");
                });
            return false;
        });
    });

    function add(obj){
        html = '<div class="input-group">'+
            '<label class="input-group-addon">类型：</label>'+
            '<input type="text" class="form-control" id="type" name="seattype">'+
            '<label class="input-group-addon">数量：</label>'+
            '<input type="text" class="form-control" id="num" name="num">'+
            '<label class="input-group-addon">票价：</label>'+
            '<input type="text" class="form-control" id="price" name="price">'+
            '<span class="input-group-btn">'+
            '<button class="btn btn-default" type="button" id="del">-</button>'+
            '</span>'+
            '</div>';
        obj.insertAdjacentHTML('afterend',html);
    }

    $(document).on('click','#del',function() {
        var el = this.parentNode.parentNode;
        el.parentNode.removeChild(el);
    });

</script>

</body>
</html>
