package com.example.lenovo.logintest.account.resetpwd;

import android.content.res.Resources;
import android.text.TextUtils;

import com.example.lenovo.logintest.MyApplication;
import com.example.lenovo.logintest.R;
import com.example.lenovo.logintest.account.AccountHelper;

public class ResetPasswordPresenter implements ResetPasswordContract.Presenter {
    ResetPasswordContract.View mView;
    AccountHelper.AccountDataSource mModel;

    public ResetPasswordPresenter(ResetPasswordContract.View view, AccountHelper.AccountDataSource model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void reset(String username, String password, String verifyCode) {
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

        Resources res= MyApplication.getContext().getResources();
        int usernameLength= res.getInteger(R.integer.length_username);
        int passwordLength=res.getInteger(R.integer.length_password);
        int verifyCodeLength=res.getInteger(R.integer.length_verifyCode);

        if(username.length()!=usernameLength)
            mView.usernameFormatError();
        else if(password.length()>passwordLength)
            mView.passwordFormatError();
        else if(verifyCode.length()!=verifyCodeLength)
            mView.verifyCodeFormatError();
        else if(!mModel.isUserExist(username))
            mView.usernameNotExist();
        else if(mModel.matchVerifyCode(username,verifyCode)) {
            mModel.modifyPassword(username,password);
            mView.resetSuccess();
        } else
            mView.verifyCodeError();
    }

    @Override
    public void start() {

    }
}
