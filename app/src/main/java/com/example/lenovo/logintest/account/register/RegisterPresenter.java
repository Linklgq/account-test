package com.example.lenovo.logintest.account.register;

import android.content.res.Resources;
import android.text.TextUtils;

import com.example.lenovo.logintest.MyApplication;
import com.example.lenovo.logintest.R;
import com.example.lenovo.logintest.account.AccountHelper;

public class RegisterPresenter implements RegisterContract.Presenter {
    RegisterContract.View mView;
    AccountHelper.AccountDataSource mModel;

    public RegisterPresenter(RegisterContract.View view, AccountHelper.AccountDataSource model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void register(String username, String password, String verifyCode) {
        if(TextUtils.isEmpty(username)){
            mView.usernameIsEmpty();
            return;
        }else if(TextUtils.isEmpty(password)){
            mView.passwordIsEmpty();
            return;
        }else if(TextUtils.isEmpty(verifyCode)){
            mView.verifyCodeIsEmpty();
            return;
        }

        Resources res=MyApplication.getContext().getResources();
        int usernameLength= res.getInteger(R.integer.length_username);
        int passwordLength=res.getInteger(R.integer.length_password);
        int verifyCodeLength=res.getInteger(R.integer.length_verifyCode);

        if(username.length()!=usernameLength)
            mView.usernameFormatError();
        else if(password.length()>passwordLength)
            mView.passwordFormatError();
        else if(verifyCode.length()!=verifyCodeLength)
            mView.verifyCodeFormatError();
        else if(mModel.isUserExist(username))
            mView.usernameExist();
        else if(mModel.matchVerifyCode(username,verifyCode)) {
            mModel.addUser(username,password);
            mView.registerSuccess();
        } else
            mView.verifyCodeError();
    }

    @Override
    public void start() {

    }
}
