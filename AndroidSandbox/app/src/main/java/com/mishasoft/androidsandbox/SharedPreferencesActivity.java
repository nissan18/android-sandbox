package com.mishasoft.androidsandbox;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

public class SharedPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
    }

    public void pushKeyValue(View view) {
        EditText editTextKey = (EditText) findViewById(R.id.editTextKey);
        String key = editTextKey.getText().toString();

        EditText editTextValue = (EditText) findViewById(R.id.editTextValue);
        String value = editTextValue.getText().toString();

        getPreferences(Context.MODE_PRIVATE).edit().putString(key, value).commit();

        editTextKey.setText("");
        editTextValue.setText("");

        Log.d(getLocalClassName(), "pushKeyValue");
        Log.d(getLocalClassName(), key + " => " + value);

    }

    public void dumpAllKeyValues(View view) {
        TextView textViewDump = (TextView) findViewById(R.id.textViewDump);
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sp.getAll();
        textViewDump.setText("");
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            textViewDump.append(String.format("%s => %s\n", entry.getKey(), entry.getValue()));
        }
    }

    public void clearAllKeyValues(View view) {
        getPreferences(Context.MODE_PRIVATE).edit().clear().commit();
        TextView textViewDump = (TextView) findViewById(R.id.textViewDump);
        textViewDump.setText("");
    }
}
