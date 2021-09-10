package com.example.myfirstproject.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.example.myfirstproject.MainActivity;
import com.example.myfirstproject.R;
import com.example.myfirstproject.model.User;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

//创建一个页面，必须要继承至：AppCompatActivity
public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //快捷键：alt+enter 创建类
        setContentView(R.layout.activity_splash);

        //延时操作，启动项目不立即跳主页面，使用Timer实现
        Timer timer = new Timer();
        timer.schedule(timerTask,2000);

        //Bmob初始化
        Bmob.initialize(this,"0648778bf9938a804f1851e8cb434a59");

    }

    //延时操作，启动项目不立即跳主页面
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            //startActivity(new Intent(Splash.this, MainActivity.class));

            //如果以登录，跳转到首页，没有登录跳转到登录页面

            BmobUser bmobUser = BmobUser.getCurrentUser(User.class);
            if(bmobUser != null){
                //表示登录了，跳转到主界面
                startActivity(new Intent(Splash.this,MainActivity.class));
                finish();
            }else{
                startActivity(new Intent(Splash.this,Login.class));
                finish();
            }
        }
    };
}
