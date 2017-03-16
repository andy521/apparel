package com.yhao.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
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
        HomeCardDAO dao = new HomeCardDAO();
        for (int i = 0; i < dao.mHomeCardInfoList.size(); i++) {
            if (TextUtils.equals(dao.mHomeCardInfoList.get(i).getTheme(), HomeCardDAO.themes[0])) {
                mCardRecommend.setData(dao.mHomeCardInfoList.get(i));
            }
            if (TextUtils.equals(dao.mHomeCardInfoList.get(i).getTheme(), HomeCardDAO.themes[1])) {
                mCardTops.setData(dao.mHomeCardInfoList.get(i));
            }
            if (TextUtils.equals(dao.mHomeCardInfoList.get(i).getTheme(), HomeCardDAO.themes[2])) {
                mCardBottoms.setData(dao.mHomeCardInfoList.get(i));
            }
            if (TextUtils.equals(dao.mHomeCardInfoList.get(i).getTheme(), HomeCardDAO.themes[3])) {
                mCardShose.setData(dao.mHomeCardInfoList.get(i));
            }
            if (TextUtils.equals(dao.mHomeCardInfoList.get(i).getTheme(), HomeCardDAO.themes[4])) {
                mCardBags.setData(dao.mHomeCardInfoList.get(i));
            }
        }
        dao.loadHomeCardInfo();
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


