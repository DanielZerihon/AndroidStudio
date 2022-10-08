package com.example.new_final_project.Classes;

public class User {

    public String heading;
    public String email;
    public String facebook;
    public String date;
    public String phone;
    public String imgageAccessToken;



    public User(){

    }

    public User(String heading, String email, String facebook, String date, String phone, String imgageAccessToken) {
        this.heading = heading;
        this.email = email;
        this.facebook = facebook;
        this.date = date;
        this.phone = phone;
        this.imgageAccessToken = imgageAccessToken;
    }
}
