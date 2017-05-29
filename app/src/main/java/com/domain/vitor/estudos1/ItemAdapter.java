package com.domain.vitor.estudos1;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    private Context myContext;
    private LayoutInflater myInflater;
    private ArrayList<Item> dataSource;

    public ItemAdapter(Context context, ArrayList<Item> items){
        myContext = context;
        dataSource = items;
        myInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View rowView = myInflater.inflate(R.layout.list_item_row_layout, parent, false);

        TextView nameView = (TextView) rowView.findViewById(R.id.item_list_name);
        TextView shortDescriptionView = (TextView) rowView.findViewById(R.id.item_list_short_des);

        Item myItem = (Item)getItem(position);

        nameView.setText(myItem.item_name);



        shortDescriptionView.setText(myItem.details);


        return rowView;

    }



}
