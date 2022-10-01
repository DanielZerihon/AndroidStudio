package com.example.new_final_project.Classes;

public class News {
    int titleImage;
    int ID_Image;
    String heading;
    String facebook;
    String phone;
    String date;

    public News(String heading, int titleImage, String facebook, String phone, String Date, int ID_Image) {
        this.heading = heading;
        this.titleImage = titleImage;
        this.facebook = facebook;
        this.phone = phone;
        this.date = Date;
        this.ID_Image = ID_Image;

    }
}
