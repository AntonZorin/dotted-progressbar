package com.antonzorin.dottedprogressbarexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.antonzorin.dottedprogressbar.DottedProgressBar;

public class MainActivity extends AppCompatActivity {
    DottedProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (DottedProgressBar) findViewById(R.id.progress);
        mProgressBar.setClockWiseDots(false);

    }
}
