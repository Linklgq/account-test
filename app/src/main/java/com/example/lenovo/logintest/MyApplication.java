package com.example.lenovo.logintest;

import android.app.Application;
import android.content.Context;

import com.example.lenovo.logintest.account.AccountHelper;
import com.example.lenovo.logintest.data.source.account.AccountRepository;
import com.tencent.bugly.crashreport.CrashReport;

public class MyApplication  extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        AccountHelper.initAccountDataSource(AccountRepository.getInstance());
        CrashReport.initCrashReport(getApplicationContext(), "92ff05ece2", true);
    }

    public static Context getContext(){
        return mContext;
    }
}
