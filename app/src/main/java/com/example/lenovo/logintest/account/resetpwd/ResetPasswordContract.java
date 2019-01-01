package com.example.lenovo.logintest.account.resetpwd;

import com.example.lenovo.logintest.BasePresenter;
import com.example.lenovo.logintest.BaseView;

public interface ResetPasswordContract {
    interface View extends BaseView<Presenter>{
        void usernameIsEmpty();

        void passwordIsEmpty();

        void usernameFormatError();

        void passwordFormatError();

        void verifyCodeIsEmpty();

        void verifyCodeFormatError();

        void verifyCodeError();

        void resetSuccess();

        void usernameNotExist();
    }

    interface Presenter extends BasePresenter{
        void reset(String username,String password,String verifyCode);
    }
}
