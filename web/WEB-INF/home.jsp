<%
    LinkedList<User> userList;
    LinkedList<Post> postLinkedList;
    LinkedList<Post> userPostLinkedList;
    String currentUserName = (String) session.getAttribute("currentUserName");
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="se.molk.blog.domain.User" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="se.molk.blog.domain.Post" %>
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
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="//cdn.ckeditor.com/4.4.7/basic/ckeditor.js"></script>

</head>
<body background="img/bg.jpg">

<div class="container-fluid">
    <nav class="navbar">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="home.jsp"><img src="img/logo-white.png"
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
                    <li><a href="user_update_profile.jsp"><i class="fa fa-cog"><b> Config my profile</b></i></a></li>
                    <li><a href="user_change_pwd.jsp"><i class="fa fa-eye"><b> Change Password</b></i></a></li>
                    <li><a href="#"><i class="fa fa-phone"><b> Contact</b></i></a></li>
                    <li><a href="main.jsp"><i class="fa fa-sign-out"><b> Log Out</b></i></a></li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
    <!-- /.navbar -->

    <div class="container-fluid "><br/><br/>
        <link rel="stylesheet"
              href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"/>

        <!--LEFT COLUMN, USERS LIST-->
        <form action="home.jsp" method="post">
            <div class="container col-md-3">
                <div class="well" id="userList">
                    <%
                        userList = (LinkedList<User>)session.getAttribute("users");
                        if(userList == null){
                            userList = new LinkedList<User>();
                        }
                    %>
                    <h1>Welcome back, <%=currentUserName %>!</h1>
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
                    postLinkedList = (LinkedList<Post>)request.getAttribute("posts");
                    if(postLinkedList == null){
                        postLinkedList = new LinkedList<Post>();
                    }
                    int i=0;
                    for(Post post : postLinkedList) {
                        i++;
                        if(i>10)
                            break;
                    %>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-1"></div>
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
                                            <textarea type="text" name="author" form="blogBody" readonly><%= post.getUserId() %></textarea>
                                            <!--<input type="submit" name="showUsersBlog" value="Show this person's blog" form="blogBody" onclick="showUsersPosts()"/>-->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/><%}%>
                </div>

                <!-- USERS BLOG POSTS -->
                <div id="users_all_posts">
                    <%
                        userPostLinkedList = (LinkedList<Post>)request.getAttribute("usersPosts");
                        if(userPostLinkedList == null){
                            userPostLinkedList = new LinkedList<Post>();
                        }
                        int j=0;
                        for(Post post : userPostLinkedList) {
                            j++;
                            if(j>10)
                                break;
                    %>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-1"></div>
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
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/><%}%>
                    <br/><br/>
                </div>

                <div>
                    <label for="title"><b>Blog Title:</b></label>
                    <input type="text" name="title" id="title" form="blogBody"/><br/>
                    <textarea name="body" form="blogBody">Write blog here...<br/><br/><br/>
                    <textarea style="text-align: right" form="blogBody"><%= currentUserName%></textarea></textarea><br/>
                    <script>
                        CKEDITOR.replace("body");
                    </script>
                    <input type="submit" name="sendBlog" value="Send This Blog" form="blogBody"/>
                </div>
            </div>
        </form>
        <br/><br/><br/><br/>

        <form action="/blog/HomeControl" method="post" id="blogBody">
            <%session.setAttribute("blogUserName", currentUserName);%>

            <!--
            <select name="category">
                <option value=""></option>
                <option value="life">life</option>
                <option value="education">education</option>
                <option value="animal">animal</option>
            </select>
            -->

        </form>
        <!--RIGHT COLUMN, CHOOSE BLOG POST BY DATE -->
        <div class="container col-md-1"></div>
        <div class="container col-md-2">
            <b>Date:</b><br/>
            <input type="date" name="date"/>
        </div>
    </div>
</div>

<script language="JavaScript">
    function showUsersPosts(){
        $("#users_all_posts").show();
        $("#all_posts").hide();
    }
</script>
</body>
</html>