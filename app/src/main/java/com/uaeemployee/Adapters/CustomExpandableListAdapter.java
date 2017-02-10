package com.uaeemployee.Adapters;


import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.uaeemployee.Models.SubCompanyData;
import com.uaeemployee.Models.SubSubCompanyData;
import com.uaeemployee.R;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<SubCompanyData> expandableListTitle;
    private HashMap<SubCompanyData, List<SubSubCompanyData>> expandableListDetail;

    public CustomExpandableListAdapter(Context context, List<SubCompanyData> expandableListTitle,
                                       HashMap<SubCompanyData, List<SubSubCompanyData>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final SubSubCompanyData subsubCompanyData = (SubSubCompanyData) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.subcompany_dropdown, null);
        }
        TextView tvSubSubComp = (TextView) convertView
                .findViewById(R.id.tvSubSubComp);
        tvSubSubComp.setText(subsubCompanyData.getName());

        TextView tvMaleCount = (TextView) convertView
                .findViewById(R.id.tvMaleCount);
        tvMaleCount.setText(subsubCompanyData.getMaleCount());

        TextView tvFemaleCount = (TextView) convertView
                .findViewById(R.id.tvFemaleCount);
        tvFemaleCount.setText(subsubCompanyData.getFemaleCount());

        TextView tvLocalCount = (TextView) convertView
                .findViewById(R.id.tvLocalCount);
        tvLocalCount.setText(subsubCompanyData.getLocalCount());
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        SubCompanyData subCompanyData = (SubCompanyData) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.subcompany_main, null);
        }
        TextView tvSubCompany = (TextView) convertView
                .findViewById(R.id.tvSubCompany);
        tvSubCompany.setTypeface(null, Typeface.BOLD);
        tvSubCompany.setText(subCompanyData.getName());

        TextView tvSubSubCompaniesCount = (TextView) convertView
                .findViewById(R.id.tvSubSubCompaniesCount);
        tvSubSubCompaniesCount.setTypeface(null, Typeface.BOLD);
        tvSubSubCompaniesCount.setText(subCompanyData.getSubSubCompanyCount());


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}