<%@ page import="cn.edu.nju.software.models.Member" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.nju.software.models.Bank" %>
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
    Member m = (Member)session.getAttribute("member");
    List banks = (List) request.getAttribute("banks");
    pageContext.setAttribute("item", m);
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
                <li class="active"><a href="<s:url action="manageAccount"/>">账号管理<span class="sr-only">(current)</span></a></li>
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

<jsp:useBean id="item" class="cn.edu.nju.software.models.Member" scope="page"/>

<div style="margin-top: 100px">
    <div class="col-md-10">
        <div class="thumbnail">
            <div class="well">
                <form id="updateMemberForm" method="post" class="form-horizontal">
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-9">
                            <input id="email" type="text" class="form-control" name="email" value="<jsp:getProperty name="item" property="email"/>" disabled="disabled">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">昵称</label>
                        <div class="col-sm-9">
                            <input id="name" type="text" class="form-control" name="name" value="<jsp:getProperty name="item" property="name"/>" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-9">
                            <% if( item.getSex() == 1 ) { %>
                                <b><label class="radio radio-inline ">
                                    <input type="radio" name="sex" value="男" checked>男</label>
                                </b>
                                <b><label class="radio radio-inline col-md-offset-1 ">
                                    <input type="radio" name="sex" value="女">女</label>
                                </b>
                            <% } else { %>
                                <b><label class="radio radio-inline ">
                                    <input type="radio" name="sex" value="男" >男</label>
                                </b>
                                <b><label class="radio radio-inline col-md-offset-1 ">
                                    <input type="radio" name="sex" value="女" checked>女</label>
                                </b>
                            <% } %>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="age" class="col-sm-2 control-label">年龄</label>
                        <div class="col-sm-9">
                            <input id="age" type="text" class="form-control" name="age"
                                   required value="<jsp:getProperty name="item" property="age"/>"
                                   onchange="checkAge()">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="address" class="col-sm-2 control-label">城市</label>
                        <div class="col-sm-9">
                            <input id="address" type="text" class="form-control" name="address"
                                   value="<jsp:getProperty name="item" property="address"/>" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="consumption" class="col-sm-2 control-label">消费</label>
                        <div class="col-sm-9">
                            <input id="consumption" type="text" class="form-control" name="consumption"
                                   value="<jsp:getProperty name="item" property="consumption"/>" disabled="disabled" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="score" class="col-sm-2 control-label">积分</label>
                        <div class="col-sm-9">
                            <input id="score" type="text" class="form-control" name="score"
                                   value="<jsp:getProperty name="item" property="score"/>" disabled="disabled" required>
                            <button type="button" class="btn btn-default" value="<jsp:getProperty name="item" property="score"/>"
                                    onclick="exchange(this.value)">兑换优惠券</button>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="level" class="col-sm-2 control-label">等级</label>
                        <div class="col-sm-9">
                            <input id="level" type="text" class="form-control" name="level"
                                   value="<jsp:getProperty name="item" property="level"/>" disabled="disabled" required>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <input id="update-member-submit" type="submit" class="btn btn-primary" value="提交修改">
                    </div>
                </form>
            </div>
        </div>

        <div class="thumbnail">
            <div class="well">
                <%
                    for (Object anAlist : banks) {
                        Bank b = (Bank) anAlist;
                        pageContext.setAttribute("bank", b);
                %>
                <jsp:useBean id="bank" class="cn.edu.nju.software.models.Bank" scope="page"/>
                <p>银行卡号：<jsp:getProperty name="bank" property="accountid"/></p>
                <%
                    }
                %>

                <form id="addCardForm" method="post" class="form-horizonta">
                    <div class="form-group" style="height: 50px">
                        <label for="accountid" class="col-sm-2 control-label">添加银行卡</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="accountid" name="accountid">
                        </div>
                    </div>

                    <div class="modal-footer">
                        <input id="add-card-submit" type="submit" class="btn btn-primary" value="确认添加">
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>




<script src="dist/js/jquery.min.js"></script>
<script src="dist/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf-8">

    function exchange(score) {
        if(score < 500) {
            alert("您的积分不够！");
        } else {
            var r = confirm("你确定要用500积分兑换10元优惠券吗？");
            if (r == true) {
                $.post("/exchangeCoupon.action", {},
                    function(){
                        alert("兑换成功！");
                    });
                return false;
            }
        }
    }

    function checkAge() {
        var age = document.getElementById("age");
        if(age.value <= 0 || age.value >= 200) {
            age.setCustomValidity("请确保年龄合法!");
        } else {
            age.setCustomValidity("");
        }
    }

    $(document).ready(function() {
        $("#updateMemberForm").submit(function(){
            $.post("/updateMember.action",
                $('#updateMemberForm').serialize(),
                function(){
                    $("#updateMemberForm").text("修改成功！");
                });
            return false;
        });
    });

    $(document).ready(function() {
        $("#addCardForm").submit(function(){
            $.post("/bindCard.action",
                $('#addCardForm').serialize(),
                function(){
                    $("#addCardForm").text("添加成功！");
                });
            return false;
        });
    });

</script>
</body>
</html>
