package com.example.david.chapterui;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static android.content.Context.SENSOR_SERVICE;

public class StepCounterFragment extends Fragment implements SensorEventListener {

    private TextView stepText;
    private TextView distanceText;
    private TextView speedText;
    private TextView caloriesText;
    private ProgressBar stepBar;

    private int steps =0;
    private long lastTime=0;
    private float calories=0;

    private final int GOAL = 1000; //Number of steps to be taken
    private final float STEP_LENGTH_AVG = 0.73f/1000f; //Average step length


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_step_counter, null, false);

        stepText = (TextView) view.findViewById(R.id.steps);
        distanceText = (TextView) view.findViewById(R.id.distance);
        speedText = (TextView) view.findViewById(R.id.speed);
        caloriesText = (TextView) view.findViewById(R.id.calories);
        stepBar =(ProgressBar) view.findViewById(R.id.stepBar);

        stepBar.setMax(GOAL);

        registerStepSensor();


        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            if(steps==GOAL){
                steps=0;
                calories=0;
                Toast.makeText(getActivity().getApplicationContext(),"SUCCESS!",Toast.LENGTH_LONG).show();
            }else{
                steps++;
            }
        }

        String s1 = String.format(Locale.ENGLISH, "%.2f", getDistanceFromSteps(steps));
        String s2 = String.format(Locale.ENGLISH, "%.2f", calculateSpeed(event.timestamp));
        String s3 = String.format(Locale.ENGLISH, "%.0f", calories+=.05);

        stepText.setText( steps + "");
        distanceText.setText(s1 );
        speedText.setText( s2 + "");
        caloriesText.setText(s3);

        stepBar.setProgress(steps);
    }

    /**
     *  Never Called for TYPE_STEP_DETECTOR
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SensorManager sm = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        sm.unregisterListener(this);
    }

    /**
     *
     * @param steps the number of steps
     * @return returns the distance calculated from the steps
     */
    private float getDistanceFromSteps(int steps){
        float distance = (steps*STEP_LENGTH_AVG);
        return distance;
    }

    /**
     * Register the step sensor
     */
    private void registerStepSensor() {
        SensorManager sm = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR), SensorManager.SENSOR_DELAY_FASTEST);
    }

    /**
     *
     * @param timestamp the time a user's foot hit the ground in Nanoseconds
     * @return returns the current speed that is calculated by the time difference between the last step and the current
     */
    private float calculateSpeed(long timestamp){
        float speed;
        float time = (float)(timestamp-lastTime)/3600000000000f;
        speed=STEP_LENGTH_AVG/time;
        lastTime=timestamp;
        return  speed;
    }

}
