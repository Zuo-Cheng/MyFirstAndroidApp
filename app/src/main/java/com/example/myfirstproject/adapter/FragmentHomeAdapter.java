package com.example.myfirstproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstproject.R;
import com.example.myfirstproject.activity.Login;
import com.example.myfirstproject.activity.Rective;
import com.example.myfirstproject.model.Information;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import cn.bmob.v3.BmobUser;

//定义FragmentHomeAdapter继承列表适配器,实现上拉刷新加载数据
public class FragmentHomeAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<Information> informationList;

    private final int N_TYPE = 0;

    private final int F_TYPE = 1;

    private int max_count = 15;

    private Boolean isFootView = true;//是否有footview

    //构造函数
    public FragmentHomeAdapter(Context context, List<Information> informationList){
        this.context = context;
        this.informationList = informationList;
    }

    //列表刷新创建的时候
    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {

        //获得刷新视图
        View generalView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.general_item,viewGroup,false);

        View footView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.foot_item,viewGroup,false);

        if(i == F_TYPE){
            return new RecyclerViewHolder(footView,F_TYPE);
        }else{
            return new RecyclerViewHolder(generalView,N_TYPE);
        }
    }

    //列表刷新绑定的时候
    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int i) {
        if(isFootView && (getItemViewType(i)) == F_TYPE){
            //下面代码是处理foot_item视图的内容。
            final RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) viewHolder;

            recyclerViewHolder.foot_item_text_loading.setText("加载中...");
            //Hnadler类解析：在一个新线程中更新主线程中的UI控件，
            // 一种是调用sendMessage方法，一种是通过post方法，另一种是obtainMessage。
            Handler handler = new Handler();
            //每次刷新，资讯列表数量加8个,2秒钟加载完
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    max_count += 8;
                    notifyDataSetChanged();
                }
            },2000);
        }else{
            //下面代码是处理general_item视图的内容。
            final RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) viewHolder;

            Information information = informationList.get(i);
            recyclerViewHolder.general_item_text_username.setText(information.getUsername());
            recyclerViewHolder.general_item_text_nickname.setText(information.getNickname());
            recyclerViewHolder.general_item_text_content.setText(information.getContent());
            recyclerViewHolder.general_item_text_time.setText(information.getCreatedAt());

            recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //获得当前用户所在的页面位置
                    int position = recyclerViewHolder.getAdapterPosition();

                    //检查用户是否登录
                    if(BmobUser.getCurrentUser(BmobUser.class) != null){
                        //页面跳转
                        Intent intent = new Intent(context, Rective.class);
                        //跳转页面时携带一些参数
                        intent.putExtra("id",informationList.get(position).getObjectId());
                        //跳转
                        context.startActivity(intent);
                    }else{
                        //没有登录跳转登录页面
                        Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, Login.class));
                    }
                }
            });

        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == max_count - 1){
            return  F_TYPE;
        }else{
            return N_TYPE;
        }
    }

    //获得列表数据总数的时候
    @Override
    public int getItemCount() {
        if (informationList.size() < max_count){
            return informationList.size();
        }else{
            return max_count;
        }
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView general_item_text_username;

        public TextView general_item_text_nickname;

        public TextView general_item_text_content;

        public TextView general_item_text_time;

        public TextView foot_item_text_loading;

        public RecyclerViewHolder(View item_view, int view_type) {
            super(item_view);

            if(view_type == N_TYPE){
                //如果进入这里就表示只找到下面这几个控件
                //general_item_text_nickname、general_item_text_content、general_item_text_time

                general_item_text_username = item_view.findViewById(R.id.general_item_text_username);
                general_item_text_nickname = item_view.findViewById(R.id.general_item_text_nickname);
                general_item_text_content = item_view.findViewById(R.id.general_item_text_content);
                general_item_text_time = item_view.findViewById(R.id.general_item_text_time);


            }else if(view_type == F_TYPE){

                //进入这里表示item_view=foot_item视图
                foot_item_text_loading = item_view.findViewById(R.id.foot_item_text_loading);


            }
        }
    }
}
