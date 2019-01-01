package com.example.lenovo.logintest.account.register;

import com.example.lenovo.logintest.BasePresenter;
import com.example.lenovo.logintest.BaseView;

public interface RegisterContract {
    interface View extends BaseView<Presenter>{
        void usernameIsEmpty();

        void passwordIsEmpty();

        void usernameFormatError();

        void passwordFormatError();

        void verifyCodeIsEmpty();

        void verifyCodeFormatError();

        void verifyCodeError();

        void usernameExist();

        void registerSuccess();
    }

    interface Presenter extends BasePresenter{
        void register(String username,String password,String verifyCode);
    }
}
