package com.project.doer.group;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.doer.R;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private int platformIcon[];
    private String[] platformNames;
    private LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, int[] platformIcon, String[] platformNames) {
        this.context = applicationContext;
        this.platformIcon = platformIcon;
        this.platformNames = platformNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return platformIcon.length;
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
        icon.setImageResource(platformIcon[i]);
        names.setText(platformNames[i]);
        return view;
    }
}
