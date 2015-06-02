package com.mlaffan.giftpal;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.content.Intent;
import android.view.View;

import com.mlaffan.giftpal.sqlite.db.DatabaseHelper;
import com.mlaffan.giftpal.sqlite.db.Person;

/**
 * Created by Mark on 05/05/2015.
 */
public class Profile extends Activity  {

    protected TextView profileFirst;
    protected TextView profileLast;
    protected TextView profileBirthday;
    protected TextView profileSex;
    protected int profileId;
    Person person;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view);

        person = (Person) getIntent().getSerializableExtra("person");

        profileFirst = (TextView) findViewById(R.id.profileFirst);
        profileFirst.setText(person.getFirstName());

        profileLast = (TextView) findViewById(R.id.profileLast);
        profileLast.setText(person.getLastName());

        profileSex = (TextView) findViewById(R.id.profileSex);
        profileSex.setText(person.getSex());

        profileBirthday = (TextView) findViewById(R.id.profileBirthday);
        profileBirthday.setText(person.getBirthday());

    }

    public void onClick(View view){
        Intent i = new Intent(this, GiftList.class);
        startActivity(i);
    }

    public void onSnap(View view){
        Intent intent = new Intent(this, TakePhoto.class);
        startActivity(intent);
    }

    public void onIdea(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com/best-sellers-books-Amazon/zgbs/books"));
        startActivity(browserIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        RelativeLayout profile_view = (RelativeLayout) findViewById(R.id.profile_view);

        switch (item.getItemId()){
            case  R.id.menu_red:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                profile_view.setBackgroundColor(Color.RED);
                return true;
            case  R.id.menu_blue:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                profile_view.setBackgroundColor(Color.BLUE);
                return true;
            case  R.id.menu_yellow:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                profile_view.setBackgroundColor(Color.YELLOW);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
