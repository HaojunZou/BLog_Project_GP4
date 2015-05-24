<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.3.0/animate.min.css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="//cdn.ckeditor.com/4.4.7/basic/ckeditor.js"></script>
        <title>Change Password</title>

        <style>
            .inner-addon {
                position: relative;
            }

            /* style icon */
            .inner-addon .glyphicon {
                position: absolute;
                padding: 10px;
                pointer-events: none;
            }

            /* align icon */
            .left-addon .glyphicon  { left:  15px;}
            .right-addon .glyphicon { right: 15px;}

            /* add padding  */
            .left-addon input  { padding-left:  30px; }
            .right-addon input { padding-right: 30px; }
        </style>

    </head>
    <body background="img/bg-grey.jpg">

        <%
            String currentUserName = (String) session.getAttribute("currentUserName");
            session.setAttribute("userName", currentUserName);
        %>
        <form action="user_change_pwd.jsp" method="post">
            <div style="text-align: center;" class="animated slideInDown">
                <h1 style="color:#ffffff;"><b><%=currentUserName %></b></h1>
            </div>
        </form>
        <form action="/blog/UserChangePwdControl" method="post" name="change_pw_form" id="change_pw_form" onsubmit="return validate(this)">
            <div style="text-align: center;" class="animated slideInDown">
                <h2 style="color:#ffffff;"><b>Change Password</b></h2>
            </div>
            <br/><br/><br/>
            <div class="container">
                <div class="col-md-4"></div>
                <div class="inner-addon left-addon col-md-4">
                    <i class="glyphicon glyphicon-eye-close"></i>
                    <input type="password" name="oldUserPassword" class="form-control" form="change_pw_form" placeholder="Old Password" required="True"/>
                </div>
                <div class="col-md-4"></div>
            </div>
            <br/>
            <div class="container">
                <div class="col-md-4"></div>
                <div class="inner-addon left-addon col-md-4">
                    <i class="glyphicon glyphicon-eye-open"></i>
                    <input type="password" name="newUserPassword" class="form-control" form="change_pw_form" placeholder="New Password" required="True"/>
                </div>
                <div class="col-md-4"></div>
            </div>
            <br/>
            <div class="container">
                <div class="col-md-4"></div>
                <div class="inner-addon left-addon col-md-4">
                    <i class="glyphicon glyphicon-eye-open"></i>
                    <input type="password" name="newUserPasswordConfirm" class="form-control" form="change_pw_form" placeholder="Confirm New Password"/>
                </div>
                <div class="col-md-4"></div>
            </div>
            <br/><br/><br/>
            <div class="container" style="text-align: center;">
                <input type="submit" class="btn btn-success btn-lg animated slideInUp" value="Change Password" form="change_pw_form"/>
                <a href="home.jsp" class="btn btn-info btn-lg animated slideInUp" role="button">Back To Home</a>
            </div>
        </form>

        <!-- check if password is empty -->
        <script language="JavaScript">
            function validate(v){
                if (v.newUserPasswordConfirm.value != v.newUserPassword.value){
                    alert("Confirm password is not correct!");
                    v.newUserPasswordConfirm.focus();
                    return false;
                }
            }
        </script>
    </body>
</html>
