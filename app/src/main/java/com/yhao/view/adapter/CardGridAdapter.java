package com.yhao.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yhao.viewModel.HomeCardInfo;

import java.util.List;

/**
 * Created by yhao on 2017/3/16.
 */

public class CardGridAdapter extends BaseAdapter {

    private List<String> mWaresId;

    public CardGridAdapter(Context context, List<String> waresId) {
        mWaresId = waresId;
    }

    @Override
    public int getCount() {
        return mWaresId.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
