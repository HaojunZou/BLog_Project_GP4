<%
    LinkedList<User> userList = (LinkedList<User>)session.getAttribute("users");
    if(userList == null){
        userList = new LinkedList<User>();
    }
    LinkedList<Post> postLinkedList = (LinkedList<Post>)request.getAttribute("posts");
    if(postLinkedList == null){
        postLinkedList = new LinkedList<Post>();
    }
    LinkedList<Post> resultPostsList = (LinkedList<Post>)request.getAttribute("resultPosts");
    if(resultPostsList == null){
        resultPostsList = new LinkedList<Post>();
    }
    LinkedList<Comment> commentLinkedList = (LinkedList<Comment>)request.getAttribute("comments");
    if(commentLinkedList == null){
        commentLinkedList = new LinkedList<Comment>();
    }
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="se.molk.blog.domain.Comment" %>
<%@ page import="se.molk.blog.domain.Post" %>
<%@ page import="se.molk.blog.domain.User" %>
<%@ page import="java.util.LinkedList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>Home</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.3.0/animate.min.css">
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"/>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <style>
        body{
            background-color: #A9BCF5;
        }
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
    </style>

</head>
<body>

<div class="container-fluid">
    <nav class="navbar">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="main.jsp"><img src="img/logo-white.png"
                style="position:absolute; top:5px; left:5px; width:160px; height:60px;"/>
                </a><br/>
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#navbar-collapse-3">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar" style="background:white"></span>
                    <span class="icon-bar" style="background:white"></span>
                    <span class="icon-bar" style="background:white"></span>
                </button>
            </div>

            <div class="collapse navbar-collapse" id="navbar-collapse-3">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#"><i class="fa fa-phone animated fadeInDown"><b> Contact</b></i></a></li>
                    <li><a href="login.jsp"><i class="fa fa-sign-out animated fadeInDown"><b> Log In</b></i></a></li>
                </ul>

            </div>
            <!-- /.navbar-collapse -->
            <div class="col-md-9"></div>
            <div class="col-md-3">
                <div class="input-group ">
                    <input type="text" class="form-control" name="fuzzySearchBlog" form="main_control" placeholder="Search for blog posts..."/>
                      <span class="input-group-btn">
                        <button class="btn btn-default" type="submit" name="fuzzySearchAction" value="Fuzzy Search" form="main_control" onclick="return SearchBlogValidate()"><i class="fa fa-search"></i></button>
                      </span>
                </div><!-- /input-group -->
            </div>
        </div>
        <!-- /.container -->
    </nav>
    <!-- /.navbar -->

    <div class="container-fluid "><br/><br/>


        <!--LEFT COLUMN, USERS LIST-->
        <form action="main.jsp" method="post">
            <div class="container col-md-3 animated bounceInLeft">
                <div class="well" id="userList">
                    <h4>Welcome!</h4>
                    <table>
                        <tr>
                            <th>User List:</th>
                        </tr>
                        <%
                            for(User user : userList) {
                                if(user.getUserType().equals("Administrator")){
                                    continue;
                                }
                        %>
                        <tbody align="center" valign="middle">
                        <tr>
                            <td><%= user.getUserName() %></td>
                        </tr>
                        </tbody><%}%>
                    </table>
                </div>
            </div>

            <div class="container col-md-6">
                <!-- BLOG POSTS -->
                <div id="all_posts">
                    <%
                        int i=0;
                        for(Post post : postLinkedList) {
                            i++;
                            if(i>10)
                                break;
                    %>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-6">
                                <div id="postlist">
                                    <div class="panel">
                                        <div class="panel-heading">
                                            <div class="text-center">
                                                <div class="row">
                                                    <div class="col-sm-9">
                                                        <h3 class="pull-left"><th><%= post.getTitle() %></th></h3>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <h4 class="pull-right">
                                                            <small><em><%= post.getDate() %><br></em></small>
                                                        </h4>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel-body">
                                            <%= post.getBody() %>
                                            <!-- COMMENT BOX -->
                                            <hr/>
                                            <div>
                                                <div><p>Comments:</p>
                                                    <%for(Comment comment : commentLinkedList) {%>
                                                    <%= comment.getCommentBody()%><br/><%}%>
                                                    <hr/>
                                                    <textarea name="commentBody" placeholder="Your Comment..." cols="60" rows="3" form="main_control" ></textarea>
                                                    <p></p>
                                                    <button type="submit" name="sentCommentAction" value="Send Comment" form="main_control" class="btn btn-success green" onclick="return SendCommentValidate()"><i class="fa fa-share"></i> Send</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/><%}%>
                </div>

                <!-- USERS BLOG POSTS -->
                <div id="result_all_posts">
                    <%
                        int j=0;
                        for(Post post : resultPostsList) {
                            j++;
                            if(j>10)
                                break;
                    %>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-6">
                                <div id="userPostlist">
                                    <div class="panel">
                                        <div class="panel-heading">
                                            <div class="text-center">
                                                <div class="row">
                                                    <div class="col-sm-9">
                                                        <h3 class="pull-left"><th><%= post.getTitle() %></th></h3>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <h4 class="pull-right">
                                                            <small><em><%= post.getDate() %><br></em></small>
                                                        </h4>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel-body">
                                            <%= post.getBody() %>
                                            <!-- COMMENT BOX -->
                                            <hr/>
                                            <div>
                                                <div><p>Comments:</p>
                                                    <%for(Comment comment : commentLinkedList) {%>
                                                    <%= comment.getCommentBody()%><br/><%}%>
                                                    <hr/>
                                                    <textarea name="commentBody" placeholder="Your Comment..." cols="60" rows="3" form="main_control" ></textarea>
                                                    <p></p>
                                                    <button type="submit" name="sentCommentAction" value="Send Comment" form="main_control" class="btn btn-success green" onclick="return SendCommentValidate()"><i class="fa fa-share"></i> Send</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/><%}%>
                    <br/><br/>
                </div>
            </div>
        </form>
        <br/><br/><br/><br/>

        <form action="/blog/MainControl" method="post" name="main_control" id="main_control">

            <!--
            <select name="category">
                <option value=""></option>
                <option value="life">life</option>
                <option value="education">education</option>
                <option value="animal">animal</option>
            </select>
            -->

        </form>
        <!--RIGHT COLUMN, FLASH GAME -->
        <div class="container col-md-3 animated bounceInRight">
            <script charset="Shift_JIS"
                src="http://chabudai.sakura.ne.jp/blogparts/honehoneclock/honehone_clock_tr.js">
            </script>
            <!--
            <b>Date:</b>
            <label>
                <input type="date" name="date"/>
            </label>
            -->
            <object type="application/x-shockwave-flash" style="outline:none;"
                    data="http://cdn.abowman.com/widgets/hamster/hamster.swf?" width="263"
                    height="197"><param name="movie" value="http://cdn.abowman.com/widgets/hamster/hamster.swf?">
                <param name="AllowScriptAccess" value="always">
                <param name="wmode" value="opaque">
            </object>
        </div>
    </div>
</div>

<script language="JavaScript">
    function SearchBlogValidate() {
        if (document.main_control.fuzzySearchBlog.value==""){
            alert("Nothing found!");
            document.main_control.fuzzySearchBlog.focus();
            return false;
        }else{
            $("#result_all_posts").show();
            $("#all_posts").hide();
            return true;
        }
    }
    function SendCommentValidate(){
        if (document.main_control.commentBody.value==""){
            alert("Please enter comment!");
            document.main_control.commentBody.focus();
            return false;
        }
    }

</script>
</body>
</html>