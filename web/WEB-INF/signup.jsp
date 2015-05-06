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
                if (v.userName.value.length==0){
                    alert("Please enter a name!");
                    v.userName.focus();
                    return false;
                }
                if (v.email.value.length==0){
                    alert("Please enter an email!");
                    v.email.focus();
                    return false;
                }
                if (v.userPassword.value.length==0){
                    alert("Please enter a password!");
                    v.userPassword.focus();
                    return false;
                }
                if (v.userPasswordConfirm.value != v.userPassword.value){
                    alert("Confirm password is not correct!");
                    v.userPasswordConfirm.focus();
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
        <div style="text-align: center;"><h1>Welcome To Blog Registration</h1></div>
        <div style="text-align: center;">
            <form action="/blog/SignUpControl" id="information" method="post" onsubmit="return validate(this)">
                <table align="center">
                    <tr>
                        <td>User Name:</td><td><input type="text" name="userName"/><span style="color: red;"> *</span></td>
                    </tr>
                    <br/><br/>
                    <tr>
                        <td>Email:</td><td><input type="text" name="email"/><span style="color: red;"> *</span></td>
                    </tr>
                    <br/><br/>
                    <tr>
                        <td>Password:</td><td><input type="text" name="userPassword"/><span style="color: red;"> *</span></td>
                    </tr>
                    <br/><br/>
                    <tr>
                        <td>Confirm Password:</td><td><input type="text" name="userPasswordConfirm"/><span style="color: red;"> *</span></td>
                    </tr>
                    <br/><br/>
                    <tr>
                        <td>Real Name:</td><td><input type="text" name="realName"/></td>
                    </tr>
                    <br/><br/>
                    <tr>
                        <td>Gender:</td><td>
                            <select name="gender">
                                <option value=""></option>
                                <option value="male">Male</option>
                                <option value="female">Female</option>
                                <option value="other">Other</option>
                            </select>
                        </td>
                    </tr>
                    <br/><br/>
                    <tr>
                        <td>Birthday:</td><td><input type="date" name="birthday"/></td>
                    </tr>
                    <br/><br/>
                    <tr>
                        <td>Country:</td><td><input type="text" name="country"/></td>
                    </tr>
                </table>
                <br/>
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
            </form>
        </div>
    </body>
</html>