package com.vengalrao.android.collegestudentregistration.Data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by vengalrao on 24-04-2017.
 */

public class DataContract {
    public static final String AUTHORITY="com.vengalrao.android.collegestudentregistration";
    public static final Uri BASE_CONTENTURI= Uri.parse("content://"+AUTHORITY);

    public static final String PATH_STUDENT="student";
    public static final class Student implements BaseColumns{
        public static final Uri CONTENT_STUDENT_URI=BASE_CONTENTURI.buildUpon().appendPath(PATH_STUDENT).build();
        public static final String TABLE_NAME="Student";
        public static final String NAME="Name";
        public static final String AGE="Age";
        public static final String DEPARTMENT="Department";
        public static final String PHONE="Phone";
        public static final String MAIL="Mail";
        public static final String GENDER="Gender";
    }
}
