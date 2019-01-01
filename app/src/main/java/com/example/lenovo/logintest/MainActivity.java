package com.example.lenovo.logintest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lenovo.logintest.account.login.LoginActivity;

//public class MainActivity extends AppCompatActivity {
//    SMSManager smsManager;
//
//    EditText code;
//    Button get;
//    Button send;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        smsManager=SMSManager.getInstance();
//
//        code=findViewById(R.id.edt_code);
//        get=findViewById(R.id.btn_get);
//        send=findViewById(R.id.btn_send);
//
//        get.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                smsManager.sendMessage(MainActivity.this,"86","13760251309");
//            }
//        });
//
//        send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                smsManager.verifyCode(MainActivity.this, "86", "13760251309",
//                        code.getText().toString(), new Callback() {
//                            @Override
//                            public void success() {
//                                Toast.makeText(MainActivity.this, "succes", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void error(Throwable error) {
//                                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            }
//        });
//    }
//}
public class MainActivity extends AppCompatActivity {
    Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}