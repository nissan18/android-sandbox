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
import android.widget.ToggleButton;

import java.util.Set;

//import java.io.File;

    public class BluetoothActivity extends AppCompatActivity {
    // https://developer.android.com/guide/topics/connectivity/bluetooth.html
    // https://developer.android.com/samples/BluetoothChat/index.html

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        TextView textViewDump = (TextView) findViewById(R.id.textViewDump);
        textViewDump.append("onCreate\n");
        Log.d(getLocalClassName(), "onCreate");

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);
    }

    public void clearDump(View view) {
        TextView textViewDump = (TextView) findViewById(R.id.textViewDump);
        textViewDump.setText("");
    }

    public void checkStatus(View view) {
        TextView textViewDump = (TextView) findViewById(R.id.textViewDump);
        textViewDump.append("checkStatus\n");

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            textViewDump.append("Device does not support Bluetooth\n");
        } else if (bluetoothAdapter.isEnabled()) {
            textViewDump.append("Bluetooth is enabled\n");
        } else {
            textViewDump.append("Bluetooth is NOT enabled\n");
        }
    }

    private static final int REQUEST_ENABLE_BLUETOOTH = 1;
    public void enableBluetooth(View view) {
        TextView textViewDump = (TextView) findViewById(R.id.textViewDump);
        textViewDump.append("enableBluetooth\n");

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            textViewDump.append("Device does not support Bluetooth\n");
        } else if (bluetoothAdapter.isEnabled()) {
            textViewDump.append("Bluetooth is ALREADY enabled\n");
        } else {
            Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetoothIntent , REQUEST_ENABLE_BLUETOOTH);
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

    public void disableBluetooth(View view) {
        TextView textViewDump = (TextView) findViewById(R.id.textViewDump);
        textViewDump.append("disableBluetooth\n");

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            textViewDump.append("Device does not support Bluetooth\n");
        } else if (!bluetoothAdapter.isEnabled()) {
            textViewDump.append("Bluetooth is NOT enabled\n");
        } else {
            bluetoothAdapter.disable();
        }
    }

    public void startDiscovery(View view) {
        TextView textViewDump = (TextView) findViewById(R.id.textViewDump);
        textViewDump.append("startDiscovery\n");

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            textViewDump.append("Device does not support Bluetooth\n");
        } else if (!bluetoothAdapter.isEnabled()) {
            textViewDump.append("Bluetooth is NOT enabled\n");
        } else {
            bluetoothAdapter.startDiscovery();
        }
    }

    public void cancelDiscovery(View view) {
        TextView textViewDump = (TextView) findViewById(R.id.textViewDump);
        textViewDump.append("cancelDiscovery\n");

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            textViewDump.append("Device does not support Bluetooth\n");
        } else if (!bluetoothAdapter.isEnabled()) {
            textViewDump.append("Bluetooth is NOT enabled\n");
        } else {
            bluetoothAdapter.cancelDiscovery();
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
