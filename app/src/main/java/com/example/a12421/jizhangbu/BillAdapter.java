package com.example.a12421.jizhangbu;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 12421 on 2017/1/20.
 */

public class BillAdapter extends BaseAdapter {
    private Context context ;
    private ArrayList<BillCheck> data_bill;

    public BillAdapter(Context context){
        this.context=context;
        data_bill=new ArrayList<BillCheck>();
    }

    public void addbillcheck(BillCheck billCheck){
        data_bill.add(billCheck);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return data_bill.size();
    }

    @Override
    public BillCheck getItem(int position) {
        return data_bill.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.list_cell,null);
        }
        TextView tvinfo=(TextView)convertView.findViewById(R.id.binfo);
        TextView tvfinance=(TextView)convertView.findViewById(R.id.bfinance);
        TextView tvtime=(TextView)convertView.findViewById(R.id.btime);
        TextView tvbillid=(TextView)convertView.findViewById(R.id.billcheckid);

        BillCheck bill=getItem(position);
        tvinfo.setText(bill.getInfo());
        tvfinance.setText("¥:"+String.valueOf(bill.getFinance()));
        tvtime.setText(bill.getTime());
        tvbillid.setText(String.valueOf(bill.getBillcheck_ID()));

        return convertView;
    }


    public Context getContext() {
        return context;
    }


}
