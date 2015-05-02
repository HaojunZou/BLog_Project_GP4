<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<%@ page import="GP4.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Log In</title>
  </head>
  <body background="img/bg.jpg">
  <!-- check if name or password is empty -->
  <script language="JavaScript">
    function validate(v){
      if (v.name.value.length==0){
        alert("Please enter a name!");
        v.name.focus();
        return false;
      }
      if (v.password.value.length==0){
        alert("Please enter a password!");
        v.password.focus();
        return false;
      }
    }
  </script>

    <%
      User user = (User)request.getAttribute("user");
      if(user == null){
        user = new User();
      }
    %>
    <div>
      <a class="navbar-brand" style="font-size:40" href="img/logo-white.png">
        <img src="img/logo-white.png" style="position:absolute; top:5px; left:5px; width:160px; height:60px;"/>
      </a><br/>
    </div>
    <div style="text-align: center;">
      <h1>Welcome  <%= user.getName()%></h1>
    </div>
    <form action="home.html" method="post" onsubmit="return validate(this)">
      <div style="text-align: center;">
        <table align="center">
          <tr>
            <td>Namn:</td><td><input type="text" name="name"/></td>
          </tr>
          <br/><br/>
          <tr>
            <td>Password:</td><td><input type="text" name="password"/></td>
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
    <form action="register.jsp" method="post">
      <div style="text-align: center;">
        <p>New here?</p>
        <tr>
          <input type="submit" value="Register" />
        </tr>
      </div>
    </form>
  </body>
</html>
