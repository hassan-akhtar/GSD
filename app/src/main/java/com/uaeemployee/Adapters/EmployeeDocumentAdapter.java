package com.uaeemployee.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uaeemployee.Network.ResponseDTOs.EmployeeDocument;
import com.uaeemployee.R;

import java.util.List;

public class EmployeeDocumentAdapter extends BaseAdapter {


    Context mContext;
    List<EmployeeDocument> mlist;
    private LayoutInflater inflater = null;

    public EmployeeDocumentAdapter(List<EmployeeDocument> list, Context context) {
        mlist = list;
        mContext = context;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView;
        rowView = inflater.inflate(R.layout.employee_doc_row_item, null);
        Holder holder = new Holder(rowView);
        holder.tvDocumentName.setText(mlist.get(position).getDocumentName());
        return rowView;
    }


    public class Holder {
        TextView tvDocumentName;


        public Holder(View view) {
            tvDocumentName = (TextView) view.findViewById(R.id.tvDocumentName);
        }
    }
}
