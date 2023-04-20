package com.example.smart_agriculture_sys;

public class User {
    private String firstName;
    private String lastName;
    private String emailaddress;
    private String username;
    private String password;


    public User() {

    }

    public User(String username, String emailaddress, String password, String firstName, String lastName) {
        this.username = username;
        this.emailaddress= emailaddress;
        this.password= password;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
