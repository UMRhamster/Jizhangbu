package com.example.a12421.jizhangbu;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

/**
 * Created by 12421 on 2016/11/18.
 */
public class DBHelper extends SQLiteOpenHelper{
    //数据库版本号
    private static final int DATABASE_VERSION=1;
    //数据库名字
    private static final String DATABASE_NAME="crud.db";
    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db){
        //创建数据表
        String CREATE_TABLE_BILLCHECK="CREATE TABLE "
                +BillCheck.TABLE+"("
                +BillCheck.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +BillCheck.KEY_INFO+" TEXT,"
                +BillCheck.KEY_FINANCE+" REAL,"
                +BillCheck.KEY_TIME+" TEXT)";
        db.execSQL(CREATE_TABLE_BILLCHECK);
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        //如果旧表存在，删除，所以数据将会消失
        db.execSQL("DROP TABLE IF EXISTS "+BillCheck.TABLE);
        //再此创建表
        onCreate(db);
    }
}