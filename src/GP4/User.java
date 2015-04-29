package GP4;

import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private ArrayList<String> phoneNumbers;

    public User(){
    }

    public String getName(){
        return name;
    }

    public void setName(String value){
        name = value;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String value){
        email = value;
    }

    public ArrayList<String> getPhoneNumbers(){
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<String> value){
        phoneNumbers = value;
    }
}
