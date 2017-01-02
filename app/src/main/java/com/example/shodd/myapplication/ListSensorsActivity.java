package com.example.shodd.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListSensorsActivity extends AppCompatActivity {

    private TextView sensorsTV;

    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sensors);

        sensorsTV = (TextView) findViewById(R.id.sensorsTV);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        int numSensor = deviceSensors.size();
        String sensorName = "";
        for (int i = 0; i < numSensor; i++) {
            sensorName = sensorName + deviceSensors.get(i).toString() + "\n";
        }

        try {
            sensorsTV.setText(sensorName);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
