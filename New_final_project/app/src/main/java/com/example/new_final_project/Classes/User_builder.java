package com.example.new_final_project.Classes;

public class User_builder {
    public String heading;
    public String facebook;
    public String phone;
    public String date;
    public String email;
    public String imgageAccessToken;

    public String getEmail() {
        return email;
    }

    public String getImgageAccessToken() {
        return imgageAccessToken;
    }

    public String getHeading() {
        return heading;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getPhone() {
        return phone;
    }

    public String getDate() {
        return date;
    }

    public User_builder() {
    }

    public User_builder(Builder builder) {
        this.heading = builder.heading;
        this.facebook = builder.facebook;
        this.phone = builder.phone;
        this.date = builder.date;
        this.email = builder.Email;
        this.imgageAccessToken = builder.imgageAccessToken;
    }

    @Override
    public String toString() {
        return "News_short{" +
                " heading='" + heading + '\'' +
                ", facebook='" + facebook + '\'' +
                ", phone='" + phone + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public static class Builder {
        private String heading;
        private String facebook;
        private String phone;
        private String date;
        public String Email;
        public String imgageAccessToken;


        public Builder Email(String Email) {
            this.Email = Email;
            return this;
        }

        public Builder heading(String heading){
            this.heading = heading;
            return this;
        }

        public Builder imgageAccessToken(String imgageAccessToken){
            this.imgageAccessToken = imgageAccessToken;
            return this;
        }

        public Builder facebook(String facebook){
            this.facebook = facebook;
            return this;
        }
        public Builder phone(String phone){
            this.phone = phone;
            return this;
        }
        public Builder date(String date){
            this.date = date;
            return this;
        }
        public User_builder build(){
            return new User_builder(this);
        }
    }


}
