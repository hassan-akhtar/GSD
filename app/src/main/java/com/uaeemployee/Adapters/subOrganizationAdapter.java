package com.uaeemployee.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.uaeemployee.Network.ResponseDTOs.SubOrganizationsDTO;
import com.uaeemployee.Network.ResponseDTOs.SubSubOrganizationsDTO;
import com.uaeemployee.R;

import java.util.List;


public class subOrganizationAdapter extends BaseAdapter {

    private List<SubSubOrganizationsDTO> orgsList;

    Context mContext;

    public subOrganizationAdapter(List<SubSubOrganizationsDTO> contactsList, Context context) {
        this.orgsList = contactsList;
        this.mContext = context;
    }


    @Override
    public int getCount() {
        Log.e("Count", "Count: " + orgsList.size());
        return orgsList.size();
    }

    @Override
    public Object getItem(int position) {
        return orgsList.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.org_row_item, parent, false);

        MyViewHolder holder = new MyViewHolder(itemView);
        SubSubOrganizationsDTO organization = orgsList.get(position);
        holder.tvName.setText(organization.getName());
        //holder.llbg.setBackgroundResource();

        return itemView;
    }


    public class MyViewHolder {
        private TextView tvName;
        private LinearLayout llbg;

        public MyViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tvName);
            llbg = (LinearLayout) view.findViewById(R.id.llbg);
        }


    }
}
