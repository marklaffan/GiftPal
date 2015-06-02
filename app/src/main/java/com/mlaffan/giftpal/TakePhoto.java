package com.mlaffan.giftpal;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Mark on 06/05/2015.
 */
public class TakePhoto extends Activity{

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_photo);

        Button photoButton = (Button) findViewById(R.id.photoButton);
        imageHolder = (ImageView) findViewById(R.id.imageHolder);

        if(!hasCamera())
            photoButton.setEnabled(false);
    }

    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void launchCamera(View view){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            imageHolder.setImageBitmap(photo);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        RelativeLayout profile_view = (RelativeLayout) findViewById(R.id.take_photo);

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
