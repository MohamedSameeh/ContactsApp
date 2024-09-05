package com.example.contactsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDataBase extends SQLiteOpenHelper {
    private  static final String NAME="CONTACTS";
    private  static final String TABLE_NAME="CONTACTS_TABLE";
    private  static final int DB_VERSION=1;
    private  static final String COLUMN_ID="_id";

    private  static final String COLUMN_NAME="name";
    private  static final String COLUMN_NUMBER="number";
    private final Context context;
    Contacts contacts;
    public MyDataBase(Context context) {
        super(context, NAME, null,DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+TABLE_NAME+
                " ( "+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_NAME+" TEXT, "+COLUMN_NUMBER+" TEXT);";
                db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     String query="DROP TABLE IF EXISTS "+TABLE_NAME;
     db.execSQL(query);
     onCreate(db);
    }
    public long addData(Contacts contacts){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME,contacts.getName());
        cv.put(COLUMN_NUMBER,contacts.getNumber());
        long data=db.insert(TABLE_NAME,null,cv);
        return data;
    }
    public long deleteData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        long data = db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        return data;
    }


    public ArrayList<Contacts> getAllContacts() {
        ArrayList<Contacts> contacts1=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if(c.moveToFirst()){
            do{
                String name=c.getString(c.getColumnIndexOrThrow(COLUMN_NAME));
                String number=c.getString(c.getColumnIndexOrThrow(COLUMN_NUMBER));
                int id=c.getInt(c.getColumnIndexOrThrow(COLUMN_ID));
                contacts1.add(new Contacts(id,name,number));
            }while(c.moveToNext());
        }
        c.close();
        return contacts1;
    }
}
