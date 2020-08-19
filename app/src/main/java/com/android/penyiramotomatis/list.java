package com.android.penyiramotomatis;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class list extends ArrayAdapter<Tanaman> {
    private Activity context;
    private List<Tanaman> tanamanList;

    public list(Activity context, List<Tanaman> tanamanList){
        super(context, R.layout.list_tanaman, tanamanList);
        this.context=context;
        this.tanamanList=tanamanList;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_tanaman,null,true);
        TextView nama_tanaman = listViewItem.findViewById(R.id.nama_tanaman);

        Tanaman tanaman = tanamanList.get(position);

        nama_tanaman.setText(tanaman.getNama_tanaman());
        return  listViewItem;
    }
}
