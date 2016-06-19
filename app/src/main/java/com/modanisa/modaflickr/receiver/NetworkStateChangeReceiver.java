package com.modanisa.modaflickr.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class NetworkStateChangeReceiver extends BroadcastReceiver {
    public static final String TAG = "NetworkStateChange";

    public enum NetworkState{
        CONNECTED, DISCONNECTED
    }

    public static NetworkState state = NetworkState.DISCONNECTED;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
        NetworkState status = getConnectivityStatus(context);
        if(status == NetworkState.CONNECTED)
        {
            Log.d(TAG, "Network is connected.");
            EventBus.getDefault().post(new NetworkConnectivityChanged.Online());
        }
        else
        {
            EventBus.getDefault().post(new NetworkConnectivityChanged.Offline());
        }
    }

    public static NetworkState getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            state = NetworkState.CONNECTED;
        }
        else
        {
            state = NetworkState.DISCONNECTED;
        }
        return state;
    }


    public static class NetworkConnectivityChanged{
        public static class Online {
            public Online(){
                super();
            }
        }

        public static class Offline {
            public Offline(){
                super();
            }
        }
    }

}
