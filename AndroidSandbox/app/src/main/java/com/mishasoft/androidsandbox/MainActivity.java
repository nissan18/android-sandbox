package com.mishasoft.androidsandbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Log.d(getLocalClassName(), "message");
    }

    public void gotoSharedPreferences(View view) {
        startActivity(new Intent(this, SharedPreferencesActivity.class));
    }

    public void gotoSavingFiles(View view) {
        startActivity(new Intent(this, SavingFilesActivity.class));
    }

    public void gotoBluetooth(View view) {
        startActivity(new Intent(this, BluetoothActivity.class));
    }

    public void gotoConnectingToWifi(View view) {
        startActivity(new Intent(this, ConnectingToWifiActivity.class));
    }
}
