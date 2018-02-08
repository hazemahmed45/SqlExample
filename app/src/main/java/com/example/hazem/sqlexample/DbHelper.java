package com.example.hazem.sqlexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hazem on 2/1/2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="dbexample.db";
    public static final int DATABASE_VERSION=1;
    public DbHelper (Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_STUDENT_TABLE =  "CREATE TABLE " + DbContract.StudentEntry.TABLE_NAME + " ("
                + DbContract.StudentEntry._ID + " INTEGER PRIMARY KEY, "
                + DbContract.StudentEntry.STUDENT_NAME + " TEXT NOT NULL, "
                + DbContract.StudentEntry.STUDENT_AGE + " TEXT NOT NULL , "
                + DbContract.StudentEntry.STUDENT_GRADUATE_YEAR + " INTEGER); ";
        sqLiteDatabase.execSQL (SQL_CREATE_STUDENT_TABLE);
    }

    public long insert(Student student)
    {
        SQLiteDatabase db=this.getWritableDatabase ();
        ContentValues values=new ContentValues ();
        values.put (DbContract.StudentEntry._ID,student.getID ());
        values.put (DbContract.StudentEntry.STUDENT_NAME,student.getName ());
        values.put (DbContract.StudentEntry.STUDENT_AGE,student.getAge ());
        values.put (DbContract.StudentEntry.STUDENT_GRADUATE_YEAR,student.getGraduateYear ());
        return db.insert (DbContract.StudentEntry.TABLE_NAME,null,values);
    }
    public Cursor getQuery()
    {
        String[] projection=
                {
                        DbContract.StudentEntry._ID,
                        DbContract.StudentEntry.STUDENT_NAME,
                        DbContract.StudentEntry.STUDENT_AGE,
                        DbContract.StudentEntry.STUDENT_GRADUATE_YEAR
                };
        SQLiteDatabase db=this.getReadableDatabase ();
        return db.query (
                DbContract.StudentEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
                );
    }
    public int delete(String ID)
    {
        SQLiteDatabase db=this.getWritableDatabase ();
        String whereClause=DbContract.StudentEntry._ID+" Like ?";
        return db.delete (DbContract.StudentEntry.TABLE_NAME,whereClause,new String[]{ID});
    }
    public void update(Student student)
    {
        SQLiteDatabase db=this.getWritableDatabase ();
        ContentValues value=new ContentValues ();
        value.put (DbContract.StudentEntry._ID,student.getID ());
        value.put (DbContract.StudentEntry.STUDENT_NAME,student.getName ());
        value.put (DbContract.StudentEntry.STUDENT_AGE,student.getAge ());
        value.put (DbContract.StudentEntry.STUDENT_GRADUATE_YEAR,student.getGraduateYear ());
        String whereClause=DbContract.StudentEntry._ID+" =?";
        db.update (DbContract.StudentEntry.TABLE_NAME,value,whereClause,new String[]{student.getID ()});
    }
    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
