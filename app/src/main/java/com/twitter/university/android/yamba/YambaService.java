package com.twitter.university.android.yamba;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by bmeike on 3/26/14.
 */
public class YambaService extends IntentService {
    private static final String TAG = "SVC";

    private static final String PARAM_TWEET = "YambaService.TWEET";

    public YambaService() { super(TAG);  }

    public static void post(Context ctxt, String tweet) {
        Intent i = new Intent(ctxt, YambaService.class);
        i.putExtra(PARAM_TWEET, tweet);
        ctxt.startService(i);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try { Thread.sleep(1000 * 60 * 2); }
        catch (Exception e) {}
        Log.d(TAG,  "Fake message sent!");

    }
}
