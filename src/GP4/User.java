package GP4;

public class User {
    private int flags;
    private String userName;
    private String email;
    private String userPassword;
    private String realName;
    private String sex;
    private String birthday;
    private String country;

    public User(){
    }

    public int getflags(){
        return flags;
    }

    public void setflags(int value){
        flags = value;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String value){
        userName = value;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String value){
        email = value;
    }

    public String getUserPassword(){
        return userPassword;
    }

    public void setUserPassword(String value){
        userPassword = value;
    }

    public String getRealName(){
        return realName;
    }

    public void setRealName(String value){
        realName = value;
    }

    public String getSex(){
        return sex;
    }

    public void setSex(String value){
        sex = value;
    }

    public String getBirthday(){
        return birthday;
    }

    public void setBirthday(String value){
        birthday = value;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String value){
        country = value;
    }
}
