package com.yhao.view.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.yhao.model.dao.HomeCardDAO;
import com.yhao.view.R;
import com.yhao.view.adapter.FastMenuAdapter;
import com.yhao.view.widgt.HomeCardView;
import com.yhao.view.widgt.Topbar;

public class HomeFragment extends Fragment {
    private Topbar mTopbar;
    private GridView mFastMenuGridView;

    private HomeCardView mCardRecommend;
    private HomeCardView mCardTops;
    private HomeCardView mCardBottoms;
    private HomeCardView mCardShose;
    private HomeCardView mCardBags;

    private boolean setRecommendDataFlag = true;
    private boolean setTopsDataFlag = true;
    private boolean setBottomsDataFlag = true;
    private boolean setShoseDataFlag = true;
    private boolean setBagsDataFlag = true;

    private ScrollView mScrollView;

    private HomeCardDAO mHomeCardDAO;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mTopbar = (Topbar) view.findViewById(R.id.topbar);
        mFastMenuGridView = (GridView) view.findViewById(R.id.fastMenuGridView);

        mCardRecommend = (HomeCardView) view.findViewById(R.id.cardRecommend);
        mCardTops = (HomeCardView) view.findViewById(R.id.cardTops);
        mCardBottoms = (HomeCardView) view.findViewById(R.id.cardBottoms);
        mCardShose = (HomeCardView) view.findViewById(R.id.cardShose);
        mCardBags = (HomeCardView) view.findViewById(R.id.cardBags);

        mScrollView = (ScrollView) view.findViewById(R.id.scrollView);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initEvent();
        initCardData();
    }

    private void initCardData() {
        mHomeCardDAO = new HomeCardDAO();
        mHomeCardDAO.loadHomeCardInfo();
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

        mScrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            if (setRecommendDataFlag && mCardRecommend.getGlobalVisibleRect(new Rect())) {
                mCardRecommend.setData(mHomeCardDAO.mHomeCardInfoMap.get(HomeCardDAO.themes[0]));
                setRecommendDataFlag = false;
            }
            if (setTopsDataFlag && mCardTops.getGlobalVisibleRect(new Rect())) {
                mCardTops.setData(mHomeCardDAO.mHomeCardInfoMap.get(HomeCardDAO.themes[1]));
                setTopsDataFlag = false;
            }
            if (setBottomsDataFlag && mCardBottoms.getGlobalVisibleRect(new Rect())) {
                mCardBottoms.setData(mHomeCardDAO.mHomeCardInfoMap.get(HomeCardDAO.themes[2]));
                setBottomsDataFlag = false;
            }
            if (setShoseDataFlag && mCardShose.getGlobalVisibleRect(new Rect())) {
                mCardShose.setData(mHomeCardDAO.mHomeCardInfoMap.get(HomeCardDAO.themes[3]));
                setShoseDataFlag = false;
            }
            if (setBagsDataFlag && mCardBags.getGlobalVisibleRect(new Rect())) {
                mCardBags.setData(mHomeCardDAO.mHomeCardInfoMap.get(HomeCardDAO.themes[4]));
                setBagsDataFlag = false;
            }
        });

    }


}


