package com.project.doer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class CustomSpinnerAdapter extends BaseAdapter {
    Context context;
    int platformLogo[];
    String[] platform;
    LayoutInflater inflter;

    public CustomSpinnerAdapter(Context applicationContext, int[] platformLogo, String[] platform) {
        this.context = applicationContext;
        this.platformLogo = platformLogo;
        this.platform = platform;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return platformLogo.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_platform_item, null);
        ImageView icon = (ImageView) view.findViewById(R.id.iv_spinner_platform_logo);
        TextView names = (TextView) view.findViewById(R.id.tv_spinner_platform_name);
        icon.setImageResource(platformLogo[i]);
        names.setText(platform[i]);
        return view;
    }
}
