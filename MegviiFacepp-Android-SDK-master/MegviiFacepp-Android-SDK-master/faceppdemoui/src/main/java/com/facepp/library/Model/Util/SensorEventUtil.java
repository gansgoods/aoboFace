package com.facepp.library.Model.Util;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorEventUtil implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensor; /*加速度传感器*/

    public int orientation = 0;

    public SensorEventUtil(Activity activity) {

        mSensorManager = (SensorManager) activity.getSystemService(activity.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);// TYPE_GRAVITY
        // 参数三，检测的精准度
        /*注册监听*/
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);// SENSOR_DELAY_GAME
    }

    /*传感器的精度发生变化时触发*/
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /*磁阻传感器方向改变时触发*/
    @Override
    public void onSensorChanged(SensorEvent event) {
        final double G = 9.81;
        final double SQRT2 = 1.414213;
        if (event.sensor == null) {
            return;
        }
        /*判断是否属于指定的加速度传感器数据，若属于则获取XYZ轴的数据，应该和3d建模有关*/
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            if (z >= G / SQRT2) { //screen is more likely lying on the table
                if (x >= G / 2) {
                    orientation = 1;
                } else if (x <= -G / 2) {
                    orientation = 2;
                } else if (y <= -G / 2) {
                    orientation = 3;
                } else {
                    orientation = 0;
                }
            } else {
                if (x >= G / SQRT2) {
                    orientation = 1;
                } else if (x <= -G / SQRT2) {
                    orientation = 2;
                } else if (y <= -G / SQRT2) {
                    orientation = 3;
                } else {
                    orientation = 0;
                }
            }
        }
    }
}