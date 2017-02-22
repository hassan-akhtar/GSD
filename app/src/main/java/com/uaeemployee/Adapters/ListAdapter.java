package com.uaeemployee.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uaeemployee.Activites.EmployeeProfileActivity;
import com.uaeemployee.Activites.VacancyDetailActivity;
import com.uaeemployee.Network.ResponseDTOs.VacanciesDTO;
import com.uaeemployee.R;
import com.uaeemployee.Utils.SharedPreferencesManager;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    Context mContext;
    List<VacanciesDTO> mlist;
    private LayoutInflater inflater = null;
    SharedPreferencesManager sharedPreferencesManager;

    public ListAdapter(List<VacanciesDTO> list, Context context) {
        mlist = list;
        mContext = context;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sharedPreferencesManager = new SharedPreferencesManager(context);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.common_list_item, null);
        holder.tvFirst = (TextView) rowView.findViewById(R.id.tvName);
        holder.tvSecond = (TextView) rowView.findViewById(R.id.tvLevel);
        holder.tvThird = (TextView) rowView.findViewById(R.id.tvJobType);
        holder.tvFirst.setText(mlist.get(position).getTitle());
        holder.tvSecond.setText(mlist.get(position).getJobLevel());
        holder.tvThird.setText(mlist.get(position).getJobType());
        return rowView;
    }


    public class Holder {
        TextView tvFirst, tvSecond, tvThird;
    }
}
