package com.twitter.university.android.yamba;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class TweetActivity extends YambaActivity {
    private static final String TAG = "STATUS";

    public TweetActivity() { super(TAG, R.layout.activity_status); }
}
