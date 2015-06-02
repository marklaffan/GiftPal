package com.mlaffan.giftpal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mlaffan.giftpal.sqlite.db.DatabaseHelper;
import com.mlaffan.giftpal.sqlite.db.Idea;

/**
 * Created by Mark on 05/05/2015.
 */
public class GiftList extends Activity{

    EditText ideasInput;
    TextView ideaText;
    DatabaseHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ideas_list);
//        ideasInput = (EditText) findViewById(R.id.ideasInput);
//        ideaText = (TextView) findViewById(R.id.ideaText);
//        dbHelper = new DatabaseHelper(this);
//        printDatabase();
    }

//    public void addButtonClicked(View view){
//        Idea idea = new Idea(ideasInput.getText().toString());
//        dbHelper.addIdea(idea);
//        printDatabase();
//    }
//
//    public void deleteButtonClicked(View view){
//        String inputText = ideasInput.getText().toString();
//        dbHelper.deleteIdea(inputText);
//        printDatabase();
//    }
//
//    public void printDatabase(){
//        String dbString = dbHelper.databaseToString();
//        ideaText.setText(dbString);
//        ideasInput.setText("");
//    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        RelativeLayout profile_view = (RelativeLayout) findViewById(R.id.list_view);

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
