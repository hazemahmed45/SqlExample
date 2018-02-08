package com.example.hazem.sqlexample;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Hazem on 2/1/2018.
 */

public class DbContract {

    public static final String CONTENT_AUTHORITY = "com.example.hazem.sqlexample";
    public static final String PATH_STUDENTS= "student";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static class StudentEntry implements BaseColumns
    {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_STUDENTS);
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STUDENTS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STUDENTS;



        public static final String TABLE_NAME="student";
        public static final String _ID=BaseColumns._ID;

        public static final String STUDENT_NAME="name";
        public static final String STUDENT_AGE="age";
        public static final String STUDENT_GRADUATE_YEAR="grad_year";
    }
}
