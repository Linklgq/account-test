package com.example.lenovo.logintest.account.resetpwd;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lenovo.logintest.R;
import com.example.lenovo.logintest.account.AccountHelper;

public class ResetPasswordActivity extends AppCompatActivity implements ResetPasswordContract.View{
    private String mErrorFormatUsername;
    private String mErrorFormatPassword;
    private String mEmptyUsername;
    private String mEmptyPassword;
    private String mErrorFormatVerifyCode;
    private String mEmptyVerifyCode;
    private String mErrorVerifyCode;
    private String mNotExistUsername;

    private String mSent;
    private String mReSend;

    private ResetPasswordContract.Presenter mPresenter;

    private TextInputLayout mUsername;
    private TextInputLayout mPassword;
    private TextInputLayout mVerifyCode;

    private Button mGetVerifyCode;
    private Button mReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mPresenter=new ResetPasswordPresenter(this, AccountHelper.getAccountDataSource());

        mErrorFormatUsername=getString(R.string.errorFormat_username);
        mErrorFormatPassword=getString(R.string.errorFormat_password);
        mEmptyUsername=getString(R.string.empty_username);
        mEmptyPassword=getString(R.string.empty_password);
        mErrorFormatVerifyCode=getString(R.string.errorFormat_verifyCode);
        mEmptyVerifyCode=getString(R.string.empty_verifyCode);
        mErrorVerifyCode=getString(R.string.error_verifyCode);
        mNotExistUsername=getString(R.string.notExist_username);

        mSent=getString(R.string.sent);
        mReSend=getString(R.string.resend);

        mUsername=findViewById(R.id.txil_username);
        mPassword=findViewById(R.id.txil_password);
        mVerifyCode=findViewById(R.id.txil_verifyCode);

        mGetVerifyCode=findViewById(R.id.btn_getVerifyCode);
        mReset=findViewById(R.id.btn_reset);

        mGetVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FIXME: 2018/11/3
                CountDownTimer timer=new CountDownTimer(60*1000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        mGetVerifyCode.setEnabled(false);
                        mGetVerifyCode.setText(mSent+"("+(millisUntilFinished/1000)+")");
                    }

                    @Override
                    public void onFinish() {
                        mGetVerifyCode.setEnabled(true);
                        mGetVerifyCode.setText(mReSend);
                    }
                };
                timer.start();
            }
        });

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=mUsername.getEditText().getText().toString();
                String password=mPassword.getEditText().getText().toString();
                String verifyCode=mVerifyCode.getEditText().getText().toString();
                mUsername.setErrorEnabled(false);
                mPassword.setErrorEnabled(false);
                mVerifyCode.setErrorEnabled(false);
                mPresenter.reset(username,password,verifyCode);
            }
        });
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
    public void usernameFormatError() {
        mUsername.setError(mErrorFormatUsername);
    }

    @Override
    public void passwordFormatError() {
        mPassword.setError(mErrorFormatPassword);
    }

    @Override
    public void verifyCodeIsEmpty() {
        mVerifyCode.setError(mEmptyVerifyCode);
    }

    @Override
    public void verifyCodeFormatError() {
        mVerifyCode.setError(mErrorFormatVerifyCode);
    }

    @Override
    public void verifyCodeError() {
        mVerifyCode.setError(mErrorVerifyCode);
    }

    @Override
    public void resetSuccess() {
        Toast.makeText(this, "重置密码成功", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void usernameNotExist() {
        mUsername.setError(mNotExistUsername);
    }

    @Override
    public void setPresenter(ResetPasswordContract.Presenter presenter) {

    }
}
