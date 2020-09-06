package com.sabkayar.praveen.power_receiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.sabkayar.praveen.sample_broadcast_receiver.BuildConfig;
import com.sabkayar.praveen.sample_broadcast_receiver.R;

public class MainActivity extends AppCompatActivity {

    private CustomBroadcastReceiver mCustomBroadcastReceiver = new CustomBroadcastReceiver();
    private AnotherBroadcastReceiver mAnotherBroadcastReceiver = new AnotherBroadcastReceiver();
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);

        // Register the receiver using the activity context.
        this.registerReceiver(mCustomBroadcastReceiver, intentFilter);


        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);

        // Register the receiver using the activity context.
        this.registerReceiver(mAnotherBroadcastReceiver, intentFilter);


        //Register receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mCustomBroadcastReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));

    }

    @Override
    protected void onDestroy() {
        //Unregister the receiver
        this.unregisterReceiver(mCustomBroadcastReceiver);

        this.unregisterReceiver(mAnotherBroadcastReceiver);

        //Unregister receiver
        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(mCustomBroadcastReceiver);
        super.onDestroy();
    }

    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }
}