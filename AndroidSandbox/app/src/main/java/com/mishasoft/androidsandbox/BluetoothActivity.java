    package com.mishasoft.androidsandbox;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

//import java.io.File;

    public class BluetoothActivity extends AppCompatActivity {
    // https://developer.android.com/guide/topics/connectivity/bluetooth.html

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        TextView textViewDump = (TextView) findViewById(R.id.textViewDump);
        textViewDump.append("onCreate\n");
//        Log.d(getLocalClassName(), "onCreate");

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);
    }

    public void checkStatus(View view) {
        TextView textViewDump = (TextView) findViewById(R.id.textViewDump);
        textViewDump.setText("checkStatus\n");

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            textViewDump.append("Devide does not support Bluetooth\n");
        } else if (bluetoothAdapter.isEnabled()) {
            textViewDump.append("Bluetooth is enabled\n");
        } else {
            textViewDump.append("Bluetooth is NOT enabled\n");
        }
    }

    private static final int REQUEST_ENABLE_BLUETOOTH = 1;

    public void enableBluetooth(View view) {
        TextView textViewDump = (TextView) findViewById(R.id.textViewDump);
        textViewDump.setText("enableBluetooth\n");

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            textViewDump.append("Devide does not support Bluetooth\n");
        } else if (bluetoothAdapter.isEnabled()) {
            textViewDump.append("Bluetooth is ALREADY enabled\n");
        } else {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BLUETOOTH);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView textViewDump = (TextView) findViewById(R.id.textViewDump);
        textViewDump.append("onActivityResult\n");
        if (requestCode == REQUEST_ENABLE_BLUETOOTH) {
            textViewDump.append("requestCode is REQUEST_ENABLE_BLUETOOTH\n");
            if (resultCode == RESULT_OK) {
                textViewDump.append("Bluetooth WAS enabled\n");
            } else if (resultCode == RESULT_CANCELED) {
                textViewDump.append("Bluetooth was NOT enabled\n");
            }
        }
    }

    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            TextView textViewDump = (TextView) findViewById(R.id.textViewDump);
            textViewDump.append("onReceive\n");
            Log.d(getLocalClassName(), "onReceive");
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                textViewDump.append(String.format("name = %s, MAC address = %s\n", device.getName(), device.getAddress()));
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(mReceiver);
    }
}
