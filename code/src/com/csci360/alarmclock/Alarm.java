/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.util.Timer;
import java.util.TimerTask;

public class Alarm {

    String name;
    int alarmHour;
    int alarmMinute;
    boolean snoozeOn; // is the alarm in snooze mode?
    boolean alarmIsOn; // is the alarm currently sounding?
    boolean alarmIsSet; // is the alarm going to go off when the time comes?
//	final static int SNOOZETIME = 600000; // 10 minutes in milliseconds CHANGE FOR SUBMISSION
    final static int SNOOZETIME = 5000; // 50 secs in milliseconds

    public Alarm(int alarmClockNum) {
        name = (alarmClockNum == 1) ? "Alarm 1" : "Alarm 2";
        snoozeOn = false;
        alarmIsSet = false;
        alarmHour = 0;
        alarmMinute = 0;
        alarmIsOn = false;
    }

    public void toggleAlarmIsSet(boolean setting) {
        alarmIsSet = (setting) ? true : false;
    }

    public void toggleAlarmIsOn(boolean setting) {
        alarmIsOn = (setting) ? true : false;
        if (!alarmIsOn) {
        } else {
        }
    }

    public boolean isSounding() {
        return alarmIsOn;
    }

    public void setAlarmHour(int hour) {
        alarmHour = hour;
    }

    public int getAlarmHour() {
        return alarmHour;
    }

    public void setAlarmMinute(int minute) {
        alarmMinute = minute;
    }

    public int getAlarmMinute() {
        return alarmMinute;
    }

    public boolean isSnoozing() {
        return snoozeOn;
    }

    public void snoozeAlarm() {
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

    public void turnAlarmSoundOff() {
        alarmIsOn = false;
    }
}
