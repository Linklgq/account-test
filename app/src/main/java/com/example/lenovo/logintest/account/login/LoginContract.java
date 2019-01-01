package com.example.lenovo.logintest.account.login;

import com.example.lenovo.logintest.BasePresenter;
import com.example.lenovo.logintest.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter>{
        void usernameIsEmpty();

        void passwordIsEmpty();

        void usernameFormatError();

        void passwordFormatError();

        void passwordError();

        void usernameNotExist();

        void loginSuccess();
    }

    interface Presenter extends BasePresenter{
        void login(String username,String password);
    }
}
