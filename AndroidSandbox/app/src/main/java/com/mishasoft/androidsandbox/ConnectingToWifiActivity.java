package com.mishasoft.androidsandbox;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ConnectingToWifiActivity extends AppCompatActivity {
    private final IntentFilter intentFilter = new IntentFilter();

    WifiP2pManager mManager;
    WifiP2pManager.Channel mChannel;
    boolean mIsWifiEnabled = false;
    MyBroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connecting_to_wifi);
        //  Indicates a change in the Wi-Fi P2P status.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);

        // Indicates a change in the list of available peers.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);

        // Indicates the state of Wi-Fi P2P connectivity has changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

        // Indicates this device's details have changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), null);
//        Log.d(getLocalClassName(), "got here");
    }

    class MyBroadcastReceiver extends BroadcastReceiver {
        public MyBroadcastReceiver() {
        }
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
                Log.d(getLocalClassName(), "WIFI_P2P_STATE_CHANGED_ACTION");
                // Determine if Wifi P2P mode is enabled or not, alert the Activity.
                int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
                if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                    Log.d(getLocalClassName(), "mIsWifiEnabled = true");
                    mIsWifiEnabled = true;
                } else {
                    Log.d(getLocalClassName(), "mIsWifiEnabled = false");
                    mIsWifiEnabled = false;
                }
            } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
                Log.d(getLocalClassName(), "WIFI_P2P_PEERS_CHANGED_ACTION");
                // The peer list has changed!  We should probably do something about
                // that.

            } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
                Log.d(getLocalClassName(), "WIFI_P2P_CONNECTION_CHANGED_ACTION");
                // Connection state changed!  We should probably do something about
                // that.

            } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
                Log.d(getLocalClassName(), "WIFI_P2P_THIS_DEVICE_CHANGED_ACTION");
//            DeviceListFragment fragment = (DeviceListFragment) activity.getFragmentManager()
//                    .findFragmentById(R.id.frag_list);
//            fragment.updateThisDevice((WifiP2pDevice) intent.getParcelableExtra(
//                    WifiP2pManager.EXTRA_WIFI_P2P_DEVICE));

            }
        }
    }

//    @Override
//    public  void onResume() {
//        super.onResume();
//        mReceiver = new MyBroadcastReceiver(mManager, mChannel, this);
//        registerReceiver(mReceiver, intentFilter);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        unregisterReceiver(mReceiver);
//    }
}
