
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Admin Panel</title>
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
            if (v.searchRecord.value.length==0){
                alert("Please enter an user name or an email!");
                v.searchRecord.focus();
                return false;
            }
        }
    </script>
    <div style="text-align: center;"><h1>Welcome back, Administrator</h1></div>
        <form action="/blog/AdminPanelControl" method="post" onsubmit="return validate(this)">
            <div style="text-align: center;">
Enter a key word to start fuzzy search:
                <input type="text" name="searchRecord"/><input type="submit" value="Search"/>
                <!-- <input type="text" name="changeRecord"/><input type="submit" value="Change"/> -->
                <!-- <input type="text" name="deleteRecord"/><input type="submit" value="Delete"/> -->
            </div>
        </form>
        <div style="text-align: center;">
            <button><a href="main.html">Log out</a></button>
        </div>
    </body>
</html>
