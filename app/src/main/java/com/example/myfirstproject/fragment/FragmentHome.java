package com.example.myfirstproject.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstproject.R;
import com.example.myfirstproject.adapter.FragmentHomeAdapter;
import com.example.myfirstproject.model.Information;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class FragmentHome extends Fragment {

    private RecyclerView fragment_home_recyclerview;

    private SwipeRefreshLayout fragment_home_swipe;

    private TextView fragment_home_title;

    List<Information> informationList;

    private FragmentHomeAdapter fragmentHomeAdapter;

    //创建页面的时候出发
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //处理逻辑
        InitView();

        Bmob.initialize(getActivity(),"0648778bf9938a804f1851e8cb434a59");

        //初始加载页面时刷新
        Refresh();

        //设置刷新的那个圆圈颜色
        this.fragment_home_swipe.setColorSchemeResources(
                android.R.color.holo_green_light,
                android.R.color.holo_red_light,
                android.R.color.holo_blue_light
        );

        //刷新监听方法。
        this.fragment_home_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                Refresh();
            }
        });
    }

    //刷新
    private void Refresh(){
        BmobQuery<Information> informationBmobQuery = new BmobQuery<Information>();
        //按照时间进行排序。
        informationBmobQuery.order("-createdAt");

        informationBmobQuery.setLimit(1000);

        //查找多条数据，findObjects()
        informationBmobQuery.findObjects(new FindListener<Information>() {
            @Override
            public void done(List<Information> list, BmobException e) {
                //停止刷新
                fragment_home_swipe.setRefreshing(false);

                if(e == null){
                    //查询成功
                    informationList = list;

                    //将数据绑定到页面
                    fragmentHomeAdapter = new FragmentHomeAdapter(getActivity(),informationList);
                    fragment_home_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                    fragment_home_recyclerview.setAdapter(fragmentHomeAdapter);
                }else{
                    //获取数据失败
                    fragment_home_swipe.setRefreshing(false);
                    Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //初始化
    private void InitView(){
        this.fragment_home_title = getActivity().findViewById(R.id.fragment_home_title);
        this.fragment_home_recyclerview = getActivity().findViewById(R.id.fragment_home_recyclerview);
        this.fragment_home_swipe = getActivity().findViewById(R.id.fragment_home_swipe);
    }
}
