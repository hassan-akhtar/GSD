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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.uaeemployee.Network.ResponseDTOs.NationDTO;
import com.uaeemployee.R;

import java.util.List;


public class NationalityAdapter extends BaseAdapter {
    List<NationDTO> lstNation;
    Context context;
    private static LayoutInflater inflater = null;

    public NationalityAdapter(Context context, List<NationDTO> lstNation) {
        this.context = context;
        this.lstNation = lstNation;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lstNation.size();
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
        holder.tvCountry_name.setText(lstNation.get(position).getName());
        holder.tvPercentage.setText(""+lstNation.get(position).getCount());

        Glide.with(context).load(lstNation.get(position).getFlagURL())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img);

        //holder.img.setImageResource(imageIds[position]);
        return rowView;
    }

}