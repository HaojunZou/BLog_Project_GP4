<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<%@ page import="GP4.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Sign Up</title>
    </head>
    <body background="img/bg.jpg">
    <!-- check if name, email or password is empty, also confirm password is as same as password -->
        <script language="JavaScript">
            function validate(v){
                if (v.un.value.length==0){
                    alert("Please enter a name!");
                    v.un.focus();
                    return false;
                }
                if (v.email.value.length==0){
                    alert("Please enter an email!");
                    v.email.focus();
                    return false;
                }
                if (v.pwd.value.length==0){
                    alert("Please enter a password!");
                    v.pwd.focus();
                    return false;
                }
                if (v.passwordConfirm.value != v.pwd.value){
                    alert("Confirm password is not correct!");
                    v.passwordConfirm.focus();
                    return false;
                }
                if (v.accept.value != "accept"){
                    alert("Accept the provision");
                    v.accept.focus();
                    return false;
                }
            }
        </script>
        <div>
            <a class="navbar-brand" style="font-size:40" href="main.html">
                <img src="img/logo-white.png" style="position:absolute; top:5px; left:5px; width:160px; height:60px;"/>
            </a><br/>
        </div>
        <div style="text-align: center;">
            <h1>Welcome To Blog Registration</h1>
        </div>
        <form action="/blog/SignUpControl" method="post" onsubmit="return validate(this)">
            <div style="text-align: center;">
                <table align="center">
                    <tr>
                        <td>Name:</td><td><input type="text" name="un"/></td>
                    </tr>
                    <br/><br/>
                    <tr>
                        <td>Email:</td><td><input type="text" name="email"/></td>
                    </tr>
                    <br/><br/>
                    <tr>
                        <td>Password:</td><td><input type="text" name="pwd"/></td>
                    </tr>
                    <br/><br/>
                    <tr>
                        <td>Confirm Password:</td><td><input type="text" name="passwordConfirm"/></td>
                    </tr>
                </table>
            </div>
            <br/>

            <div style="text-align: center;">
                <textarea rows="6" cols="30" readonly>
        No violence!
        No reactionary!
        No pornography!
        No infringement!
                </textarea>
                <br/>
                <input type="radio" name="accept" value="accept" /> I accept
                <input type="radio" name="accept" value="decline" checked="checked"/> I decline
                <br/><br/>
                <tr>
                    <input type="submit" value="Sign Up"/>
                </tr>
            </div>
        </form>
    </body>
</html>