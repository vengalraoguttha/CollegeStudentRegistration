package com.vengalrao.android.collegestudentregistration.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by vengalrao on 24-04-2017.
 */

public class DataProvider extends ContentProvider {
    public static final int STUDENT=100;
    public static final int STUDENT_ID=101;

    private DataDBHelper mDataDBHelper;
    private static final UriMatcher sUriMacher=buildUriMatcher();

    public static UriMatcher buildUriMatcher(){
        UriMatcher uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(DataContract.AUTHORITY,DataContract.PATH_STUDENT,STUDENT);
        uriMatcher.addURI(DataContract.AUTHORITY,DataContract.PATH_STUDENT+"/*",STUDENT_ID);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        Context context=getContext();
        mDataDBHelper=new DataDBHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db=mDataDBHelper.getReadableDatabase();
        int match=sUriMacher.match(uri);
        Cursor retCursor;
        switch (match){
            case STUDENT:
                retCursor=db.query(
                        DataContract.Student.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Operation not supported.");
        }
        retCursor.setNotificationUri(getContext().getContentResolver(),uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db=mDataDBHelper.getWritableDatabase();
        int match=sUriMacher.match(uri);
        Uri returnUri;
        switch (match){
            case STUDENT:
                long id=db.insert(DataContract.Student.TABLE_NAME,null,values);
                if(id>0){
                    returnUri= ContentUris.withAppendedId(DataContract.Student.CONTENT_STUDENT_URI,id);
                }else{
                    throw new android.database.SQLException("Failed to insert into General table "+uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Operation Not Supported");
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db=mDataDBHelper.getWritableDatabase();
        int match=sUriMacher.match(uri);
        int retDelRows;
        switch (match){
            case STUDENT_ID:
                String id2=uri.getPathSegments().get(1);
                String mSelection2="Name=?";
                String[] mSelectionArgs2=new String[]{id2};
                retDelRows=db.delete(
                        DataContract.Student.TABLE_NAME,
                        mSelection2,
                        mSelectionArgs2
                );
                break;
            default:
                throw new UnsupportedOperationException("Operation not supported");
        }
        if(retDelRows!=0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return retDelRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
