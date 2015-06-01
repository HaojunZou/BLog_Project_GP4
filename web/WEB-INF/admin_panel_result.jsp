<%
    Post selectedPost = (Post) request.getAttribute("selectedPost");
    if(selectedPost == null){
        selectedPost = new Post();
    }
%>
<%@ page import="se.molk.blog.domain.User" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="se.molk.blog.domain.Post" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="utf-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.3.0/animate.min.css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="//cdn.ckeditor.com/4.4.7/basic/ckeditor.js"></script>
        <title>Admin Panel Result</title>

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
    <body>

        <div class="container" style="text-align: center;">
            <div style="text-align: center;" class="animated slideInDown">
                <h1><b>Here's the result, Administrator</b></h1>
            </div>
            <form action="admin_panel_result.jsp" method="post" name="admin_panel_result" id="admin_panel_result">
                <%
                    LinkedList<User> userList = (LinkedList<User>)request.getAttribute("users");
                    if(userList == null){
                        userList = new LinkedList<User>();
                    }
                %>
                <table border="1" style="background-color: white;">
                    <tr>
                        <th>User ID</th>
                        <th>User Type</th>
                        <th>User Name</th>
                        <th>Email</th>
                        <th>User Password</th>
                        <th>Real Name</th>
                        <th>Gender</th>
                        <th>Birthday</th>
                        <th>Country</th>
                    </tr>
                    <%
                        for(User user : userList) {
                    %>
                    <tbody align="center" valign="middle">
                    <tr>
                        <td><%= user.getUserId() %></td>
                        <td><%= user.getUserType() %></td>
                        <td><%= user.getUserName() %></td>
                        <td><%= user.getEmail() %></td>
                        <td><%= user.getUserPassword() %></td>
                        <td><%= user.getRealName() %></td>
                        <td><%= user.getGender() %></td>
                        <td><%= user.getBirthday() %></td>
                        <td><%= user.getCountry() %></td>
                    </tr>
                    </tbody><%}%>
                </table>
                <br/>
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

        <form action="/blog/AdminPanelResultControl" method="post" name="execute_form" id="execute_form" onsubmit="return checkLength(this)">
            <!-- DELETE USER-->
            <div style="text-align: center;">
                <h2><b>Delete An User</b></h2><br/>
                <h4><b>Enter the user name to delete an user:</b></h4>
                <span style="color:red">(this action may not be reversed)</span>
            </div>
            <div class="container">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="inner-addon left-addon col-md-6">
                        <i class="glyphicon glyphicon-user"></i>
                        <input type="text" id="delete_user_record" name="deleteUserRecord"
                               class="form-control" form="execute_form" placeholder="User name?"/>
                    </div>
                    <div class="col-md-6">
                        <input type="submit" class="btn btn-warning btn-lg" name="deleteUserAction" value="Delete User" onclick="return deleteUserValidate()"/>
                    </div>
                    <div class="col-md-4"></div>
                </div>
            </div>
            <br/><br/>

            <!-- UPDATE USER PROFILE -->
            <div style="text-align: center;">
                <h2><b>Update User Profile</b></h2><br/>
            </div>
            <div class="container">
                <div class="col-md-4"></div>
                <div class="inner-addon left-addon col-md-4">
                    <i class="glyphicon glyphicon-user"></i>
                    <input type="text" class="form-control" id="user_name" name="userName" form="execute_form" placeholder="User Name?"/>
                </div>
                <div class="col-md-4"></div>
            </div>
            <br/>
            <div class="container">
                <div class="col-md-4"></div>
                <div class="inner-addon left-addon col-md-4">
                    <span style="color:red">(information will be restored if area is empty)</span>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="container">
                <div class="col-md-4"></div>
                <div class="inner-addon left-addon col-md-4">
                    <i class="glyphicon glyphicon-user"></i>
                    <input type="text" class="form-control" name="newUserName" placeholder="New User Name"/>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="container">
                <div class="col-md-4"></div>
                <div class="inner-addon left-addon col-md-4">
                    <i class="glyphicon glyphicon-eye-open"></i>
                    <input type="text" class="form-control" name="newPassword" placeholder="New Password"/>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="container">
                <div class="col-md-4"></div>
                <div class="inner-addon left-addon col-md-4">
                    <i class="glyphicon glyphicon-envelope"></i>
                    <input type="email" name="newEmail" class="form-control" placeholder="New Email"/>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="container">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <input type="text" name="newRealName" class="form-control" placeholder="New Real Name"/>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="container">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <label for="newGender">New Gender</label>
                    <select name="newGender" class="form-control" id="newGender">
                        <option value=""></option>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                        <option value="other">Other</option>
                    </select>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="container">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <label>New Birthday
                        <input type="date" name="newBirthday" class="form-control"/>
                    </label>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="container">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <label for="newCountry">New Country
                        <select name="newCountry" class="form-control" id="newCountry">
                            <option value=""></option>
                            <option value="Afghanistan">Afghanistan</option>
                            <option value="Albania">Albania</option>
                            <option value="Algeria">Algeria</option>
                            <option value="American Samoa">American Samoa</option>
                            <option value="Andorra">Andorra</option>
                            <option value="Angola">Angola</option>
                            <option value="Anguilla">Anguilla</option>
                            <option value="Antarctica">Antarctica</option>
                            <option value="Antigua and Barbuda">Antigua and Barbuda</option>
                            <option value="Argentina">Argentina</option>
                            <option value="Armenia">Armenia</option>
                            <option value="Aruba">Aruba</option>
                            <option value="Australia">Australia</option>
                            <option value="Austria">Austria</option>
                            <option value="Azerbaidjan">Azerbaidjan</option>
                            <option value="Bahamas">Bahamas</option>
                            <option value="Bahrain">Bahrain</option>
                            <option value="Bangladesh">Bangladesh</option>
                            <option value="Barbados">Barbados</option>
                            <option value="Belarus">Belarus</option>
                            <option value="Belgium">Belgium</option>
                            <option value="Belize">Belize</option>
                            <option value="Benin">Benin</option>
                            <option value="Bermuda">Bermuda</option>
                            <option value="Bhutan">Bhutan</option>
                            <option value="Bolivia">Bolivia</option>
                            <option value="Bosnia-Herzegovina">Bosnia-Herzegovina</option>
                            <option value="Botswana">Botswana</option>
                            <option value="Bouvet Island">Bouvet Island</option>
                            <option value="Brazil">Brazil</option>
                            <option value="Brunei Darussalam">Brunei Darussalam</option>
                            <option value="Bulgaria">Bulgaria</option>
                            <option value="Burkina Faso">Burkina Faso</option>
                            <option value="Burundi">Burundi</option>
                            <option value="Cambodia">Cambodia</option>
                            <option value="Cameroon">Cameroon</option>
                            <option value="Canada">Canada</option>
                            <option value="Cape Verde">Cape Verde</option>
                            <option value="Cayman Islands">Cayman Islands</option>
                            <option value="Central African Republic">Central African Republic</option>
                            <option value="Chad">Chad</option>
                            <option value="Chile">Chile</option>
                            <option value="China(中国)">China(中国)</option>
                            <option value="Christmas Island">Christmas Island</option>
                            <option value="Cocos Islands">Cocos Islands</option>
                            <option value="Colombia">Colombia</option>
                            <option value="Comoros">Comoros</option>
                            <option value="Congo">Congo</option>
                            <option value="Cook Islands">Cook Islands</option>
                            <option value="Costa Rica">Costa Rica</option>
                            <option value="Croatia">Croatia</option>
                            <option value="Cuba">Cuba</option>
                            <option value="Cyprus">Cyprus</option>
                            <option value="Czech Republic">Czech Republic</option>
                            <option value="Denmark">Denmark</option>
                            <option value="Djibouti">Djibouti</option>
                            <option value="Dominica">Dominica</option>
                            <option value="Dominican Republic">Dominican Republic</option>
                            <option value="East Timor">East Timor</option>
                            <option value="Ecuador">Ecuador</option>
                            <option value="Egypt">Egypt</option>
                            <option value="El Salvador">El Salvador</option>
                            <option value="Equatorial Guinea">Equatorial Guinea</option>
                            <option value="Estonia">Estonia</option>
                            <option value="Ethiopia">Ethiopia</option>
                            <option value="Falkland Islands">Falkland Islands</option>
                            <option value="Faroe Islands">Faroe Islands</option>
                            <option value="Fiji">Fiji</option>
                            <option value="Finland">Finland</option>
                            <option value="Former Czechoslovakia">Former Czechoslovakia</option>
                            <option value="France">France</option>
                            <option value="French Guyana">French Guyana</option>
                            <option value="Gabon">Gabon</option>
                            <option value="Gambia">Gambia</option>
                            <option value="Georgia">Georgia</option>
                            <option value="Germany">Germany</option>
                            <option value="Ghana">Ghana</option>
                            <option value="Gibraltar">Gibraltar</option>
                            <option value="Great Britain">Great Britain</option>
                            <option value="Greece">Greece</option>
                            <option value="Greenland">Greenland</option>
                            <option value="Grenada">Grenada</option>
                            <option value="Guadeloupe">Guadeloupe</option>
                            <option value="Guam">Guam</option>
                            <option value="Guatemala">Guatemala</option>
                            <option value="Guinea">Guinea</option>
                            <option value="Guinea Bissau">Guinea Bissau</option>
                            <option value="Guyana">Guyana</option>
                            <option value="Haiti">Haiti</option>
                            <option value="Honduras">Honduras</option>
                            <option value="Hong Kong">Hong Kong</option>
                            <option value="Hungary">Hungary</option>
                            <option value="Iceland">Iceland</option>
                            <option value="India">India</option>
                            <option value="Indonesia">Indonesia</option>
                            <option value="Iran">Iran</option>
                            <option value="Iraq">Iraq</option>
                            <option value="Ireland">Ireland</option>
                            <option value="Israel">Israel</option>
                            <option value="Italy">Italy</option>
                            <option value="Ivory Coast">Ivory Coast</option>
                            <option value="Jamaica">Jamaica</option>
                            <option value="Japan">Japan</option>
                            <option value="Jordan">Jordan</option>
                            <option value="Kazakhstan">Kazakhstan</option>
                            <option value="Kenya">Kenya</option>
                            <option value="Kiribati">Kiribati</option>
                            <option value="Kuwait">Kuwait</option>
                            <option value="Kyrgyzstan">Kyrgyzstan</option>
                            <option value="Laos">Laos</option>
                            <option value="Latvia">Latvia</option>
                            <option value="Lebanon">Lebanon</option>
                            <option value="Lesotho">Lesotho</option>
                            <option value="Liberia">Liberia</option>
                            <option value="Libya">Libya</option>
                            <option value="Liechtenstein">Liechtenstein</option>
                            <option value="Lithuania">Lithuania</option>
                            <option value="Luxembourg">Luxembourg</option>
                            <option value="Macau">Macau</option>
                            <option value="Macedonia">Macedonia</option>
                            <option value="Madagascar">Madagascar</option>
                            <option value="Malawi">Malawi</option>
                            <option value="Malaysia">Malaysia</option>
                            <option value="Maldives">Maldives</option>
                            <option value="Mali">Mali</option>
                            <option value="Malta">Malta</option>
                            <option value="Marshall Islands">Marshall Islands</option>
                            <option value="Martinique">Martinique</option>
                            <option value="Mauritania">Mauritania</option>
                            <option value="Mauritius">Mauritius</option>
                            <option value="Mayotte">Mayotte</option>
                            <option value="Mexico">Mexico</option>
                            <option value="Micronesia">Micronesia</option>
                            <option value="Moldavia">Moldavia</option>
                            <option value="Monaco">Monaco</option>
                            <option value="Mongolia">Mongolia</option>
                            <option value="Montserrat">Montserrat</option>
                            <option value="Morocco">Morocco</option>
                            <option value="Mozambique">Mozambique</option>
                            <option value="Myanmar">Myanmar</option>
                            <option value="Namibia">Namibia</option>
                            <option value="Nauru">Nauru</option>
                            <option value="Nepal">Nepal</option>
                            <option value="Netherlands">Netherlands</option>
                            <option value="Netherlands Antilles">Netherlands Antilles</option>
                            <option value="Neutral Zone">Neutral Zone</option>
                            <option value="New Caledonia">New Caledonia</option>
                            <option value="New Zealand">New Zealand</option>
                            <option value="Nicaragua">Nicaragua</option>
                            <option value="Niger">Niger</option>
                            <option value="Nigeria">Nigeria</option>
                            <option value="Niue">Niue</option>
                            <option value="Norfolk Island">Norfolk Island</option>
                            <option value="North Korea">North Korea</option>
                            <option value="Norway">Norway</option>
                            <option value="Oman">Oman</option>
                            <option value="Pakistan">Pakistan</option>
                            <option value="Palau">Palau</option>
                            <option value="Panama">Panama</option>
                            <option value="Papua New Guinea">Papua New Guinea</option>
                            <option value="Paraguay">Paraguay</option>
                            <option value="Peru">Peru</option>
                            <option value="Philippines">Philippines</option>
                            <option value="Pitcairn Island">Pitcairn Island</option>
                            <option value="Poland">Poland</option>
                            <option value="Polynesia">Polynesia</option>
                            <option value="Portugal">Portugal</option>
                            <option value="Puerto Rico">Puerto Rico</option>
                            <option value="Qatar">Qatar</option>
                            <option value="Reunion">Reunion</option>
                            <option value="Romania">Romania</option>
                            <option value="Russian Federation">Russian Federation</option>
                            <option value="Rwanda">Rwanda</option>
                            <option value="Saint Helena">Saint Helena</option>
                            <option value="Saint Lucia">Saint Lucia</option>
                            <option value="Saint Vincent and Grenadines">Saint Vincent and Grenadines</option>
                            <option value="Samoa">Samoa</option>
                            <option value="San Marino">San Marino</option>
                            <option value="Saudi Arabia">Saudi Arabia</option>
                            <option value="Senegal">Senegal</option>
                            <option value="Seychelles">Seychelles</option>
                            <option value="Sierra Leone">Sierra Leone</option>
                            <option value="Singapore">Singapore</option>
                            <option value="Slovak Republic">Slovak Republic</option>
                            <option value="Slovenia">Slovenia</option>
                            <option value="Solomon Islands">Solomon Islands</option>
                            <option value="Somalia">Somalia</option>
                            <option value="South Africa">South Africa</option>
                            <option value="South Korea">South Korea</option>
                            <option value="Spain">Spain</option>
                            <option value="Sri Lanka">Sri Lanka</option>
                            <option value="Sudan">Sudan</option>
                            <option value="Suriname">Suriname</option>
                            <option value="Swaziland">Swaziland</option>
                            <option value="Sweden">Sweden</option>
                            <option value="Switzerland">Switzerland</option>
                            <option value="Syria">Syria</option>
                            <option value="Tadjikistan">Tadjikistan</option>
                            <option value="Taiwan">Taiwan</option>
                            <option value="Tanzania">Tanzania</option>
                            <option value="Thailand">Thailand</option>
                            <option value="Togo">Togo</option>
                            <option value="Tokelau">Tokelau</option>
                            <option value="Tonga">Tonga</option>
                            <option value="Trinidad and Tobago">Trinidad and Tobago</option>
                            <option value="Tunisia">Tunisia</option>
                            <option value="Turkey">Turkey</option>
                            <option value="Turkmenistan">Turkmenistan</option>
                            <option value="Tuvalu">Tuvalu</option>
                            <option value="Uganda">Uganda</option>
                            <option value="Ukraine">Ukraine</option>
                            <option value="United Arab Emirates">United Arab Emirates</option>
                            <option value="United Kingdom">United Kingdom</option>
                            <option value="United States">United States</option>
                            <option value="Uruguay">Uruguay</option>
                            <option value="Uzbekistan">Uzbekistan</option>
                            <option value="Vanuatu">Vanuatu</option>
                            <option value="Vatican City State">Vatican City State</option>
                            <option value="Venezuela">Venezuela</option>
                            <option value="Vietnam">Vietnam</option>
                            <option value="Virgin Islands (British)">Virgin Islands (British)</option>
                            <option value="Virgin Islands (USA)">Virgin Islands (USA)</option>
                            <option value="Wallis and Futuna Islands">Wallis and Futuna Islands</option>
                            <option value="Western Sahara">Western Sahara</option>
                            <option value="Yemen">Yemen</option>
                            <option value="Yugoslavia">Yugoslavia</option>
                            <option value="Zaire">Zaire</option>
                            <option value="Zambia">Zambia</option>
                            <option value="Zimbabwe">Zimbabwe</option>
                        </select>
                    </label>
                </div>
                <div class="col-md-4"></div>
            </div>
            <br/>
            <div class="container" style="text-align: center;">
                <input type="submit" class="btn btn-warning btn-lg" name="updateUserAction" value="Update User" onclick="return updateUserValidate()"/>
            </div>
            <br/><br/>

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
                               class="form-control" form="execute_form" placeholder="Post ID?"/>
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
                            <input type="number" class="form-control" name="searchPostId" form="execute_form"  min="1" max="999999999" placeholder="Post ID?"/>
                            <input type="hidden" class="form-control" name="thisPostId" value="<%= selectedPost.getId() %>" form="execute_form"/>
                        </div>
                        <div class="col-md-6">
                            <input class="btn btn-default btn-lg" type="submit" name="searchPostAction" value="Get This Post" form="execute_form" onclick="return updatePostValidate()"/>
                        </div>
                    </div>
                    <div class="col-md-4"></div>
                </div>
            </div>
            <br/>
            <div class="container">
                <div class="col-md-6">
                    <label>New Post Title
                        <textarea class="form-control" name="newPostTitle" form="execute_form"><%= selectedPost.getTitle() %></textarea>
                    </label>
                    <%--<script>
                        CKEDITOR.replace("newPostTitle");
                    </script>--%>
                </div>
                <div class="col-md-6">
                    <label>New Post Body
                        <textarea class="form-control" name="newPostBody" cols="100" rows="20" form="execute_form"><%= selectedPost.getBody() %></textarea>
                    </label>
                    <%--<script>
                        CKEDITOR.replace("newPostBody");
                    </script>--%>
                </div>
            </div>
            <br/>
            <div class="container" style="text-align: center;">
                <input type="submit" class="btn btn-warning btn-lg" name="updatePostAction" value="Update This Post" form="execute_form"/>
            </div>
            <br/><br/>
        </form>

        <div class="container" style="text-align: center;">
            <a href="admin_panel.jsp" class="btn btn-primary btn-lg" role="button">Go Back To Panel</a>
            <a href="main.jsp" class="btn btn-info btn-lg" role="button">Log Out</a>
        </div>

        <script language="javascript">
            function deleteUserValidate(){
                if (document.execute_form.deleteUserRecord.value==""){
                    alert("Please enter a value to delete an user!");
                    document.execute_form.deleteUserRecord.focus();
                    return false;
                }
            }
            function deletePostValidate(){
                if (document.execute_form.deletePostRecord.value==""){
                    alert("Please enter a value to delete a post!");
                    document.execute_form.deletePostRecord.focus();
                    return false;
                }
            }
            function updateUserValidate(){
                if (document.execute_form.userName.value==""){
                    alert("Please enter a value to update an user!");
                    document.execute_form.userName.focus();
                    return false;
                }
            }
            function updatePostValidate(){
                if (document.execute_form.searchPostId.value==""){
                    alert("Please enter a post ID!");
                    document.execute_form.searchPostId.focus();
                    return false;
                }
            }
            function checkLength(form){
                if (form.newPostTitle.value.length > 100){
                    alert("Text too long. Must be 100 characters or less");
                    return false;
                }
                if (form.newPostBody.value.length > 2000){
                    alert("Text too long. Must be 2000 characters or less");
                    return false;
                }
                return true;
            }
        </script>

    </body>
</html>
