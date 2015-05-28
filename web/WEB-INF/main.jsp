<%
    LinkedList<User> userList = (LinkedList<User>)session.getAttribute("users");
    if(userList == null){
        userList = new LinkedList<User>();
    }
    LinkedList<Post> postLinkedList = (LinkedList<Post>)request.getAttribute("posts");
    if(postLinkedList == null){
        postLinkedList = new LinkedList<Post>();
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
    <title>Main</title>

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
            background-attachment: fixed;
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
        /*#nav {
            background-color: #102444;;
            color: white;
            height: 90px;
            left: 0;
            right: 0;
            box-shadow: 0 0 20px 10px #102444;
        }*/
        #nav_list a:hover{
            background-color: #f06fff;
            box-shadow: 0 0 20px 10px #f06fff;
            color: #fff;
            bottom: 5px;
            height: 45px;
        }
        #hone_hone_clock_bg{
            position:absolute;
            top: 45px;
            left: 225px;
            width: 135px;
            height: 10px;
            background-color: #ffffff;
            box-shadow: 0 0 20px 10px #ffffff;
        }
        #hone_hone_clock{
            position: absolute;
            top: 10px;
            left: 220px;
        }
        #user_list{
            background-color: #f5f5f5;
            box-shadow: 0 0 10px 5px #f5f5f5;
        }
        #post_list{
            background-color: #ffffff;
            box-shadow: 0 0 10px 5px #ffffff;
        }

    </style>

</head>
<body background="img/bg-light-purple.jpg">

    <div id="nav">
        <nav class="navbar">
            <div class="container col-md-12">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <a class="navbar-brand" href="main.jsp"><img src="img/logo-white.png"
                    style="position:absolute; top:15px; left:15px; width:160px; height:60px;"/>
                    <a id="hone_hone_clock_bg"></a>
                    <a id="hone_hone_clock">
                        <script charset="Shift_JIS" src="http://chabudai.sakura.ne.jp/blogparts/honehoneclock/honehone_clock_tr.js"></script>
                    </a>
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
                    <ul class="nav navbar-nav navbar-right" id="nav_list">
                        <li><a href="contact.jsp"><i class="fa fa-phone animated fadeInDown" style="color: #ffffff; font-size: 16px;"><b> Contact</b></i></a></li>
                        <li><a href="login.jsp"><i class="fa fa-sign-out animated fadeInDown" style="color: #ffffff; font-size: 16px;"><b> Log In</b></i></a></li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
                <div class="container col-md-12">
                    <div class="col-md-10"></div>
                    <div class="col-md-2">
                        <div class="input-group ">
                            <input type="text" class="form-control" name="fuzzySearchBlog" form="main_control" placeholder="Search for blog posts..."/>
                              <span class="input-group-btn">
                                <button class="btn btn-default" type="submit" name="fuzzySearchAction" value="Fuzzy Search" form="main_control" onclick="return SearchBlogValidate()"><i class="fa fa-search"></i></button>
                              </span>
                        </div><!-- /input-group -->
                    </div>
                </div>
            </div>
            <!-- /.container -->
        </nav>
        <!-- /.navbar -->
    </div>

    <div class="container-fluid"><br/><br/>
        <div class="col-md-9">
            <form action="main.jsp" method="post" onSubmit="return checkLength(this)">
                <!-- BLOG POSTS -->
                <div id="all_posts">
                    <%
                        int i=0;
                        for(Post post : postLinkedList) {
                            i++;
                            if(i>10)
                                break;
                    %>
                    <div class="row">
                        <div class="col-md-12">
                            <div id="post_list">
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
                                            <div><p><b>Comments:</b></p>
                                                <%for(Comment comment : commentLinkedList) {%>
                                                <%= comment.getCommentBody()%><br/><%}%>
                                                <hr/>
                                                <textarea name="commentBody" placeholder="Your Comment..." cols="60" rows="3" form="main_control" ></textarea>
                                                <p></p>
                                                <button type="submit" name="sentCommentAction" value="Send Comment" form="main_control" class="btn btn-success green"><i class="fa fa-share"></i> Send</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/><%}%>
                </div>
            </form>
        </div>

        <!-- RIGHT COLUMN, USER LIST, FLASH GAME -->
        <div class="col-md-3 animated bounceInRight">
            <div class="well" id="user_list">
                <h4>Welcome!</h4>
                <br/>
                <table>
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

        <form action="/blog/MainControl" method="post" name="main_control" id="main_control" onSubmit="return checkLength(this)">
            <!--
            <select name="category">
                <option value=""></option>
                <option value="life">life</option>
                <option value="education">education</option>
                <option value="animal">animal</option>
            </select>
            -->
        </form>

    </div>

<script language="JavaScript">
    function SearchBlogValidate() {
        if (document.main_control.fuzzySearchBlog.value==""){
            alert("Nothing found!");
            document.main_control.fuzzySearchBlog.focus();
            return false;
        }
    }
    function SendCommentValidate(){
        if (document.main_control.commentBody.value==""){
            alert("Please enter comment!");
            document.main_control.commentBody.focus();
            return false;
        }
    }
    function checkLength(form){
        if (form.fuzzySearchBlog.value.length > 100){
            alert("Text too long. Must be 100 characters or less");
            return false;
        }
        if (form.commentBody.value.length > 200){
            alert("Text too long. Must be 200 characters or less");
            return false;
        }
        return true;
    }

</script>
</body>
</html>