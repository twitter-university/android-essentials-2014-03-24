package com.twitter.university.android.yamba;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class TweetActivity extends Activity {
    private static final String TAG = "STATUS";

    private int okColor;
    private int warnColor;
    private int errColor;

    private int tweetLenMax;
    private int warnMax;
    private int errMax;

    private Button submitButton;
    private TextView countView;
    private EditText tweetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        Log.d(TAG, "onCreate: " + this);

        Resources rez = getResources();
        okColor = rez.getColor(R.color.ok);
        warnColor = rez.getColor(R.color.warn);
        errColor = rez.getColor(R.color.error);

        tweetLenMax = rez.getInteger(R.integer.tweet_limit);
        warnMax = rez.getInteger(R.integer.warn_limit);
        errMax = rez.getInteger(R.integer.err_limit);

        submitButton = (Button) findViewById(R.id.tweet_submit);
        submitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) { sendTweet(); }
                }
        );

        countView = (TextView) findViewById(R.id.tweet_count);

        tweetView = ((EditText) findViewById(R.id.tweet_tweet));
        tweetView.addTextChangedListener(
            new TextWatcher() {
                @Override
                public void afterTextChanged(Editable editable) { setCount(); }

                @Override
                public void beforeTextChanged(CharSequence cs, int i, int i2, int i3) {  }

                @Override
                public void onTextChanged(CharSequence cs, int i, int i2, int i3) { }
            }
        );
    }

    void setCount() {
        int n = tweetView.getText().length();

        submitButton.setEnabled(checkTweetLen(n));

        n = tweetLenMax - n;

        int color;
        if (n > warnMax) { color = okColor; }
        else if (n > errMax) { color = warnColor; }
        else  { color = errColor; }

        countView.setText(String.valueOf(n));
        countView.setTextColor(color);
    }

    void sendTweet() {
        String tweet = tweetView.getText().toString();
        if (!checkTweetLen(tweet.length())) { return; }

        tweetView.setText("");

        YambaService.post(this, tweet);
    }

    private boolean checkTweetLen(int n) {
        return (errMax < n) && (tweetLenMax > n);
    }
}
