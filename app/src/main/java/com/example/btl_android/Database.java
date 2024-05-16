package com.example.btl_android;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // tao bang nguoi dung
        String qry1 = "create table users(username text,email text,password text) " ;
        db.execSQL(qry1); ;
        // tao bang them san pham
        String qry2 = "create table cart(username text,packet text,price float,otype text) " ;
        db.execSQL(qry2);
        // tao bang dajt lich hen
        String qry3 = "create table orderbook(username text,fullname text,address text, contact text,  code int, date text, time text, amout float, otype text) " ;
        db.execSQL(qry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // dang ky
    public void register(String username, String email, String password){
        ContentValues contentValues = new ContentValues() ;
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password) ;
        SQLiteDatabase db = getWritableDatabase() ;
        db.insert("users", null, contentValues);

    }

    // dang nhap
    public int login(String username, String password){
        int rs = 0;
        String str[] = new String[2] ;
        str[0] = username ;
        str[1] = password ;
        SQLiteDatabase db = getReadableDatabase() ;
        Cursor c = db.rawQuery("select * from users where username = ? and password = ?",str ) ;
        if(c.moveToFirst()){
            rs = 1 ;
        }
        return rs ;
    }

    // them vao gio hang
    public void addtoCart(String username, String packet, float price, String otype){
        ContentValues contentValues = new ContentValues() ;
        contentValues.put("username", username);
        contentValues.put("packet", packet);
        contentValues.put("price", price) ;
        contentValues.put("otype", otype) ;
        SQLiteDatabase db = getWritableDatabase() ;
        db.insert("cart", null, contentValues);

        }

        // xoa
        public void deleteCartItem(String username, String packet) {
            SQLiteDatabase db = this.getWritableDatabase();
            // Xác định điều kiện để xóa mục từ cơ sở dữ liệu
            String selection = "username = ? AND packet = ?";
            String[] selectionArgs = {username, packet};
            // Thực hiện xóa mục từ cơ sở dữ liệu
            db.delete("cart", selection, selectionArgs);

        }
    // check gio hang
    public int checkcart(String username, String packet){
        int rs = 0;
        String str[] = new String[2] ;
        str[0] = username ;
        str[1] = packet ;
        SQLiteDatabase db = getReadableDatabase() ;
        Cursor c = db.rawQuery("select * from cart where username = ? and packet = ?",str ) ;
        if(c.moveToFirst()){
            rs = 1 ;
        }

        return rs ;
    }
    // xoa gio hang
    public void remove(String username, String otype){
        String str[] = new String[2] ;
        str[0] = username ;
        str[1]  = otype ;
        SQLiteDatabase db = getReadableDatabase() ;
        db.delete("cart","username=? and otype=?",str) ;
    }
    public ArrayList getCartData(String username, String otype){
        ArrayList<String> arr = new ArrayList<>() ;
        SQLiteDatabase db = getReadableDatabase() ;
        String str[] = new String[2] ;
        str[0] = username ;
        str[1]  = otype ;
        Cursor c = db.rawQuery("select * from cart where username= ? and otype = ?",str) ;
        if(c.moveToFirst()){
            do{
                String packet = c.getString(1) ;
                String price  = c.getString(2) ;
                arr.add(packet+"$"+price) ;
            }
            while (c.moveToNext());
        }
        return arr ;

    }

    public ArrayList getCartData2(String username, String otype){
        ArrayList<String> arr = new ArrayList<>() ;
        SQLiteDatabase db = getReadableDatabase() ;
        String str[] = new String[2] ;
        str[0] = username ;
        str[1]  = otype ;
        Cursor c = db.rawQuery("select * from cart where username= ? and otype = ?",str) ;
        if(c.moveToFirst()){
            do{
                String packet = c.getString(1) ;
                String price  = c.getString(2) ;
                arr.add(packet+" : " +price+ "$") ;
            }
            while (c.moveToNext());
        }
        return arr ;

    }

    // them lich hen
    public void addOrder(String username, String fullname, String address, String contact,int code, String date, String time, float amout, String otype){
        ContentValues cv = new ContentValues() ;
        cv.put("username",username);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("contact",contact);
        cv.put("code",code);
        cv.put("date",date) ;
        cv.put("time",time) ;
        cv.put("amout",amout);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase() ;
        db.insert("orderbook",null,cv) ;
    }

    // lay danhsach order
    public ArrayList getOrderData(String username){
        ArrayList<String> arr = new ArrayList<>()  ;
        SQLiteDatabase db = getReadableDatabase() ;
        String str[] = new String[1];
        str[0] = username ;
        Cursor c = db.rawQuery("select * from orderbook where username = ?",str) ;
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(1)+ "$"+ c.getString(2)+ "$"+c.getString(3)+ "$"+c.getString(4)+ "$"+c.getString(5)+ "$"+c.getString(6)+ "$"+c.getString(7)+ "$"+c.getString(8)) ;
            }while( c.moveToNext() );
        }
        return arr ;
    }

    public int checkBookExit(String username, String fullname, String address, String contact, String date, String time,String otype){
        int rs = 0 ;
        String str[] = new String[7] ;
        str[0]  =username ;
        str[1] = fullname ;
        str[2] = address ;
        str[3] = contact ;
        str[4] = date ;
        str[5] = time ;
        str[6] = otype ;
        SQLiteDatabase db = getReadableDatabase() ;
        Cursor c = db.rawQuery("select * from orderbook where username = ? and fullname = ?  and address = ? and contact = ? and date = ? and time = ? and otype = ?" ,str);
        if(c.moveToFirst()){
            rs = 1;
        }
         return rs ;
     }
}
