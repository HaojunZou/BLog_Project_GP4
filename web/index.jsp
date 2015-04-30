<%@ page import="GP4.User" %>
<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Insert title here</title>
</head>
<body>
  <%
    User user = (User)request.getAttribute("user");
    if(user == null){
      user = new User();
    }
  %>

  <h1>Välkommen <%= user.getName() %></h1>

  <ul>
    <%
      ArrayList<String> phoneNumbers = user.getPhoneNumbers();
      for(String phoneNumber : phoneNumbers) {
    %>
    <li><%= phoneNumber %></li>
    <%
      }
    %>
  </ul>

  <form action="ControllerServlet" method="post">
    <p>
      Namn: <input type="text" name="name" />
    </p>
    <p>
      <input type="submit" value="Skicka" />
    </p>
  </form>

</body>
</html>