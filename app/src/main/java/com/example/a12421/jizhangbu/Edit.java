package com.example.a12421.jizhangbu;




import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Edit extends AppCompatActivity {

    private static int id = 1;
    private EditText editText_info;
    private EditText editText_finance;
    private BillCheck bc;
    BillCheckRepo repo = new BillCheckRepo(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("");
        setSupportActionBar(toolbar);

        editText_finance=(EditText)findViewById(R.id.edit_finance) ;
        editText_info=(EditText)findViewById(R.id.edit_info);

        if("change".equals(String.valueOf(getIntent().getSerializableExtra("change_bill")))){
            bc = (BillCheck) getIntent().getSerializableExtra("billCheck");
            editText_info.setText(bc.getInfo());
            editText_finance.setText(String.valueOf(bc.getFinance()));
        }

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Edit.this,MainInterface.class));
                finish();
            }
        });

        findViewById(R.id.edit_complite).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //获取输入数据
                String finance_string = editText_finance.getText().toString();
                String info=editText_info.getText().toString();
                SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");//设置日期格式
                df.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));                 //时区
                String time = df.format(new Date(System.currentTimeMillis()));// new Date()为获取当前系统时间


                if(info.equals("")&&!finance_string.equals("")){
                    Toast.makeText(Edit.this,"请输入详细信息！",Toast.LENGTH_SHORT).show();
                }
                else if(!info.equals("")&&finance_string.equals("")){
                    Toast.makeText(Edit.this,"请输入账单金额！",Toast.LENGTH_SHORT).show();
                }
                else if(info.equals("")&&finance_string.equals("")){
                    Toast.makeText(Edit.this,"请输入账单记录！",Toast.LENGTH_SHORT).show();
                }
                else{
                    Double finance = Double.valueOf(finance_string);
                    BillCheck billCheck = new BillCheck(id,info,finance,time);

                    if("change".equals(String.valueOf(getIntent().getSerializableExtra("change_bill")))){
                        //修改
                        bc.info=editText_info.getText().toString();
                        bc.finance=Double.valueOf(editText_finance.getText().toString());
                        repo.update(bc);
                        Toast.makeText(Edit.this,"修改成功！",Toast.LENGTH_SHORT).show();
                    }else{
                        //插入数据库
                        repo.insert(billCheck);
                        Toast.makeText(Edit.this,"新增账单成功！",Toast.LENGTH_SHORT).show();
                        Edit.id++;
                    }

                    startActivity(new Intent(Edit.this,MainInterface.class));
                    finish();
                }




            }
        });
    }


}