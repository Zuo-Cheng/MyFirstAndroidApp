package com.example.myfirstproject.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.example.myfirstproject.R;
import com.example.myfirstproject.model.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Register extends AppCompatActivity {

    private EditText text_username;

    private EditText text_password;

    private EditText text_nickname;

    private Button btn_register;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        text_username = (EditText)findViewById(R.id.text_register_username);
        text_password = (EditText)findViewById(R.id.text_register_password);
        text_nickname = (EditText)findViewById(R.id.text_register_nickname);
        btn_register = (Button) findViewById(R.id.btn_register_register);

        //注册监听
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User();
                user.setUsername(text_username.getText().toString().trim());
                user.setPassword(text_password.getText().toString());
                user.setNickName(text_nickname.getText().toString());

                if(!CheckString(text_username.getText().toString().trim())){
                    Toast.makeText(Register.this, "用户名没有输入", Toast.LENGTH_SHORT).show();
                }else if(!CheckString(text_password.getText().toString())){
                    Toast.makeText(Register.this, "密码没有输入", Toast.LENGTH_SHORT).show();
                }else if(!CheckString(text_nickname.getText().toString())){
                    Toast.makeText(Register.this, "昵称没有输入", Toast.LENGTH_SHORT).show();
                }else{

                    //注册回调
                    user.signUp(new SaveListener<User>() {
                        @Override
                        public void done(User user, BmobException e) {
                            if(e == null){
                                Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(Register.this, "注册失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });
    }


    public boolean CheckString(String str){

        if(str.isEmpty()){
            return false;
        }

        return true;
    }


}
