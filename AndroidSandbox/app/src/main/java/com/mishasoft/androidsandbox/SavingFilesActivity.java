package com.mishasoft.androidsandbox;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.io.File;
import java.io.FileOutputStream;

public class SavingFilesActivity extends AppCompatActivity {
//    https://developer.android.com/training/basics/data-storage/files.html

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_files);
    }

    public void createFile(View view) {
        String filename = getFilename();
        if (getDirectoryType() == "Internal") {
            createFileInInternalDirectory(filename);
        }
        else if (getDirectoryType() == "Cache") {
            createFileInCacheDirectory(filename);
        }
    }

    private void createFileInInternalDirectory(String filename) {
        FileOutputStream outputStream;
        String filetext = "hello";

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(filetext.getBytes());
            outputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createFileInCacheDirectory(String filename) {
        try {
            File file = File.createTempFile(filename, null, getCacheDir());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void listFiles(View view) {
        File directory = getDirectory();
        File[] files = directory.listFiles();
        Log.d(getLocalClassName(), "directory: " + directory.toString());
        Log.d(getLocalClassName(), "files: " + files.length);
        for (File file : files) {
            Log.d(getLocalClassName(), "file: " + file.getName());
        }
    }

    public void deleteFile(View view) {
        File file = new File(getDirectory(), getFilename());
        Boolean deleted = file.delete();

        Log.d(getLocalClassName(), "deleted: " + deleted);
    }

    private String getFilename() {
        EditText editTextKey = (EditText) findViewById(R.id.editTextFilename);
        return editTextKey.getText().toString().trim();
    }

    private File getDirectory() {
        RadioGroup radioGroupDirectoryType = (RadioGroup) findViewById((R.id.radioGroupDirectoryType));
        if (radioGroupDirectoryType.getCheckedRadioButtonId() == R.id.radioButtonInternalDirectory)
            return getFilesDir();
        else if (radioGroupDirectoryType.getCheckedRadioButtonId() == R.id.radioButtonCacheDirectory)
            return getCacheDir();
        return null;
    }

    private String getDirectoryType() {
        RadioGroup radioGroupDirectoryType = (RadioGroup) findViewById((R.id.radioGroupDirectoryType));
        if (radioGroupDirectoryType.getCheckedRadioButtonId() == R.id.radioButtonInternalDirectory)
            return "Internal";
        else if (radioGroupDirectoryType.getCheckedRadioButtonId() == R.id.radioButtonCacheDirectory)
            return "Cache";
        return null;
    }
}
