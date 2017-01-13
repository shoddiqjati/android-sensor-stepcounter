package com.example.shodd.myapplication;

import android.content.Context;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private TextView valX, valY, valZ, stepCounter, sevenfiveTV, speedTV;
    private SensorManager sensorManager;
    private Sensor mAccelerometer;
    private Sensor mGyroscope;
    private Sensor mStepCounter;

    private double initStep;
    public static float INIT_STEP_VALUE = 0;
    private static int BLUE_STATE = 0;
    long tStart;
    long tEnd;
    double delta, velocity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valX = (TextView) findViewById(R.id.valx);
        valY = (TextView) findViewById(R.id.valy);
        valZ = (TextView) findViewById(R.id.valz);
        stepCounter = (TextView) findViewById(R.id.stepCounterTV);
        sevenfiveTV = (TextView) findViewById(R.id.sevenfiveTV);
        speedTV = (TextView) findViewById(R.id.speedTV);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        mGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
        tStart = System.currentTimeMillis();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        double step;
        double distanceA, distanceB;
        tEnd = System.currentTimeMillis();
        delta = tEnd - tStart;
//        double x = event.values[0];
        //1m
        if (INIT_STEP_VALUE == 0) {
            initStep = event.values[0];
            step = initStep - initStep;
            INIT_STEP_VALUE = 1;
        } else {
            step = event.values[0] - initStep;
            distanceA = step * 0.6096;
            distanceB = step * 0.75;
            velocity = (distanceB / delta) / 1000;
            String strDistanceA = String.format("%.2f", distanceA) + "m";
            String strDistanceB = String.format("%.2f", distanceB) + "m";
            String strVelocity = String.valueOf(velocity) + "m/s";
            stepCounter.setText(strDistanceA);
            sevenfiveTV.setText(strDistanceB);
            speedTV.setText(strVelocity);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void displayData(String x, String y, String z) {
        valX.setText(x);
        valY.setText(y);
        valZ.setText(z);
    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener(this);
        sensorManager.flush(this);
        INIT_STEP_VALUE = 1;
        super.onStop();
    }

    @Override
    protected void onResume() {
        INIT_STEP_VALUE = 0;
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
