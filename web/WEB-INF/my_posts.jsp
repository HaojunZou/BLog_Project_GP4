<%
    Post selectedPost = (Post) request.getAttribute("selectedPost");
    if(selectedPost == null){
        selectedPost = new Post();
    }
%>
<%@ page import="se.molk.blog.domain.Post" %>
<%@ page import="java.util.LinkedList" %>
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
    <title>My Posts</title>

    <style>
        body{
            background-color: #bdbdbd;
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
<body background="img/bg-light-blue.jpg">

    <div class="container" style="text-align: center;">
        <div style="text-align: center;" class="animated slideInDown">
            <h1><b>My Posts...</b></h1>
        </div>
        <form action="my_posts.jsp" method="post">
            <%
                LinkedList<Post> postList = (LinkedList<Post>)request.getAttribute("posts");
                if(postList == null){
                    postList = new LinkedList<Post>();
                }
            %>
            <table border="1" style="background-color: white;">
                <tr>
                    <th>Post ID</th>
                    <th>Post Title</th>
                    <th>Post Body</th>
                    <th>Author ID</th>
                    <th>Published Date</th>
                </tr>
                <%
                    for(Post post: postList) {
                %>
                <tbody align="center" valign="middle">
                <tr>
                    <td><%= post.getId() %></td>
                    <td><%= post.getTitle() %></td>
                    <td><%= post.getBody() %></td>
                    <td><%= post.getUserId() %></td>
                    <td><%= post.getDate() %></td>
                </tr>
                </tbody><%}%>
            </table>
        </form>
    </div>

    <form action="/blog/MyPostsControl" method="post" name="my_posts_control" id="my_posts_control">

        <!-- DELETE A POST -->
        <div style="text-align: center;">
            <h2><b>Delete A Post</b></h2><br/>
            <h4><b>Enter the post id to delete a post:</b></h4>
            <span style="color:red">(this action may not be reversed)</span>
        </div>
        <div class="container">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <div class="inner-addon left-addon col-md-6">
                    <i class="glyphicon glyphicon-file"></i>
                    <input type="number" id="delete_post_record" name="deletePostRecord" min="1" max="999999999"
                           class="form-control" form="my_posts_control" placeholder="Post ID?"/>
                </div>
                <div class="col-md-6">
                    <input type="submit" class="btn btn-warning btn-lg" name="deletePostAction" value="Delete Post" onclick="return deletePostValidate()"/>
                </div>
            </div>
            <div class="col-md-4"></div>
        </div>

    <!-- UPDATE A POST -->
        <div style="text-align: center;">
            <h2><b>Update A Post</b></h2><br/>
            <div class="container">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="inner-addon left-addon col-md-6">
                        <i class="glyphicon glyphicon-file"></i>
                        <input type="number" class="form-control" name="searchPostId" form="my_posts_control" min="1" max="999999999" placeholder="Post ID?"/>
                        <input type="hidden" class="form-control" name="thisPostId" value="<%= selectedPost.getId() %>" form="my_posts_control"/>
                    </div>
                    <div class="col-md-6">
                        <input class="btn btn-default btn-lg" type="submit" name="searchPostAction" value="Get This Post" form="my_posts_control" onclick="return updatePostValidate()"/>
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>
        <br/>
        <div class="container">
            <div class="col-md-6">
                <label>New Post Title
                    <textarea class="form-control" name="newPostTitle" form="my_posts_control"><%= selectedPost.getTitle() %></textarea>
                </label>
                <script>
                    CKEDITOR.replace("newPostTitle");
                </script>
            </div>
            <div class="col-md-6">
                <label>New Post Body
                    <textarea class="form-control" name="newPostBody" cols="60" rows="10" form="my_posts_control"><%= selectedPost.getBody() %></textarea>
                </label>
                <script>
                    CKEDITOR.replace("newPostBody");
                </script>
            </div>
        </div>
        <br/>
        <div class="container" style="text-align: center;">
            <input type="submit" class="btn btn-warning btn-lg" name="updatePostAction" value="Update This Post" form="my_posts_control"/>
        </div>
    </form>
    <br/><br/>

    <div class="container" style="text-align: center;">
        <a href="user_profile.jsp" class="btn btn-info btn-lg animated slideInUp" role="button">Back</a>
    </div>
    <br/><br/><br/><br/>

    <script language="javascript">
        function deletePostValidate(){
            if (document.my_posts_control.deletePostRecord.value==""){
                alert("Please enter a value to delete a post!");
                document.my_posts_control.deletePostRecord.focus();
                return false;
            }
        }
        function updatePostValidate(){
            if (document.my_posts_control.searchPostId.value==""){
                alert("Please enter a post ID!");
                document.my_posts_control.searchPostId.focus();
                return false;
            }
        }
    </script>

</body>
</html>
