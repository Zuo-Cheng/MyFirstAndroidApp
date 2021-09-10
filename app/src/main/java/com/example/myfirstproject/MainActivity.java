package com.example.myfirstproject;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.myfirstproject.adapter.SectionsPagerAdapter;
import com.example.myfirstproject.fragment.FragmentForum;
import com.example.myfirstproject.fragment.FragmentHome;
import com.example.myfirstproject.fragment.FragmentMine;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    private ViewPager main_viewpager;

    private BottomNavigationBar main_btmnav;

    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_btmnav = findViewById(R.id.main_btmnav);
        main_viewpager  = findViewById(R.id.main_viewpager);

        InitView();

//
//        text_username = findViewById(R.id.text_main_username);
//        text_nickname = findViewById(R.id.text_main_nickname);
//
//        //查询，获得登录的用户
//        BmobUser user = BmobUser.getCurrentUser(User.class);
//        String userId = user.getObjectId();
//
//        //通过id查询用户数据
//        BmobQuery<User> myUser = new BmobQuery<User>();
//        myUser.getObject(userId, new QueryListener<User>() {
//            @Override
//            public void done(User user, BmobException e) {
//                //没有异常表示查找成功
//                if(e == null){
//                    text_username.setText(user.getUsername());
//                    text_nickname.setText(user.getNickName());
//                }else{
//                    Toast.makeText(MainActivity.this, "没有当前登录用户", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    /*
     *
     * 初始化代码
     * */
    private void InitView(){
        InitViewPager();

        InitBottomNavigationBar();
    }

    //初始化viewPager视图控件
    private void InitViewPager(){
        //加载当前页面的时候，也一并缓存当前页面的左右俩个页面，这样滑动的时候就不会卡了
        main_viewpager.setOffscreenPageLimit(3);

        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new FragmentHome());
        fragmentList.add(new FragmentForum());
        fragmentList.add(new FragmentMine());

        main_viewpager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(),fragmentList));

        //添加页面切换事件
        main_viewpager.addOnPageChangeListener(this);
        //设置默认页面
        main_viewpager.setCurrentItem(0);
    }


    //初始化底部导航
    //注意这个注解
    @SuppressLint("ResourceType")
    private void InitBottomNavigationBar(){
        //设置导航栏选项选择监听事件。
        main_btmnav.setTabSelectedListener(this);
        main_btmnav.clearAll();
        //设置自适应大小
        main_btmnav.setMode(BottomNavigationBar.MODE_FIXED);
        //设置背景样式
        main_btmnav.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);

        //设置背景颜色,从资源文件(colors)中获得颜色
        int navigation_background = getResources().getColor(R.color.white);
        main_btmnav.setBackgroundColor(navigation_background);

        //设置导航选项选中时的颜色
        main_btmnav.setActiveColor(R.color.navigationBar_activity);

        //设置导航选项没有选中时的颜色
        //注意这里要添加一个：disable “ResourceType”，才能正常运行。
        main_btmnav.setActiveColor(R.color.black);

        //添加导航项，
        //new BottomNavigationItem();第一个参数是图标，第二个参数是文字
        // 点击时选项显示的状态
        //setInactiveIconResource();
        // 是设置没有点击时显示的图标
        main_btmnav.addItem(
                new BottomNavigationItem(R.drawable.main_btmnav_activity_home,"首页")
                        .setInactiveIconResource(R.drawable.main_btmnav_noactivity_home)
        );
        main_btmnav.addItem(
                new BottomNavigationItem(R.drawable.main_btmnav_activity_forum,"论坛")
                        .setInactiveIconResource(R.drawable.main_btmnav_noactivity_forum)
        );
        main_btmnav.addItem(
                new BottomNavigationItem(R.drawable.main_btmnav_activity_mine,"我的")
                        .setInactiveIconResource(R.drawable.main_btmnav_noactivity_mine)
        );

        //设置导航栏选项默认选中哪一个
        main_btmnav.setFirstSelectedPosition(0);

        //导航栏初始化!!!!!!!!
        main_btmnav.initialise();


    }

    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}