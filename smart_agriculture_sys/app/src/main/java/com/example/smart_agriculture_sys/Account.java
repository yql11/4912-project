package com.example.smart_agriculture_sys;

public class Account {
    public String username;
    public String password;
    public String email;

    public Account() {
        // Default constructor required for Firebase
    }

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
