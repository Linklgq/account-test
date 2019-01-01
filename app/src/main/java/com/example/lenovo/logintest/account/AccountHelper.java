package com.example.lenovo.logintest.account;

import android.support.annotation.NonNull;

public class AccountHelper {
    public interface AccountDataSource{
        boolean isUserExist(String username);

        boolean matchPassword(String username,String password);

        boolean matchVerifyCode(String username,String verifyCode);

        void addUser(String username,String password);

        void modifyPassword(String username,String newPassword);
    }

    private static AccountDataSource sAccountDataSource=null;

    public static void initAccountDataSource(@NonNull AccountDataSource accountDataSource){
        sAccountDataSource=accountDataSource;
    }

    @NonNull
    public static AccountDataSource getAccountDataSource(){
        return sAccountDataSource;
    }
}
