package org.odddev.githubsearcher.core.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.odddev.githubsearcher.core.di.Injector;
import org.odddev.githubsearcher.core.eventBus.RxEventBus;

import javax.inject.Inject;

/**
 * @author kenrube
 * @since 03.12.16
 */

public class ConnectReceiver extends BroadcastReceiver {

    @Inject
    RxEventBus eventBus;

    public ConnectReceiver() {
        Injector.getAppComponent().inject(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            eventBus.send(new ConnectedEvent());
        }
    }
}