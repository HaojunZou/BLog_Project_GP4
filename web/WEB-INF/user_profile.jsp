<%@ page import="se.molk.blog.domain.User" %>
<%
    String currentUserName = (String) session.getAttribute("currentUserName");
    session.setAttribute("userName", currentUserName);
    User userInfo = (User) request.getAttribute("userInfo");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Profile</title>
    <meta charset="UTF-8">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.3.0/animate.min.css">
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"/>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="//cdn.ckeditor.com/4.4.7/basic/ckeditor.js"></script>

    <style>
        body{
            background-attachment: fixed
        }

    </style>
    </head>
    <body background="img/bg-color.jpg">
        <h1 class="animated fadeInDown" style="text-align: center; color:#ffffff;"><%=currentUserName%></h1>

        <form action="user_profile.jsp" method="post" name="user_profile" id="user_profile">
            <div class="container" style="text-align: center; color:#ffffff;" id="user_info">
                <h3>User Name: <%=userInfo.getUserName() %></h3>
                <h3>Real Name: <%=userInfo.getRealName() %></h3>
                <h3>Gender: <%=userInfo.getGender() %></h3>
                <h3>Birthday: <%=userInfo.getBirthday() %></h3>
                <h3>Country: <%=userInfo.getCountry() %></h3>
            </div>
        </form>

        <form action="/blog/UserProfileControl" method="post" name="user_profile_control" id="user_profile_control">
            <div class="container" style="text-align: center">
                <a href="user_update_profile.jsp" class="btn btn-default btn-lg animated slideInUp"> Config my profile</a>
                <a href="user_change_pwd.jsp" class="btn btn-primary btn-lg animated slideInUp"> Change Password</a>
                <input type="submit" class="btn btn-success btn-lg animated slideInUp" name="postSearchAction" value="My Posts"/>
                <a href="home.jsp" class="btn btn-info btn-lg animated slideInUp" role="button">Back To Home</a>
            </div>
        </form>

</body>
</html>
