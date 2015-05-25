
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.3.0/animate.min.css">
        <link href="http://fonts.googleapis.com/css?family=Oleo+Script" rel="stylesheet" type="text/css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="//cdn.ckeditor.com/4.4.7/basic/ckeditor.js"></script>
        <title>Admin Panel</title>

        <style>
            .inner-addon {
                position: relative;
            }

            /* style icon */
            .inner-addon .glyphicon {
                position: absolute;
                padding: 10px;
                pointer-events: none;
            }

            /* align icon */
            .left-addon .glyphicon  { left:  15px;}
            .right-addon .glyphicon { right: 15px;}

            /* add padding  */
            .left-addon input  { padding-left:  30px; }
            .right-addon input { padding-right: 30px; }

            h1 {
                font: 400 50px/1.3 'Oleo Script', Helvetica, sans-serif;
                color: #2b2b2b;
                text-shadow: 4px 4px 0px rgba(0,0,0,0.1);
            }
        </style>

    </head>
    <body background="img/bg-red.jpg">

        <div style="text-align: center;">
            <br/><br/><br/><br/><br/><br/>
            <h1 class="animated slideInDown" style="color:#ffffff;"><b>Welcome back, Administrator</b></h1>
        </div>
        <br/><br/><br/>

        <form action="/blog/AdminPanelControl" id="admin_panel" name="admin_panel" method="post">
            <!-- SEARCH USER -->
            <div class="container">
                <div class="col-md-3"></div>
                <div class="inner-addon right-addon col-md-6">
                    <i class="glyphicon glyphicon-search"></i>
                    <input type="text" name="userSearchRecord" class="form-control" placeholder="Enter a key word to start fuzzy search users(empty for search all)"/>
                </div>
                <div class="col-md-3">
                    <input type="submit" class="btn btn-success btn-lg" name="userSearchAction" value="User Search"/>
                </div>
            </div>
            <br/><br/><br/>

            <!-- SEARCH POST -->
            <div class="container">
                <div class="col-md-3"></div>
                <div class="inner-addon right-addon col-md-6">
                    <i class="glyphicon glyphicon-search"></i>
                    <input type="text" name="postSearchRecord" class="form-control" placeholder="Enter a title to start fuzzy search posts(empty for search all)"/>
                </div>
                <div class="col-md-3">
                    <input type="submit" class="btn btn-success btn-lg" name="postSearchAction" value="Post Search"/>
                </div>
            </div>
        </form>
        <br/><br/><br/>

        <div class="container" style="text-align: center;">
            <a href="main.jsp" class="btn btn-info btn-lg animated slideInUp" role="button">Log Out</a>
        </div>

    </body>
</html>
