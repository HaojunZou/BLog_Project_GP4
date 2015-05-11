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
                <table border="1" style="background-color: white">
                    <%
                        String result1 = (String) request.getAttribute("resultId");
                        String result2 = (String) request.getAttribute("resultUserType");
                        String result3 = (String) request.getAttribute("resultUserName");
                        String result4 = (String) request.getAttribute("resultEmail");
                        String result5 = (String) request.getAttribute("resultUserPassword");
                        String result6 = (String) request.getAttribute("resultRealName");
                        String result7 = (String) request.getAttribute("resultGender");
                        String result8 = (String) request.getAttribute("resultBirthday");
                        String result9 = (String) request.getAttribute("resultCountry");
                    %>
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
                    <tbody align="center" valign="middle">
                        <tr>
                            <td> <%= result1 %> </td>
                            <td> <%= result2 %> </td>
                            <td> <%= result3 %> </td>
                            <td> <%= result4 %> </td>
                            <td> <%= result5 %> </td>
                            <td> <%= result6 %> </td>
                            <td> <%= result7 %> </td>
                            <td> <%= result8 %> </td>
                            <td> <%= result9 %> </td>
                        </tr>
                    </tbody>
                </table>
            </form>
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
