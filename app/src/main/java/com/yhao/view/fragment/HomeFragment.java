package com.yhao.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yhao.view.R;
import com.yhao.view.widgt.Topbar;

public class HomeFragment extends Fragment {
    private Topbar mTopbar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mTopbar = (Topbar) view.findViewById(R.id.topbar);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    }


}
