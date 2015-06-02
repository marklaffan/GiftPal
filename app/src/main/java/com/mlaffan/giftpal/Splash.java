package com.mlaffan.giftpal;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
/**
 * Created by Mark on 01/04/2015.
 */
public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}