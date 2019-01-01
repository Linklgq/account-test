package com.example.lenovo.logintest.data.source.account;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.example.lenovo.logintest.MyApplication;
import com.example.lenovo.logintest.data.User;

@Database(entities = {User.class},version = 1)
public abstract class UserDatabase extends RoomDatabase{
    private static volatile UserDatabase sInstance;

    public abstract UserDao accountDao();

    public static UserDatabase getInstance(){
        if(sInstance==null){
            synchronized (UserDatabase.class){
                if(sInstance==null)
                    sInstance= Room.databaseBuilder(MyApplication.getContext(),
                            UserDatabase.class,"users.db")
                            .allowMainThreadQueries()
                            .build();
            }
        }
        return sInstance;
    }
}
