package com.example.jakewilson.kitchensink.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jakewilson.kitchensink.R;
import com.example.jakewilson.kitchensink.models.Data;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by jakewilson on 8/19/14.
 */
public class DataAdapter extends BaseAdapter {

    private Context context;
    private List<Data> items;
    public DataAdapter(Context context, List<Data> items){
        this.items = items;
        this.context = context;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.data_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.title.setText("Item");
        holder.number.setText(String.valueOf(position + 1));
        holder.description.setText("This is a cool description that is in row " + position + " .");
        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.title) TextView title;
        @InjectView(R.id.number) TextView number;
        @InjectView(R.id.description) TextView description;
        @InjectView(R.id.image) ImageView image;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
