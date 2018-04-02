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

<body style="background-image: url(img/index.jpg)">
<%
    session.invalidate();
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
                <li class="active"><a href="/index.jsp">关于Tickets<span class="sr-only">(current)</span></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a data-toggle="modal" data-target="#loginManagerModal">经理登录</a></li>
                <li><a data-toggle="modal" data-target="#loginMemberModal">用户登录</a></li>
                <li><a data-toggle="modal" data-target="#loginVenueModal">场馆登录</a></li>
                <li><a data-toggle="modal" data-target="#registerMemberModal">用户注册</a></li>
                <li><a data-toggle="modal" data-target="#registerVenueModal">场馆注册</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="modal fade" id="loginManagerModal" tabindex="-1" role="dialog" aria-labelledby="loginManagerModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="loginManagerModalLabel">经理登录</h4>
            </div>
            <form action="<s:url action="logInManager"/>" method="post" class="form-horizontal">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="id" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="id" name="id" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="manager_password" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" id="manager_password" name="password" required>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <input type="submit" class="btn btn-primary" value="登陆">
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="loginMemberModal" tabindex="-1" role="dialog" aria-labelledby="loginMemberModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="loginMemberModalLabel">用户登录</h4>
            </div>
            <form action="<s:url action="logInMember"/>" method="post" class="form-horizontal">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="email_login" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="email_login" name="email" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password_login" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" id="password_login" name="password" required>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <input type="submit" class="btn btn-primary" value="登陆">
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="loginVenueModal" tabindex="-1" role="dialog" aria-labelledby="loginVenueModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="loginVenueModalLabel">场馆登录</h4>
            </div>
            <form action="<s:url action="logInVenue"/>" method="post" class="form-horizontal">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="venue_id" class="col-sm-3 control-label">场馆识别码</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="venue_id" name="id" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="venue_password" class="col-sm-3 control-label">密码</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" id="venue_password" name="password" required>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <input type="submit" class="btn btn-primary" value="登陆">
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="registerMemberModal" tabindex="-1" role="dialog" aria-labelledby="registerMemberModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="registerMemberModalLabel">用户注册</h4>
            </div>
            <form action="<s:url action="registerMember"/>" id="registerMemberForm" method="post" class="form-horizontal">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="register_email" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <input type="text" class="form-control" id="register_email" name="email" required>
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button" onclick="getVerification()">获取验证码</button>
                                </span>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="verification" class="col-sm-2 control-label">验证码</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="verification" name="verification" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="nick" class="col-sm-2 control-label">昵称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="nick" name="name" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="pwd" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-9">
                            <input id="pwd" type="password" class="form-control" name="password" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="pwd1" class="col-sm-2 control-label">密码确认</label>
                        <div class="col-sm-9">
                            <input id="pwd1" type="password" class="form-control" name="password_confirm" required
                                   onchange="checkPasswords()">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="age" class="col-sm-2 control-label">年龄</label>
                        <div class="col-sm-9">
                            <input id="age" type="text" class="form-control" name="age" required
                                   onchange="checkAge()">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="address" class="col-sm-2 control-label">城市</label>
                        <div class="col-sm-9">
                            <input id="address" type="text" class="form-control" name="address" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-9">
                            <b><label class="radio radio-inline ">
                                <input type="radio" name="sex" value="男" checked>男</label>
                            </b>
                            <b><label class="radio radio-inline col-md-offset-1 ">
                                <input type="radio" name="sex" value="女">女</label>
                            </b>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <input id="register-member-submit" type="submit" class="btn btn-primary" value="注册">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="registerVenueModal" tabindex="-1" role="dialog" aria-labelledby="registerVenueModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="registerVenueModalLabel">场馆注册</h4>
            </div>
            <form action="<s:url action="registerVenue"/>" id="registerVenueForm" method="post" class="form-horizontal">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="place" class="col-sm-2 control-label">地点</label>
                        <div class="col-sm-9">
                            <input id="place" type="text" class="form-control" name="place" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-9">
                            <textarea id="description" rows="5" class="form-control" name="description" required></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-9">
                            <input id="password" type="password" class="form-control" name="password" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password1" class="col-sm-2 control-label">密码确认</label>
                        <div class="col-sm-9">
                            <input id="password1" type="password" class="form-control" name="password_confirm" required
                                   onchange="checkPasswords()">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="seatnum" class="col-sm-2 control-label">座位数</label>
                        <div class="col-sm-9">
                            <input id="seatnum" type="text" class="form-control" name="seatnum" required
                                   onchange="checkSeatNums()">
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <input id="register-venue-submit" type="submit" class="btn btn-primary" value="注册">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="col-sm-12 col-md-12" align="center">
    <div class="caption" style="margin-top: 150px;">
        <h1>欢迎来到Tickets订票系统！</h1>
        <p>你想要的触手可及</p>
    </div>
</div>


<script src="dist/js/jquery.min.js"></script>
<script src="dist/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf-8">

    function getVerification(){
        var email = document.getElementById("register_email");
        var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
        if(email.value === "") {
            alert("输入不能为空!");
        } else if(!reg.test(email.value)) {
            alert("请确保邮箱格式正确!");
        } else {
            $.post("/verify.action", {email: email.value}, function(data){
                alert($('#register_email').html(data).find("#alert").html());
            });
        }
    }

    function checkPasswords() {
        var passl = document.getElementById("pwd");
        var pass2 = document.getElementById("pwd1");
        if (passl.value != pass2.value) {
            passl.setCustomValidity("两次密码必须输入一致！");
        } else {
            passl.setCustomValidity('');
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

    function checkSeatNums() {
        var seatnum = document.getElementById("seatnum");
        if(seatnum.value <= 0 || seatnum.value >= 999999) {
            seatnum.setCustomValidity("请确保内容合法!");
        } else {
            seatnum.setCustomValidity("");
        }
    }

</script>

</body>
</html>