package com.example.lenovo.logintest.account.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.logintest.R;
import com.example.lenovo.logintest.account.AccountHelper;
import com.example.lenovo.logintest.account.register.RegisterActivity;
import com.example.lenovo.logintest.account.resetpwd.ResetPasswordActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{
    private String mErrorFormatUsername;
    private String mErrorFormatPassword;
    private String mErrorPassword;
    private String mNotExistUsername;
    private String mEmptyUsername;
    private String mEmptyPassword;

    private LoginContract.Presenter mPresenter;

    private Button mLogin;
    private TextView mRegister;
    private TextView mForgetPassword;

    private TextInputLayout mUsername;
    private TextInputLayout mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPresenter=new LoginPresenter(this,AccountHelper.getAccountDataSource());

        mLogin=findViewById(R.id.btn_login);
        mRegister=findViewById(R.id.tx_register);
        mForgetPassword=findViewById(R.id.tx_forgetPassword);

        mUsername=findViewById(R.id.txil_username);
        mPassword=findViewById(R.id.txil_password);

        mErrorFormatUsername=getString(R.string.errorFormat_username);
        mErrorFormatPassword=getString(R.string.errorFormat_password);
        mErrorPassword=getString(R.string.error_password);
        mNotExistUsername=getString(R.string.notExist_username);
        mEmptyUsername=getString(R.string.empty_username);
        mEmptyPassword=getString(R.string.empty_password);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=mUsername.getEditText().getText().toString();
                String password=mPassword.getEditText().getText().toString();
                mUsername.setErrorEnabled(false);
                mPassword.setErrorEnabled(false);
                mPresenter.login(username,password);
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        mForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void usernameFormatError() {
        mUsername.setError(mErrorFormatUsername);
    }

    @Override
    public void passwordFormatError() {
        mPassword.setError(mErrorFormatPassword);
    }

    @Override
    public void passwordError() {
        mPassword.setError(mErrorPassword);
    }

    @Override
    public void usernameNotExist() {
        mUsername.setError(mNotExistUsername);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }

    @Override
    public void usernameIsEmpty() {
        mUsername.setError(mEmptyUsername);
    }

    @Override
    public void passwordIsEmpty() {
        mPassword.setError(mEmptyPassword);
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
