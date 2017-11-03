package com.example.a12421.jizhangbu;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainInterface extends AppCompatActivity {
    private ListView listView;
    private BillAdapter adapter;
    private ArrayList<BillCheck> billlist;
    private BillCheckRepo repo;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);

        //增加按钮
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainInterface.this,Edit.class);
                intent.putExtra("insert","newbill");
                startActivity(intent);
                finish();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("");
        setSupportActionBar(toolbar);

        adapter=new BillAdapter(this);
        listView=(ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        repo=new BillCheckRepo(this);
        billlist=new ArrayList<BillCheck>();
        billlist = repo.getBillList();
        for(int i=0;i<billlist.size();i++){adapter.addbillcheck(billlist.get(i));}

        //列表点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BillCheck billCheck = billlist.get(position);
                Intent intent = new Intent(MainInterface.this,Edit.class);
                intent.putExtra("change_bill","change");
                intent.putExtra("billCheck",billCheck);
                startActivity(intent);
                finish();
            }
        });

        //列表长按时间
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainInterface.this);
                builder.setMessage("确认删除吗");
                builder.setTitle("提示");

                final BillCheck billCheck_long = billlist.get(position);


                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        arg0.dismiss();
                    }
                });

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        repo.delete(billCheck_long.getBillcheck_ID());
                        adapter.notifyDataSetChanged();
                        arg0.dismiss();
                    }
                });
                builder.create().show();
                return true;
            }
        });


    }
}