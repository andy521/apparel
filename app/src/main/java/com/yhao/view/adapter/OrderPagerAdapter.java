package com.yhao.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yhao.model.dao.WaresLimitDAO;
import com.yhao.view.R;
import com.yhao.view.databinding.GridViewPagerItemBinding;
import com.yhao.view.widgt.BounceScrollView;
import com.yhao.viewModel.GridAdapterVM;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhao on 2017/3/28.
 */

public class OrderPagerAdapter extends PagerAdapter {
    private List<View> viewLists;
    private String[] tabStrs = new String[]{"流行", "热销", "上新", "价格"};
    private List<BounceScrollView> mBounceScrollViews;

    public OrderPagerAdapter(Context context, List<WaresLimitDAO> waresLimitDAOList,List<BounceScrollView> scrollViews) {
        viewLists = new ArrayList<>();
        mBounceScrollViews = scrollViews;
        for (int i = 0; i < 4; i++) {
            GridViewPagerItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.grid_view_pager_item, null, false);
            WaresLimitDAO dao = new WaresLimitDAO(context, binding.loadMoreTv,binding.scrollView);
            waresLimitDAOList.add(i, dao);
            scrollViews.add(i, binding.scrollView);
            binding.setGridAdapterVM(new GridAdapterVM(dao.mLikeGridAdapter));
            binding.gridView.setAdapter(dao.mLikeGridAdapter);
            viewLists.add(i, binding.getRoot());
        }
    }


    @Override
    public int getCount() {
        return viewLists.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewLists.get(position));
        return viewLists.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewLists.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabStrs[position];
    }
}
