/*
 * CSCI 360 Semester Project
 * Team 6ix - Dual Alarm Clock Radio
 * Professor: Dr. Bowring
 */
package com.csci360.alarmclock;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The Alarm class represents an alarm that the user can set the time of, snooze, and turn off. On 
 * initialization, alarms are not set and no alarm will sound unless it has been set to go off by the user.
 */
public class Alarm {

    String name;
    int alarmHour;
    int alarmMinute;
    boolean snoozeOn; // is the alarm in snooze mode?
    boolean alarmIsOn; // is the alarm currently sounding?
    boolean alarmIsSet; // is the alarm going to go off when the time comes?
//	final static int SNOOZETIME = 600000; // 10 minutes in milliseconds CHANGE FOR SUBMISSION
    final static int SNOOZETIME = 5000; // 5 secs in milliseconds

    public Alarm(int alarmClockNum) {
        name = (alarmClockNum == 1) ? "Alarm 1" : "Alarm 2";
        snoozeOn = false;
        alarmIsSet = false;
        alarmHour = 0;
        alarmMinute = 0;
        alarmIsOn = false;
    }

    protected void toggleAlarmIsSet(boolean setting) {
        alarmIsSet = (setting) ? true : false;
    }

    protected void toggleAlarmIsOn(boolean setting) {
        alarmIsOn = (setting) ? true : false;
        if (!alarmIsOn) {
        } else {
        }
    }

    protected boolean isSounding() {
        return alarmIsOn;
    }

    protected void setAlarmHour(int hour) {
        alarmHour = hour;
    }

    protected int getAlarmHour() {
        return alarmHour;
    }

    protected void setAlarmMinute(int minute) {
        alarmMinute = minute;
    }

    protected int getAlarmMinute() {
        return alarmMinute;
    }

    protected boolean isSnoozing() {
        return snoozeOn;
    }

    protected void snoozeAlarm() {
        snoozeOn = true;
        alarmIsOn = false;

        Timer snooze = new Timer();
        snooze.schedule(new TimerTask() {
            @Override
            public void run() {
                alarmIsOn = true;
                snoozeOn = false;
            }
        }, SNOOZETIME);
    }

    protected void turnAlarmSoundOff() {
        alarmIsOn = false;
    }
}
