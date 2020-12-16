package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ContactsDB
{
    public static  final String KEY_ROWID = "_id";
    public static  final String KEY_NAME = "person_name";
    public static  final String KEY_CELL = "_cell";

    private final String DATABASE_NAME = "ContactsDB";
    private final String DATABASE_TABLE = "ContactsTable";
    private final int DATABASE_VERSION = 1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public ContactsDB (Context context)
    {
        ourContext = context;
    }

    private class DBHelper extends SQLiteOpenHelper
    {

        public DBHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            /* CREATE TABLE ContactsTable (_id INTEGER PRIMARY AUTOINCREMENT,
                person_name TEXT NOT NULL, _cell TEXT NOT NULL); */

            String sqlCode = "CREATE TABLE " + DATABASE_TABLE + " (" +
                    KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NAME + " TEXT NOT NULL, " +
                    KEY_CELL + " TEXT NOT NULL);";

            db.execSQL(sqlCode);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public ContactsDB open() throws SQLException
    {
        ourHelper = new DBHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        ourHelper.close();
    }

    private ContentValues getContentValues(String name, String cell)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_CELL, cell);

        return cv;
    }

    public long createEntry(String name, String cell)
    {
        ContentValues cv = getContentValues(name, cell);

        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public String getData()
    {
        String [] columns = new String [] { KEY_ROWID, KEY_NAME, KEY_CELL };

        Cursor cursor = ourDatabase.query(
                DATABASE_TABLE,
                columns,
                null,
                null,
                null,
                null,
                null);

        String result = "";

        int iRowID = cursor.getColumnIndex(KEY_ROWID);
        int iName = cursor.getColumnIndex(KEY_NAME);
        int iCell = cursor.getColumnIndex(KEY_CELL);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            result = result + cursor.getString(iRowID) + ": " +
                    cursor.getString(iName) + " " +
                    cursor.getString(iCell) + "\n";
        }

        cursor.close();

        return result;
    }

    public long deleteEntry(String rowId)
    {
        return ourDatabase.delete(
                DATABASE_TABLE,
                KEY_ROWID + "=?",
                new String[] { rowId });
    }

    public long updateEntry(String rowId, String name, String cell)
    {
        ContentValues cv = getContentValues(name, cell);

        return ourDatabase.update(
                DATABASE_TABLE, cv,
                KEY_ROWID + "=?",
                new String[] { rowId });
    }
}
