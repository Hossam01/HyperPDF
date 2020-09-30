package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;

public class AndroidBarcodeTestActivite extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidBarcodeView view = new AndroidBarcodeView(this);
        
        setContentView(view);
    }
}