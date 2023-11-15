package com.example.secondtrainep;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {


    @Insert
    void insert(User user);

    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    User getUser(String username, String password);
}
