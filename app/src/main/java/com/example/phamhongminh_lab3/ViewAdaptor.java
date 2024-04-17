package com.example.phamhongminh_lab3;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ViewAdaptor extends BaseAdapter {
    private List<ThiSinh> data;

    public ViewAdaptor(List<ThiSinh> data) {
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @SuppressLint("SuspiciousIndentation")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.layout_viewthisinh, null);
        } else view = convertView;

        TextView txtSBD = view.findViewById(R.id.textSBD);
        TextView txtName = view.findViewById(R.id.textHoten);
        TextView txtDiem = view.findViewById(R.id.textDiem);

        ThiSinh itemValues = data.get(position);

        txtSBD.setText(itemValues.getSoBaoDanh());
        txtName.setText(itemValues.getHoTen());
        txtDiem.setText(itemValues.Tong()+"");
        return view;
    }
}
