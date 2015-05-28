<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link href="http://fonts.googleapis.com/css?family=Oleo+Script" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Oleo+Script" rel="stylesheet" type="text/css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="https://cdn.ckeditor.com/4.4.7/standard/ckeditor.js"></script>


    <!-- SOCIAL BUTTONS -->
    <link href="http://codepen.io/cguillou/pen/LmCuJ.css" rel="stylesheet" type="text/css">
    <script src="http://codepen.io/cguillou/pen/LmCuJ.scss"></script>

    <style>
        body {
            background-attachment: fixed;
        }

        h2 {
            font: 400 50px/1.3 'Oleo Script', Helvetica, sans-serif;
            color: #2b2b2b;
            text-shadow: 4px 4px 0px rgba(0,0,0,0.1);
        }

        h5 {
            font: 400 20px/1.3 'Oleo Script', Helvetica, sans-serif;
            color: #2b2b2b;
            text-shadow: 1px 1px 0px rgba(0,0,0,0.1);
        }

        section #services li div {
            width: 120px;
            height: 120px;
            border-radius: 100px;
            color:#086A87;
            font-size: 3.4em;
            text-align: center;
            line-height: 120px;
            background-color: #ffffff;
            opacity: .4;
            transition: all 0.5s ease;
        }

        html, body {
            margin: 0;
            background-color: #A9BCF5;
        }

        #nav_list a:hover{
            background-color: #339fff;
            box-shadow: 0 0 20px 10px #339fff;
            color: #fff;
            bottom: 5px;
            height: 45px;
        }

    </style>
</head>

<body background="img/bg-water.jpg">
<div class="container-fluid">
    <nav class="navbar">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="main.jsp"><img src="img/logo-white.png"
                style="position:absolute; top:15px; left:15px; width:160px; height:60px;"/>
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
                    <li><a href="main.jsp"><i class="fa fa-home" style="color: #ffffff;"><b> Go to main</b></i></a></li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
    <!-- /.navbar -->
</div>

<div class="container" style="text-align: center;">
    <br/><br/><br/>
    <h2 class="animated slideInDown" style="color: #ffffff"><b>Contact Blog</b></h2><br/><br/>
    <h5>Get in touch with us and get all your questions answered</h5>
</div>

<section><p></p>
    <ul id='services'>
        <li>
            <a href="mailto: blog@gmail.com"><div class='fa fa-envelope-o'></div></a>
            <span>E-mail Us</span>
        </li>
        <li>
            <a href="tel: 070-1232314"><div class='fa fa-mobile'></div></a>
            <span>Call Us</span>
        </li>
        <li>
            <a href="https://www.google.se/maps/place/Datalinjen+3,+583+30+Link%C3%B6ping/@58.3969471,15.5561867,17z/data=!3m1!4b1!4m2!3m1!1s0x46596f7d039b57bd:0x8553db47620d481b">
                <div class='fa fa-map-marker'></div></a>
            <span>Visit Us</span>
        </li>
        <li>
            <a href="https://facebook.com"><div class='fa fa-facebook'></div></a>
            <span>Facebook</span>
        </li>
        <li>
            <a href="https://twitter.com"><div class='fa fa-twitter'></div></a>
            <span>Twitter</span>
        </li>
        <li>
            <a href="https://instagram.com"> <div class="fa fa-instagram"></div></a>
            <span>Instagram</span>
        </li>
    </ul>
</section>

</body>
</html>