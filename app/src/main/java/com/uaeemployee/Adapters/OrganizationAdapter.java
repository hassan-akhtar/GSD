package com.uaeemployee.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.uaeemployee.Models.Organization;
import com.uaeemployee.R;

import java.util.List;


public class OrganizationAdapter extends BaseAdapter {

    private List<Organization> orgsList;
    TextDrawable drawable;
    Context mContext;
    public static String contactID = "", rawContactId = "";

    public OrganizationAdapter(List<Organization> contactsList, Context context) {
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
        Organization organization = orgsList.get(position);
        holder.tvOrgName.setText(organization.getName());
        holder.tvSubCompaniesCount.setText(organization.getSubCompaniesCount());
            drawable = TextDrawable.builder()
                    .beginConfig()
                    .endConfig()
                    .buildRound(organization.getName().substring(0, 1).toUpperCase(), mContext.getResources().getColor(R.color.colorPrimaryDark));
            holder.ivLetter.setImageDrawable(drawable);

        /*else {
            holder.name.setText(contacts.getLastName().substring(0,1).toUpperCase()+ contacts.getLastName().substring(1).toLowerCase()+ ", " + contacts.getFirstName().substring(0,1).toUpperCase()+ contacts.getFirstName().substring(1).toLowerCase());
            drawable = TextDrawable.builder()
                    .beginConfig()
                    .endConfig()
                    .buildRound(contacts.getLastName().substring(0,1).toUpperCase()+contacts.getFirstName().substring(0,1).toUpperCase(), mContext.getResources().getColor(R.color.colorPrimaryDark));
            holder.ivLetter.setImageDrawable(drawable);
        }*/


        return itemView;
    }


    public class MyViewHolder {
        private TextView tvOrgName;
        private TextView tvSubCompaniesCount;
        private ImageView ivLetter;

        public MyViewHolder(View view) {
            tvOrgName = (TextView) view.findViewById(R.id.tvOrgName);
            tvSubCompaniesCount = (TextView) view.findViewById(R.id.tvSubCompaniesCount);
            ivLetter = (ImageView) view.findViewById(R.id.ivLetter);
        }


    }
}
