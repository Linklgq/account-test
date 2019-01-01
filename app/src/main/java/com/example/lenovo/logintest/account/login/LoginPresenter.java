package com.example.lenovo.logintest.account.login;

import android.text.TextUtils;

import com.example.lenovo.logintest.MyApplication;
import com.example.lenovo.logintest.R;
import com.example.lenovo.logintest.account.AccountHelper;

public class LoginPresenter implements LoginContract.Presenter{
    LoginContract.View mView;
    AccountHelper.AccountDataSource mModel;

    public LoginPresenter(LoginContract.View view, AccountHelper.AccountDataSource model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void login(String username, String password) {
        if(TextUtils.isEmpty(username)){
            mView.usernameIsEmpty();
            return;
        }else if(TextUtils.isEmpty(password)){
            mView.passwordIsEmpty();
            return;
        }

        int usernameLength= MyApplication.getContext().getResources()
                .getInteger(R.integer.length_username);
        int passwordLength=MyApplication.getContext().getResources()
                .getInteger(R.integer.length_password);

        if(username.length()!=usernameLength)
            mView.usernameFormatError();
        else if(password.length()>passwordLength)
            mView.passwordFormatError();
        else if(!mModel.isUserExist(username))
            mView.usernameNotExist();
        else if(mModel.matchPassword(username,password))
            mView.loginSuccess();
        else
            mView.passwordError();
    }

    @Override
    public void start() {

    }
}
