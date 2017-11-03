package com.example.a12421.jizhangbu;


import java.io.Serializable;

/**
 * Created by 12421 on 2016/11/18.
 */
public class BillCheck implements Serializable{
    //表名
    public static final String TABLE="Billcheck";
    //表的各域
    public static final String KEY_ID="id";              //账单id
    public static final String KEY_INFO="info";           //账单信息
    public static final String KEY_FINANCE="finance";    //账单金额
    public static final String KEY_TIME="time";          //账单产生时间
    //属性
    public int billcheck_ID;
    public String info;
    public Double finance;
    public String time;

    public BillCheck(){}

    public BillCheck(int id,String info,Double finance,String time){
        this.billcheck_ID=id;
        this.info=info;
        this.finance=finance;
        this.time=time;
    }

    public int getBillcheck_ID() {
        return billcheck_ID;
    }

    public Double getFinance() {
        return finance;
    }

    public String getInfo() {
        return info;
    }

    public String getTime() {
        return time;
    }
}
