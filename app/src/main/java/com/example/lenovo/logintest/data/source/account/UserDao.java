package com.example.lenovo.logintest.data.source.account;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.lenovo.logintest.data.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users WHERE username=:username LIMIT 1")
    User getUserByName(String username);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Query("SELECT * FROM users WHERE username=:username AND password=:password LIMIT 1")
    User getUser(String username,String password);
}
