package com.example.secondtrainep;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
        @PrimaryKey(autoGenerate = true)
        public int id;
        public String username;
        public String password;
        public  String email;




        public User(String username, String password, String email) {
                this.username = username;
                this.password = password;
                this.email = email;
        }

        public User(String usernameEditText, String passwordEditText) {

        }


        // Constructors, getters, setters


        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }
}
