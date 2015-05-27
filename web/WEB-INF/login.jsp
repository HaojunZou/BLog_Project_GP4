<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
    <head>
        <base href="<%=basePath%>">
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Log In</title>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.3.0/animate.min.css">
        <link href="http://fonts.googleapis.com/css?family=Oleo+Script" rel="stylesheet" type="text/css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="//cdn.ckeditor.com/4.4.7/basic/ckeditor.js"></script>

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

            h1 {
                font: 400 50px/1.3 'Oleo Script', Helvetica, sans-serif;
                color: #2b2b2b;
                text-shadow: 4px 4px 0px rgba(0,0,0,0.1);
            }
        </style>
    </head>
    <body background="img/bg-color.jpg">

        <div>
            <a class="navbar-brand" style="font-size:40" href="main.jsp">
                <img src="img/logo-white.png" style="position:absolute; top:5px; left:5px; width:160px; height:60px;"/>
            </a><br/>
        </div>

        <div style="text-align: center;" class="animated flipInX">
            <br/><br/><br/><br/><br/><br/>
            <h1 style="color:#ffffff;"><b>Welcome</b></h1>
        </div>
        <br/><br/><br/>

        <form action="/blog/UserLogInControl" method="post" id="log_in_form" onsubmit="return validate(this)">
            <div class="container">
                <div class="col-md-4"></div>
                <div class="inner-addon left-addon col-md-4">
                    <i class="glyphicon glyphicon-user"></i>
                    <input type="text" class="form-control" name="userName" form="log_in_form" placeholder="User Name"/>
                </div>
                <div class="col-md-4"></div>
            </div>
            <br/>
            <div class="container">
                <div class="col-md-4"></div>
                <div class="inner-addon left-addon col-md-4">
                    <i class="glyphicon glyphicon-eye-open"></i>
                    <input type="password" class="form-control" name="userPassword" form="log_in_form" placeholder="Password"/>
                </div>
                <div class="col-md-4"></div>
            </div>
            <br/><br/><br/>
            <div class="container" style="text-align: center;">
                <input type="submit" class="btn btn-primary btn-lg animated flip" value="Log In"/>
                <input type="submit" class="btn btn-success btn-lg animated flip" value="Sign Up" form="sign_up_form"/>
                <a href="main.jsp" class="btn btn-info btn-lg animated flip" role="button">Back To Main</a>
            </div>
        </form>
        <form action="signup.jsp" id="sign_up_form" method="post">

        </form>

        <!-- check if name or password is empty -->
        <script language="JavaScript">
            function validate(v){
                if (v.userName.value.length==0){
                    alert("Please enter a name!");
                    v.userName.focus();
                    return false;
                }
                if (v.userPassword.value.length==0){
                    alert("Please enter a password!");
                    v.userPassword.focus();
                    return false;
                }
            }
            /*
            function containSpecial(s)
            {
                var containSpecial = RegExp(/[(\ )(\~)(\!)(\@)(\#)
                                            (\$)(\%)(\^)(\&)(\*)(\()(\))(\-)(\_)(\+)(\=)
                                            (\[)(\])(\{)(\})(\|)(\\)(\;)(\:)(\')(\")(\,)(\.)(\/)
                                            (\<)(\>)(\?)(\)]+/);
                return ( containSpecial.mocktest(s) );
            }
            */
        </script>
    </body>
</html>