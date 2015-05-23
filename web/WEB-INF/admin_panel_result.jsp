
<%@ page import="se.molk.blog.domain.User" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="se.molk.blog.domain.Post" %>
<%@ page import="javafx.geometry.Pos" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Admin Panel Result</title>
    </head>
    <body>

        <div class="container" style="text-align: center;">
            <h1>Here's the result, Administrator</h1>
            <form action="admin_panel_result.jsp" method="post">
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
            </form>
        </div>
        <hr/>
        <div class="container" style="text-align: center;">
            <form action="admin_panel_result.jsp" method="post">
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
                        <td><%= post.getDate() %></td>
                    </tr>
                    </tbody><%}%>
                </table>
            </form>
        </div>
        <hr/>
        <div class="container" style="text-align: center;">
            <form action="/blog/AdminPanelResultControl" method="post" name="execute_form" id="execute_form">
                <table align="center" style="background-color: white;">
                    <tr><th><h2>Delete an user</h2></th></tr>
                    <tr>
                        <td colspan=2>Enter the user name to delete an user:
                        <span style="color:red">(this action may not be reversed)</span></td></tr>
                    <tr>
                        <td>
                            <input type="text" id="delete_user_record" name="deleteUserRecord" placeholder="Enter an user name"/>
                            <input type="submit" name="deleteUserAction" value="Delete User" onclick="return deleteUserValidate()"/>
                        </td>
                    </tr>
                    <tr><th><h2>Update user profile</h2></th></tr>
                    <tr>
                        <td colspan=2>Which user need to be updated?<br/>(information will be restored if area is empty)</td>
                    </tr>
                    <tr>
                        <td><input type="text" id="user_name" name="userName" placeholder="User Name"/></td>
                    </tr>
                    <tr>
                        <td>New User Name</td><td><input type="text" name="newUserName" placeholder="New User Name"/></td>
                    </tr>
                    <tr>
                        <td>New Password</td><td><input type="text" name="newPassword" placeholder="New Password"/></td>
                    </tr>
                    <tr>
                        <td>New Email</td><td><input type="email" name="newEmail" placeholder="New Email"/></td>
                    </tr>
                    <tr>
                        <td>New Real Name</td><td><input type="text" name="newRealName" placeholder="New Real Name"/></td>
                    </tr>
                    <tr>
                        <td>New Gender</td><td>
                        <select name="newGender">
                            <option value=""></option>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                            <option value="other">Other</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td>New Birthday</td><td><input type="date" name="newBirthday"/></td>
                    </tr>
                    <tr>
                        <td>New Country</td><td>
                        <select name="newCountry">
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
                        </select></td>
                    </tr>
                    <tr>
                        <td colspan=2><input type="submit" name="updateUserAction" value="Update User" onclick="return updateUserValidate()"/></td>
                    </tr>

                    <tr><th><h2>Delete a post</h2></th></tr>
                    <td colspan=2>Enter the post id to delete a post:
                        <span style="color:red">(this action may not be reversed)</span></td>
                    <br/>
                    <tr>
                        <td>
                            <input type="text" id="delete_post_record" name="deletePostRecord" placeholder="Enter a post id"/>
                            <input type="submit" name="deletePostAction" value="Delete Post" onclick="return deletePostValidate()"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <hr/>
        <div class="container" style="text-align: center;">
            <button><a href="admin_panel.jsp">Go back to panel</a></button>
            <button><a href="main.jsp">Log out</a></button>
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
        </script>

    </body>
</html>
