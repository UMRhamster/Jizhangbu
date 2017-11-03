package com.example.a12421.jizhangbu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by 12421 on 2016/11/18.
 */
public class BillCheckRepo {
    private DBHelper dbHelper;

    public BillCheckRepo(Context context) {
        dbHelper = new DBHelper(context);
    }
    //增加
    public int insert(BillCheck billCheck){
        //打开链接，写入数据
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(BillCheck.KEY_INFO,billCheck.info);
        values.put(BillCheck.KEY_FINANCE,billCheck.finance);
        values.put(BillCheck.KEY_TIME,billCheck.time);
        //
        long billCheck_ID=db.insert(BillCheck.TABLE,null,values);
        db.close();
        return (int)billCheck_ID;
    }
    //删除
    public void delete(int billcheck_id){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        db.delete(BillCheck.TABLE,BillCheck.KEY_ID+"=?",new String[]{String.valueOf(billcheck_id)});
        db.close();
    }
    //修改
    public void update(BillCheck billCheck){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(BillCheck.KEY_INFO,billCheck.info);
        values.put(BillCheck.KEY_FINANCE,billCheck.finance);
        values.put(BillCheck.KEY_TIME,billCheck.time);

        db.update(BillCheck.TABLE,values,BillCheck.KEY_ID+"=?",new String[] { String.valueOf(billCheck.getBillcheck_ID()) });
        db.close();
    }
    //查询
    public ArrayList<BillCheck> getBillList(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                BillCheck.KEY_ID+","+
                BillCheck.KEY_INFO+","+
                BillCheck.KEY_FINANCE+","+
                BillCheck.KEY_TIME + " FROM "+ BillCheck.TABLE;
        ArrayList<BillCheck> billcheckList=new ArrayList<BillCheck>();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                BillCheck billCheck = new BillCheck(999999,"hello",23.0,"2017年1月20日18:23:14");
                billCheck.billcheck_ID=Integer.parseInt(cursor.getString(cursor.getColumnIndex(BillCheck.KEY_ID)));
                billCheck.finance=Double.parseDouble(cursor.getString(cursor.getColumnIndex(BillCheck.KEY_FINANCE)));
                billCheck.info=cursor.getString(cursor.getColumnIndex(BillCheck.KEY_INFO));
                billCheck.time=cursor.getString(cursor.getColumnIndex(BillCheck.KEY_TIME));
                billcheckList.add(billCheck);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return billcheckList;
    }

//    public BillCheck getBillcheckById(int Id){
//        SQLiteDatabase db=dbHelper.getReadableDatabase();
//        String selectQuery="SELECT "+
//                BillCheck.KEY_ID + "," +
//                BillCheck.KEY_INFO + " FROM " + BillCheck.TABLE
//                + " WHERE " +
//                BillCheck.KEY_ID + "=?";
//        int iCount=0;
//        BillCheck billCheck=new BillCheck();
//        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(Id)});
//        if(cursor.moveToFirst()){
//            do{
//                billCheck.billcheck_ID =cursor.getInt(cursor.getColumnIndex(BillCheck.KEY_ID));
//                billCheck.info =cursor.getString(cursor.getColumnIndex(BillCheck.KEY_INFO));
//            }while(cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return billCheck;
//    }
}
