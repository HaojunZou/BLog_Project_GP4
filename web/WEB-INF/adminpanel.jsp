
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Admin Panel</title>
    </head>
    <body>
    <div style="text-align: center;"><h1>Welcome back, Administrator</h1></div>
        <form action="/blog/AdminPanelControl" method="post">
            <div style="text-align: center;">
Enter a user name or email to start search:
                <input type="text" name="searchRecord"/><input type="submit" value="Search"/>
                <!-- <input type="text" name="changeRecord"/><input type="submit" value="Change"/> -->
                <!-- <input type="text" name="deleteRecord"/><input type="submit" value="Delete"/> -->
            </div>
        </form>
        <form action="main.html" method="post">
            <div style="text-align: center;">
                <input type="submit" value="Back to main"/>
            </div>
        </form>
    </body>
</html>
