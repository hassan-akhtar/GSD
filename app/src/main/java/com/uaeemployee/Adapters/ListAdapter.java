package com.uaeemployee.Adapters;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uaeemployee.Activites.EmployeeProfileActivity;
import com.uaeemployee.Activites.VacancyDetailActivity;
import com.uaeemployee.Network.ResponseDTOs.EmployeeDTO;
import com.uaeemployee.Network.ResponseDTOs.VacanciesDTO;
import com.uaeemployee.R;
import com.uaeemployee.Utils.SharedPreferencesManager;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    Context mContext;
    List<VacanciesDTO> mlist;
    List<EmployeeDTO> mlistEmployee;
    private LayoutInflater inflater = null;

    public ListAdapter(List<VacanciesDTO> list, Context context) {
        mlist = list;
        mContext = context;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ListAdapter(List<EmployeeDTO> list, Context context, String str) {
        mlistEmployee = list;
        mContext = context;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        if (null!=mlist) {
          return mlist.size();
        } else if (null!=mlistEmployee){
            return mlistEmployee.size();
        }else {
            Log.e( " ListAdapter getView: ", "Something went wrong");
            return 0;

        }
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


        if (null!=mlist) {
            holder.tvFirst.setText(mlist.get(position).getTitle());
            holder.tvSecond.setText(mlist.get(position).getJobLevel());
            holder.tvThird.setText(mlist.get(position).getJobType());
        } else if (null!=mlistEmployee){
            holder.tvFirst.setText(mlistEmployee.get(position).getFirstName()+" "+ mlistEmployee.get(position).getLastName());
            holder.tvSecond.setText(mlistEmployee.get(position).getGender());
            holder.tvThird.setText(mlistEmployee.get(position).getCountryName());
        }else {
            Log.e( " ListAdapter getView: ", "Something went wrong");
        }


        return rowView;
    }


    public class Holder {
        TextView tvFirst, tvSecond, tvThird;
    }
}
