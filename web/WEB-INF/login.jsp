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
    </head>
    <body background="img/bg.jpg">

        <div>
            <a class="navbar-brand" style="font-size:40" href="main.html">
                <img src="img/logo-white.png" style="position:absolute; top:5px; left:5px; width:160px; height:60px;"/>
            </a><br/>
        </div>

        <div style="text-align: center;">
            <h1>Welcome</h1>
        </div>
        <form action="/blog/UserLogInControl" method="post" onsubmit="return validate(this)">
            <div style="text-align: center;">
                <table align="center">
                    <tr>
                        <td>User Name</td><td><input type="text" class="form-control" name="userName"/></td>
                    </tr>
                    <br/><br/>
                    <tr>
                        <td>Password</td><td><input type="password" class="form-control" name="userPassword"/></td>
                    </tr>
                </table>
            </div>
            <br/>
            <div style="text-align: center;">
                <tr>
                    <input type="submit" value="Log in" />
                </tr>
            </div>
        </form>
        <form action="signup.jsp" method="post">
            <div style="text-align: center;">
                <p>New here?</p>
                <tr>
                    <input type="submit" value="Sign up" />
                </tr>
            </div>
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
        </script>
    </body>
</html>