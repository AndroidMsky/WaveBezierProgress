package com.msky.wavebezierprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private WavaBezierProgress mWavaBezierProgress;
    private int value = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWavaBezierProgress = (WavaBezierProgress) findViewById(R.id.wavaBezierProgress);
        mWavaBezierProgress.setProgress(value);


    }

    public void jia(View v) {
        value += 10;
        mWavaBezierProgress.setProgress(value);
    }

    public void jian(View v) {

        value -= 10;
        mWavaBezierProgress.setProgress(value);
    }

    public void man(View v) {
        value = 100;
        mWavaBezierProgress.setProgress(value);

    }

}
