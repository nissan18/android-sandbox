package com.mishasoft.androidsandbox;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private ListView listViewMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(getLocalClassName(), "started onCreate");

        listViewMenu = (ListView) findViewById(R.id.listViewMenu);
        listViewMenu.setOnItemClickListener(this);

//        Log.d(getLocalClassName(), "finished onCreate");
    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

        Resources resources = getResources();
        String selectedItem = resources.getStringArray(R.array.main_menu)[position];

        if (selectedItem.equals("Shared Preferences")) {
            startActivity(new Intent(this, SharedPreferencesActivity.class));
        } else if (selectedItem.equals("Saving Files")) {
            startActivity(new Intent(this, SavingFilesActivity.class));
        } else if (selectedItem.equals("Bluetooth")) {
            startActivity(new Intent(this, BluetoothActivity.class));
        }
    }
}
