package com.example.shodd.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private TextView valX, valY, valZ, stepCounter;
    private SensorManager sensorManager;
    private Sensor mAccelerometer;
    private Sensor mGyroscope;
    private Sensor mStepCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valX = (TextView) findViewById(R.id.valx);
        valY = (TextView) findViewById(R.id.valy);
        valZ = (TextView) findViewById(R.id.valz);
        stepCounter = (TextView) findViewById(R.id.stepCounterTV);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        mGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        double x = event.values[0];
        //1m
        stepCounter.setText(String.valueOf(x));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void displayData(String x, String y, String z) {
        valX.setText(x);
        valY.setText(y);
        valZ.setText(z);
    }
}