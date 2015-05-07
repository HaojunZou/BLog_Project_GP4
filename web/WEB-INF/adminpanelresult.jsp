<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Admin Panel Result</title>
    </head>
    <body>
        <div style="text-align: center;">
            <h1>Here's the result, Administrator</h1>
            <form action="adminpanelresult.jsp" method="post">
                <table border="1">
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
        </div>
        <div style="text-align: center;">
            <button><a href="adminpanel.jsp">Go back to panel</a></button>
            <button><a href="main.html">Log out</a></button>
        </div>
    </body>
</html>
