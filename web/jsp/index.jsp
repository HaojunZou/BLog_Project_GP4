<%@ page import="GP4.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Log In</title>
  </head>
  <body>
    <%
      User user = (User)request.getAttribute("user");
      if(user == null){
        user = new User();
      }
    %>

    <h1>Välkommen <%= user.getName() %></h1>

    <form action="ControllerServlet" method="post">
      <p>
        Namn: <input type="text" name="name" />
      </p>
      <p>
        Password: <input type="text" name="password" />
      </p>
      <p>
        <input type="submit" value="Skicka" />
      </p>
    </form>

  </body>
</html>
