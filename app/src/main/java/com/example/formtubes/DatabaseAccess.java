package com.example.formtubes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    Cursor c = null;

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);

    }

    public static DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance=new DatabaseAccess(context);
        }
        return  instance;
    }

    public void open(){
        this.database=openHelper.getWritableDatabase();
    }

    public void close(){
        if(database!=null){
            this.database.close();
        }
    }

//    Menghitung berapa banyak variable yang munculpada suatu klasifikasi
    public Integer probLikelihoodPembilang(String variable, Integer input, String classification ){
        c=database.rawQuery("select count(*) from Mental_disorder_symptoms where " + variable + " = '"+input+"' and disorder = '"+classification+"'", null);
        Integer result = 0;
        while (c.moveToNext()){
            result = c.getInt(0);
        }return result;
    }

//    Menghitung berapa banyak nilai pada suatu variable
    public Integer probLikelihoodPenyebut(String variable, Integer input ){
        c=database.rawQuery("select count(*) from Mental_disorder_symptoms where " + variable + " = '"+input+"'", null);
        Integer result = 0;
        while (c.moveToNext()){
            result = c.getInt(0);
        }return result;
    }

//    Menghitung berapa banyak jumlah suatu klasifikasi
    public Integer sumClass(String classification){
        c=database.rawQuery("select count(*) from Mental_disorder_symptoms where disorder = '"+classification+"'", null);
        Integer result = 0;
        while (c.moveToNext()){
            result = c.getInt(0);
        }return result;
    }

//    Menghitung berapa banyak nilai yang muncul pada suatu variabel
    public Integer predictorPriorProb(String variable, Integer input){
        c=database.rawQuery("select count(*) from Mental_disorder_symptoms where " + variable + " = '"+input+"'", null);
        Integer result = 0;
        while (c.moveToNext()){
            result = c.getInt(0);
        }return result;
    }

//    Menghitung total data
    public Integer total(){
        c=database.rawQuery("select count(*) from Mental_disorder_symptoms", null);
        Integer result = 0;
        while (c.moveToNext()){
            result = c.getInt(0);
        }return result;
    }

}
