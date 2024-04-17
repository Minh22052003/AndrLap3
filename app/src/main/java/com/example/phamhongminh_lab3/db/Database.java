package com.example.phamhongminh_lab3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.phamhongminh_lab3.ThiSinh;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public static final String HoTen="HoTen";
    public static final String SBD = "SBD";
    public static final String DToan = "DToan";
    public static final String DLy = "DLy";
    public static final String DHoa = "DHoa";
    public static final String DTB = "DTB";
    private static final String tableName = "DBTS";

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate="Create table if not exists "+tableName+" ("
                +SBD+ " Text Primary key, "
                +HoTen+" Text, "
                +DToan+" Double, "
                +DLy+" Double, "
                +DHoa + " DOUBLE, "
                + DTB + " DOUBLE)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+tableName);
        onCreate(db);
    }


    public void addTS(ThiSinh thisinh){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(SBD,thisinh.getSoBaoDanh());
        contentValues.put(HoTen,thisinh.getHoTen());
        contentValues.put(DToan,thisinh.getDToan());
        contentValues.put(DLy,thisinh.getDLy());
        contentValues.put(DHoa,thisinh.getDHoa());
        double dtb = thisinh.TrungBinh();
        contentValues.put(DTB, dtb);
        db.insert(tableName,null,contentValues);
        db.close();
    }
    public void addData(){
        ThiSinh thiSinh1 = new ThiSinh("Phạm Hồng Minh","211202468",8.8,8.5,8.25);
        ThiSinh thiSinh2 = new ThiSinh("Lại Tuấn Nam","211213643",8.0,9.0,7.0);
        ThiSinh thiSinh3 = new ThiSinh("Trần Nho Tỉnh","211205275",5.4,6.5,7.25);
        addTS(thiSinh1);
        addTS(thiSinh2);
        addTS(thiSinh3);
    }
    public List<ThiSinh> getData(){
        List<ThiSinh> thiSinhList = new ArrayList<>();
        String sql="Select * from "+tableName + " ORDER BY " + SBD + " ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                ThiSinh thiSinh = new ThiSinh();
                thiSinh.setSoBaoDanh(cursor.getString(0));
                thiSinh.setHoTen(cursor.getString(1));
                thiSinh.setDToan(cursor.getDouble(2));
                thiSinh.setDLy(cursor.getDouble(3));
                thiSinh.setDHoa(cursor.getDouble(4));
                thiSinhList.add(thiSinh);
            }
        }
        return thiSinhList;
    }
    public void deleteTS(String sbd){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, SBD + "=?", new String[]{sbd});
        db.close();
    }

}
