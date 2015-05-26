<!DOCTYPE html>
<html>
    <head lang="en">
        <meta charset="UTF-8">
        <title>Home</title>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link href="http://fonts.googleapis.com/css?family=Oleo+Script" rel="stylesheet" type="text/css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="https://cdn.ckeditor.com/4.4.7/standard/ckeditor.js"></script>

        <!-- SOCIAL BUTTONS -->
        <link href="http://codepen.io/cguillou/pen/LmCuJ.css" rel="stylesheet" type="text/css">
        <script src="http://codepen.io/cguillou/pen/LmCuJ.scss"></script>

        <!-- SCRIPT FOR GOOGLE MAPS -->
        <script src="http://maps.googleapis.com/maps/api/js"></script>

        <script>
            var myCenter=new google.maps.LatLng(58.3961316,15.5569955);

            function initialize()
            {
                var mapProp = {
                    center:myCenter,
                    zoom:15,
                    mapTypeId:google.maps.MapTypeId.ROADMAP
                };

                var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);

                var marker=new google.maps.Marker({
                    position:myCenter,
                });

                marker.setMap(map);
            }

            google.maps.event.addDomListener(window, 'load', initialize);
        </script>
        <style>
            body {
                background-attachment: fixed;
            }

            h2 {
                font: 400 50px/1.3 'Oleo Script', Helvetica, sans-serif;
                color: #2b2b2b;
                text-shadow: 4px 4px 0px rgba(0,0,0,0.1);
            }

            h3 {
                font: 400 25px/1.3 Comic Sans MS, sans-serif;
                color: #2b2b2b;
                text-shadow: 1px 1px 0px rgba(0,0,0,0.1);
            }

            h4 {
                font: 400 19px/1.3 Comic Sans MS, sans-serif;
                color: #2b2b2b;
                text-shadow: 1px 1px 0px rgba(0,0,0,0.1);
            }

            section #services li div {
                width: 120px;
                height: 120px;
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

        </style>
    </head>
    <body background="img/bg-water.jpg">

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
                            <li><a href="home.jsp"><i class="fa fa-home" style="color: #ffffff;"><b> Home</b></i></a></li>
                            <li><a href="contact.jsp"><i class="fa fa-phone" style="color: #ffffff;"><b> Contact</b></i></a></li>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->
                </div>
                <!-- /.container -->
            </nav>
            <!-- /.navbar -->
        </div>

        <div class="col-md-3"></div>
        <div class="col-md-6" style="text-align: center;">
            <br/><br/><br/>
            <h2 class="animated slideInDown" style="color: #ffffff"><b>Contact Us</b></h2><br/><br/>
        </div>
        <div class="col-md-3"></div>

        <!-- DIV FOR GOOGLE MAPS -->
        <div id="googleMap" style="width:500px;height:380px;left:450px;text-align: center;"></div>

        <div class="container">
            <div style="text-align:center"><p></p><p></p><p></p>

                <h3>Please feel free to contact us!</h3>

                <h4><b>Address: </b> Datalinjen 3B<br/> 583 30 Linkoping</h4>
                <h4 ><b>Phone: </b><a href="tel: 555-555-555" style="color: #ffffff">555-555-555</a></h4>
                <h4 ><b>Email: </b><a href="mailto: blog@gmail.com" style="color: #ffffff"> blog@gmail.com</a></h4>
            </div>
        </div>

        <section>
            <hr/>
            <ul id='services'>
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