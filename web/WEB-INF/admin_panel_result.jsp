
<%@ page import="se.molk.blog.domain.User" %>
<%@ page import="java.util.LinkedList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Admin Panel Result</title>
        <style>
            body{
                background-image: url("http://localhost:8080/blog/img/su-35.jpg");
                background-size: cover;
            }
        </style>
    </head>
    <body>
    <script language="JavaScript">
        function validate(v){
            if (v.deleteRecord.value.length==0){
                alert("Please enter an user name!");
                v.deleteRecord.focus();
                return false;
            }
        }
    </script>
        <div style="text-align: center;">
            <h1>Here's the result, Administrator</h1>
            <form action="admin_panel_result.jsp" method="post">
                <%
                    LinkedList<User> userList = (LinkedList<User>)request.getAttribute("users");
                    if(userList == null){
                        userList = new LinkedList<User>();
                    }
                %>
                <table border="1" style="background-color: white;">
                    <tr>
                        <th>ID</th>
                        <th>User Type</th>
                        <th>User Name</th>
                        <th>Email</th>
                        <th>User Password</th>
                        <th>Real Name</th>
                        <th>Gender</th>
                        <th>Birthday</th>
                        <th>Country</th>
                    </tr>
                    <%
                        for(User user : userList) {
                    %>
                    <tbody align="center" valign="middle">
                    <tr>
                        <td><%= user.getUserId() %></td>
                        <td><%= user.getUserType() %></td>
                        <td><%= user.getUserName() %></td>
                        <td><%= user.getEmail() %></td>
                        <td><%= user.getUserPassword() %></td>
                        <td><%= user.getRealName() %></td>
                        <td><%= user.getGender() %></td>
                        <td><%= user.getBirthday() %></td>
                        <td><%= user.getCountry() %></td>
                    </tr>
                    </tbody><%}%>
                </table>
            </form>
            <br/>
            <form action="/blog/AdminPanelResultControl" method="post" onsubmit="return validate(this)">
                Enter the user name to delete an user: <span style="color:red">(this action may not be reversed)</span>
                <input type="text" name="deleteRecord"/><input type="submit" value="Delete"/>
            </form>
        </div>
        <div style="text-align: center;">
            <button><a href="admin_panel.jsp">Go back to panel</a></button>
            <button><a href="main.html">Log out</a></button>
        </div>
    </body>
</html>
