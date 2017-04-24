package com.vengalrao.android.collegestudentregistration.Data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vengalrao on 24-04-2017.
 */

public class DataDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="student.db";
    private static final int VERSION=1;

    public DataDBHelper(Context context){
        super(context,DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_STUDENT_TABLE="CREATE TABLE "+DataContract.Student.TABLE_NAME+" ("+
                DataContract.Student._ID+" INTEGER PRIMARY KEY, "+
                DataContract.Student.NAME+" TEXT NOT NULL, "+
                DataContract.Student.AGE+" TEXT NOT NULL, "+
                DataContract.Student.DEPARTMENT+" TEXT NOT NULL, "+
                DataContract.Student.PHONE+" TEXT NOT NULL, "+
                DataContract.Student.MAIL+" TEXT NOT NULL, "+
                DataContract.Student.GENDER+" TEXT NOT NULL ); ";
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DEL_STUDENT_TABLE="DROP TABLE IF EXITS "+DataContract.Student.TABLE_NAME;
        db.execSQL(DEL_STUDENT_TABLE);
        onCreate(db);
    }
}
