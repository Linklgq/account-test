package com.example.lenovo.logintest.data.source.account;

import com.example.lenovo.logintest.account.AccountHelper;
import com.example.lenovo.logintest.data.User;

public class AccountRepository implements AccountHelper.AccountDataSource {
    private static volatile AccountRepository sInstance;

    private final UserDao mUserDao;

    private AccountRepository(){
        mUserDao=UserDatabase.getInstance().accountDao();
    }

    public static AccountRepository getInstance(){
        if(sInstance==null){
            synchronized (AccountRepository.class){
                if(sInstance==null)
                    sInstance=new AccountRepository();
            }
        }
        return sInstance;
    }

    @Override
    public boolean isUserExist(String username) {
        return mUserDao.getUserByName(username)!=null;
    }

    @Override
    public boolean matchPassword(String username, String password) {
        return mUserDao.getUser(username,password)!=null;
    }

    @Override
    public boolean matchVerifyCode(String username, String verifyCode) {
        // FIXME: 2018/11/3
        return true;
    }

    @Override
    public void addUser(String username, String password) {
        mUserDao.insertUser(new User(username,password));
    }

    @Override
    public void modifyPassword(String username, String newPassword) {
        User user=mUserDao.getUserByName(username);
        user.setPassword(newPassword);
        mUserDao.updateUser(user);
    }
}
