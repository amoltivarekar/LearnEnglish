package com.example.amolt.learnenglish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper
{
    public static final String TAG=DbHelper.class.getSimpleName();
    public static final String DB_NAME="myapp.dp";
    public static final int DB_VERSION=1;

    public static final String TABLE_NAME="users";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_EMAIL="email";
    public static final String COLUMN_PASSWORD="password";

    public static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("
            +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_EMAIL+" TEXT,"
            +COLUMN_PASSWORD+" TEXT);";

    public DbHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXITS "+TABLE_NAME);
    }

    public void addUser(String email,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL,email);
        values.put(COLUMN_PASSWORD,password);

        long id = db.insert(TABLE_NAME,null,values);

        Log.d(TAG, "user inserted "+id);
    }

    public boolean getUser(String email,String pass)
    {
        String selectQuery = "select * from "+TABLE_NAME+" where "+COLUMN_EMAIL+" = "+"'"+email+"'"+" and "+COLUMN_PASSWORD+" = "+"'"+pass+"'";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        if(cursor.getCount()>0)
        {
            return true;
        }
        cursor.close();
        db.close();

        return false;
    }
}
