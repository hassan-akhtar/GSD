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
import com.uaeemployee.Models.Employee;
import com.uaeemployee.R;
import com.uaeemployee.Utils.SharedPreferencesManager;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    Context mContext;
    List<Employee> mlist;
    private LayoutInflater inflater = null;
    SharedPreferencesManager sharedPreferencesManager;

    public ListAdapter(List<Employee> list, Context context) {
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
        holder.tvSecond= (TextView) rowView.findViewById(R.id.tvGender);
        holder.tvThird= (TextView) rowView.findViewById(R.id.tvCountry);
        holder.tvFirst.setText(mlist.get(position).getName());
        holder.tvSecond.setText(mlist.get(position).getGender());
        holder.tvThird.setText(mlist.get(position).getCountry());
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferencesManager.setString(SharedPreferencesManager.CURRENT_FIRST,mlist.get(position).getName(),mContext);
                sharedPreferencesManager.setString(SharedPreferencesManager.CURRENT_SECOND,mlist.get(position).getGender(),mContext);
                sharedPreferencesManager.setString(SharedPreferencesManager.CURRENT_THIRD,mlist.get(position).getCountry(),mContext);
                if (!sharedPreferencesManager.getBoolean(SharedPreferencesManager.IS_VACANCY,mContext)) {
                    mContext.startActivity(new Intent(mContext , EmployeeProfileActivity.class));
                } else {
                    mContext.startActivity(new Intent(mContext , VacancyDetailActivity.class));
                }
            }
        });
        return rowView;
    }


    public class Holder {
        TextView tvFirst, tvSecond, tvThird;
    }
}
