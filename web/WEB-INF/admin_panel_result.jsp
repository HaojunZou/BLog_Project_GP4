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
                    List<String> result1 = (List) request.getAttribute("resultId");
                    List<String> result2 = (List) request.getAttribute("resultUserType");
                    List<String> result3 = (List) request.getAttribute("resultUserName");
                    List<String> result4 = (List) request.getAttribute("resultEmail");
                    List<String> result5 = (List) request.getAttribute("resultUserPassword");
                    List<String> result6 = (List) request.getAttribute("resultRealName");
                    List<String> result7 = (List) request.getAttribute("resultGender");
                    List<String> result8 = (List) request.getAttribute("resultBirthday");
                    List<String> result9 = (List) request.getAttribute("resultCountry");
                %>
                <table border="1" style="background-color: white; float:left;">
                    <tr><th>ID</th></tr>
                    <tbody align="center" valign="middle">
                        <%for(int i=0; i<result1.size(); i++){String id = result1.get(i);%><tr>
                        <td> <%= id %> </td></tr><%}%>
                    </tbody>
                </table>
                <table border="1" style="background-color: white; float:left;">
                    <tr><th>User Type</th></tr>
                    <tbody align="center" valign="middle">
                        <%for(int i=0; i<result1.size(); i++){String userType = result2.get(i);
                            if(userType.equals("")){userType = "N/A";}%><tr>
                        <td> <%= userType %> </td></tr><%}%>
                    </tbody>
                </table>
                <table border="1" style="background-color: white; float:left;">
                    <tr><th>User Name</th></tr>
                    <tbody align="center" valign="middle">
                    <%for(int i=0; i<result1.size(); i++){String userName = result3.get(i);
                        if(userName.equals("")){userName = "N/A";}%><tr>
                        <td> <%= userName %> </td></tr><%}%>
                    </tbody>
                </table>
                <table border="1" style="background-color: white; float:left;">
                    <tr><th>Email</th></tr>
                    <tbody align="center" valign="middle">
                    <%for(int i=0; i<result1.size(); i++){String email = result4.get(i);
                        if(email.equals("")){email = "N/A";}%><tr>
                        <td> <%= email %> </td></tr><%}%>
                    </tbody>
                </table>
                <table border="1" style="background-color: white; float:left;">
                    <tr><th>User Password</th></tr>
                    <tbody align="center" valign="middle">
                    <%for(int i=0; i<result1.size(); i++){String userPassword = result5.get(i);
                        if(userPassword.equals("")){userPassword = "N/A";}%><tr>
                        <td> <%= userPassword %> </td></tr><%}%>
                    </tbody>
                </table>
                <table border="1" style="background-color: white; float:left;">
                    <tr><th>Real Name</th></tr>
                    <tbody align="center" valign="middle">
                    <%for(int i=0; i<result1.size(); i++){String realName = result6.get(i);
                        if(realName.equals("")){realName = "N/A";}%><tr>
                        <td> <%= realName %> </td></tr><%}%>
                    </tbody>
                </table>
                <table border="1" style="background-color: white; float:left;">
                    <tr><th>Gender</th></tr>
                    <tbody align="center" valign="middle">
                    <%for(int i=0; i<result1.size(); i++){String gender = result7.get(i);
                        if(gender.equals("")){gender = "N/A";}%><tr>
                        <td> <%= gender %> </td></tr><%}%>
                    </tbody>
                </table>
                <table border="1" style="background-color: white; float:left;">
                    <tr><th>Birthday</th></tr>
                    <tbody align="center" valign="middle">
                    <%for(int i=0; i<result1.size(); i++){String birthday = result8.get(i);
                        if(birthday.equals("")){birthday = "N/A";}%><tr>
                        <td> <%= birthday %> </td></tr><%}%>
                    </tbody>
                </table>
                <table border="1" style="background-color: white;">
                    <tr><th>Country</th></tr>
                    <tbody align="center" valign="middle">
                    <%for(int i=0; i<result1.size(); i++){String country = result9.get(i);
                        if(country.equals("")){country = "N/A";}%><tr>
                        <td> <%= country %> </td></tr><%}%>
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
