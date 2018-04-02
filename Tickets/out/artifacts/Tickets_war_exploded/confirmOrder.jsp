<%@ page import="cn.edu.nju.software.models.Activity" %>
<%@ page import="cn.edu.nju.software.models.Bank" %>
<%@ page import="cn.edu.nju.software.models.Member" %>
<%@ page import="cn.edu.nju.software.models.Venue" %>
<%@ page import="java.util.List" %>
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
    List banks = (List) session.getAttribute("banks");
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
    <div class="thumbnail">
        <div class="well">
            <div class="modal-header">
                <h4 class="modal-title" id="registerVenueModalLabel">审核订单</h4>
            </div>
            <form id="confirmOrderForm" method="post" class="form-horizontal">
                <div class="form-group">
                    <p>请在3分钟内支付</p>
                    <label class="col-sm-3 control-label">选择银行卡</label>
                    <% for (Object anAlist : banks) {
                        Bank bank = (Bank) anAlist;
                        pageContext.setAttribute("b", bank);
                    %>
                    <jsp:useBean id="b" class="cn.edu.nju.software.models.Bank" scope="page"/>
                    <div class="col-sm-8">
                        <b><label class="radio radio-inline ">
                            <input type="radio" name="accountid"
                                   value="<jsp:getProperty name="b" property="accountid"/>" checked>
                            <jsp:getProperty name="b" property="accountid"/>
                        </label></b>
                     <% } %>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">银行卡密码</label>
                    <div class="col-sm-8">
                        <label class="radio radio-inline ">
                            <input type="password" name="password" id="pass" class="form-control"
                                   onchange="checkPasswords(<jsp:getProperty name="b" property="paypassword"/>)"
                                   required>
                        </label>
                    </div>
                </div>
                <div class="modal-footer">
                    <input id="confirm-order-submit" type="submit" class="btn btn-primary" value="确认">
                </div>
            </form>
        </div>
    </div>
</div>

<script src="dist/js/jquery.min.js"></script>
<script src="dist/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf-8">

    function checkPasswords(truepassword) {
        var pass = document.getElementById("pass");
        if (pass.value != truepassword) {
            alert("支付密码错误!");
        }
    }

    $(document).ready(function () {
        $("#confirmOrderForm").submit(function () {
            $.post("/confirmOrder.action",
                $('#confirmOrderForm').serialize(),
                function () {
                    $("#confirmOrderForm").text("购买成功！");
                });
            return false;
        });
    });

</script>
</body>
</html>
