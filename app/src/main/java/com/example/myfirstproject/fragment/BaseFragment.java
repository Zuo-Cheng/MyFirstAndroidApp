package com.example.myfirstproject.fragment;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import io.reactivex.annotations.Nullable;

public abstract class BaseFragment extends Fragment {

    public Context applet;

    public Resources resources;

    public LayoutInflater layoutInflater;

    public void  onAttach(Context context){
        super.onAttach(context);
        applet = context;
        this.resources = context.getResources();
        this.layoutInflater = layoutInflater.from(context);
    }

    @Override
    public void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private View RootView;


    //创建页面时触发的事件
    @android.support.annotation.Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @android.support.annotation.Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @android.support.annotation.Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        if(RootView == null){
            RootView = inflater.inflate(getLayoutID(),container,false);
        }

        ViewGroup parent = (ViewGroup) RootView.getParent();

        if(parent != null){
            parent.removeView(RootView);
        }

        return RootView;

    }


    //
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @android.support.annotation.Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitView(view);
        BindEvent();
        InitData();
    }

    @Override
    public void onActivityCreated(@android.support.annotation.Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected <VV extends View> VV FindView(View view, @IdRes int id){
        return view.findViewById(id);
    }

    protected <VV extends View> VV FindView( @IdRes int id){
        return RootView.findViewById(id);
    }

    protected abstract void InitData();

    protected abstract void BindEvent();

    protected abstract void InitView(View view);


    protected  abstract  int getLayoutID();
}
