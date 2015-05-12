<%@ page import="java.util.List" %>
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
                    List<String> userList = (List) request.getAttribute("resultUserList");
                %>
                <table border="1" style="background-color: white; float:left;">
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
                        <th>User Status</th>
                    </tr>
                    <tbody align="center" valign="middle">
                        <%for(int i=0; i<(userList.size()); i+=10){
                            %><tr>
                        <td> <%= userList.get(i) %> </td>
                        <td> <%= userList.get(i+1) %> </td>
                        <td> <%= userList.get(i+2) %> </td>
                        <td> <%= userList.get(i+3) %> </td>
                        <td> <%= userList.get(i+4) %> </td>
                        <td> <%= userList.get(i+5) %> </td>
                        <td> <%= userList.get(i+6) %> </td>
                        <td> <%= userList.get(i+7) %> </td>
                        <td> <%= userList.get(i+8) %> </td>
                        <td> <%= userList.get(i+9) %> </td>
                        </tr><%}%>
                    </tbody>
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
