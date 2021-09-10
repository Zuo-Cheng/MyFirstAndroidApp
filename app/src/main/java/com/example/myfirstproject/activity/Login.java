package com.example.myfirstproject.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.myfirstproject.MainActivity;
import com.example.myfirstproject.R;
import com.example.myfirstproject.model.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Login extends AppCompatActivity {

    private Button btn_login;

    private Button btn_register;

    private EditText text_username;

    private EditText text_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button)findViewById(R.id.btn_login_login);
        btn_register = (Button)findViewById(R.id.btn_login_register);
        text_username = (EditText)findViewById(R.id.text_login_username);
        text_password = (EditText)findViewById(R.id.text_login_password);

        //登录监听
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("login start");
                //登录验证的方法
                User user = new User();
                user.setUsername(text_username.getText().toString().trim());
                user.setPassword(text_password.getText().toString());
                user.login(new SaveListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if(e == null){
                            Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
                            //页面跳转
                            startActivity(new Intent(Login.this, MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(Login.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Login.this,Register.class));
            }
        });



    }

}
