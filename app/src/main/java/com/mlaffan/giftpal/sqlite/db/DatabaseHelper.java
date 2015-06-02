package com.mlaffan.giftpal.sqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Mark on 02/05/2015.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "people_directory";
    public static final String TABLE_PEOPLE = "people";
    public static final String TABLE_IDEAS = "ideas";
    public static final int DATABASE_VERSION = 2;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS people (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstName TEXT, " +
                "lastName TEXT, " +
                "sex TEXT, " +
                "birthday TEXT)";
        db.execSQL(sql);

        String sqlI = "CREATE TABLE IF NOT EXISTS ideas (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "idea TEXT)";

//                +
//                "personId INTEGER, " +
//                "FOREIGN KEY(personId) REFERENCES people(_id)";
        db.execSQL(sqlI);

//        ContentValues values = new ContentValues();
//
//        values.put("firstName", "John");
//        values.put("lastName", "Smith");
//        values.put("sex", "M");
//        values.put("birthday", "1984-08-09");

//        values.put("firstName", "Robert");
//        values.put("lastName", "Jackson");
//        values.put("sex", "M");
//        values.put("birthday", "1965-03-15");
//
//        values.put("firstName", "Marie");
//        values.put("lastName", "Potter");
//        values.put("sex", "F");
//        values.put("birthday", "1981-08-30");
//
//        values.put("firstName", "Lisa");
//        values.put("lastName", "Jordan");
//        values.put("sex", "F");
//        values.put("birthday", "1984-10-11");
//
//        db.insert(TABLE_NAME, null, values);
//        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLES IF EXISTS people AND ideas");
        onCreate(db);
    }

    public void addPerson(Person person){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("firstName", person.getFirstName());
        cv.put("lastName", person.getLastName());
        cv.put("sex", person.getSex());
        cv.put("birthday", person.getBirthday());
        db.insert(TABLE_PEOPLE, null, cv);
        db.close();
        Log.i("AddPerson", "Added: " + person.getFirstName());
    }

    // Add an idea row - foreign key??
    public void addIdea(Idea idea){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idea", idea.getIdea());
        db.insert(TABLE_IDEAS, null, values);
        db.close();
    }

    //Delete an idea
    public void deleteIdea(String idea){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_IDEAS+ " WHERE idea =\"" + idea + "\";" );
    }

    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_IDEAS + " WHERE 0";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("idea"))!=null){
                dbString += c.getString(c.getColumnIndex("idea"));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
    }

    public ArrayList<Person> getPeople(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PEOPLE;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Person> people = new ArrayList<Person>();

        if(cursor.moveToFirst()){
            do{
                Person person = new Person();
                person.setId(cursor.getInt(0));
                person.setFirstName(cursor.getString(1));
                person.setLastName(cursor.getString(2));
                person.setSex(cursor.getString(3));
                person.setBirthday(cursor.getString(4));
                people.add(person);
            }while(cursor.moveToNext());
            cursor.close();
        }

        Log.i("GetPeople", "Number of people in database: " + people.size());
        return people;
    }

}
