<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Change Password</title>
    </head>
    <body background="img/bg.jpg">
        <%
            String currentUserName = (String) session.getAttribute("currentUserName");
        %>
        <form action="user_change_pwd.jsp" method="post">
            <div style="text-align: center;">
                <h1><%=currentUserName %></h1>
            </div>
        </form>
        <form action="/blog/UserChangePwdControl" method="post" onsubmit="return validate(this)">
            <div style="text-align: center;">
                <h2>Change password</h2>
                <table align="center">
                    <% session.setAttribute("userName", currentUserName); %>
                    <tr>
                        <td>Old Password</td><td><input type="password" class="form-control" name="oldUserPassword"/></td>
                    </tr>
                    <br/><br/>
                    <tr>
                        <td>New Password</td><td><input type="password" class="form-control" name="newUserPassword"/></td>
                    </tr>
                    <br/><br/>
                    <tr>
                        <td>Confirm Password</td><td><input type="password" class="form-control" name="newUserPasswordConfirm"/></td>
                    </tr>
                </table>
                <input type="submit" value="Change password"/>
            </div>
        </form>

        <div style="text-align: center;"><button><a href="home.jsp">Back to home</a></button></div>

        <!-- check if password is empty -->
        <script language="JavaScript">
            function validate(v){
                if (v.oldUserPassword.value.length==0){
                    alert("Please enter your old password!");
                    v.oldUserPassword.focus();
                    return false;
                }
                if (v.newUserPassword.value.length==0){
                    alert("Please enter a new password!");
                    v.newUserPassword.focus();
                    return false;
                }
                if (v.newUserPasswordConfirm.value != v.newUserPassword.value){
                    alert("Confirm password is not correct!");
                    v.newUserPasswordConfirm.focus();
                    return false;
                }
            }
        </script>
    </body>
</html>
