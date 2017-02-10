package com.uaeemployee.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uaeemployee.R;


public class NationalityAdapter extends BaseAdapter {
    String[] listCountryName, listPercentages;
    Context context;
    int[] imageIds;
    private static LayoutInflater inflater = null;

    public NationalityAdapter(Context context, String[] listCountryName, String[] listPercentages,int[] imageIds) {
        this.listCountryName = listCountryName;
        this.listPercentages = listPercentages;
        this.context = context;
        this.imageIds = imageIds;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listCountryName.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        TextView tvCountry_name, tvPercentage;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.row_nationality, null);
        holder.tvCountry_name = (TextView) rowView.findViewById(R.id.tvCountry_name);
        holder.tvPercentage= (TextView) rowView.findViewById(R.id.tvPercentage);
        holder.img = (ImageView) rowView.findViewById(R.id.ivFlag);
        holder.tvCountry_name.setText(listCountryName[position]);
        holder.tvPercentage.setText(listPercentages[position]);
        holder.img.setImageResource(imageIds[position]);
        return rowView;
    }

}