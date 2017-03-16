package com.yhao.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.yhao.view.R;
import com.yhao.view.adapter.FastMenuAdapter;
import com.yhao.view.widgt.Topbar;

public class HomeFragment extends Fragment {
    private Topbar mTopbar;
    private GridView mFastMenuGridView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mTopbar = (Topbar) view.findViewById(R.id.topbar);
        mFastMenuGridView = (GridView) view.findViewById(R.id.fastMenuGridView);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initEvent();

    }

    private void initView() {
        mFastMenuGridView.setAdapter(new FastMenuAdapter(getContext()));
    }



    private void initEvent() {
        mTopbar.setTopBarClickListener(new Topbar.TopBarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(getContext(), "more", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(getContext(), "search", Toast.LENGTH_SHORT).show();
            }
        });
        mFastMenuGridView.setOnItemClickListener((parent, views, position, id) -> Toast.makeText(getContext(), "pos=" + position, Toast.LENGTH_SHORT).show());
    }


}


