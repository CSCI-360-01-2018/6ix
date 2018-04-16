/*
 * CSCI 360 Semester Project
 * Team 6ix - Dual Alarm Clock Radio
 * Professor: Dr. Bowring
 */
package com.csci360.alarmclock;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The Clock class keeps time for the system. The user can set and change the time of the clock as well
 * as set the format for the time to Military or Standard format. The clock also contains the two alarms
 * and is responsible for alerting the user when an alarm is set to go off and the time has come for it 
 * to go off.
 */
public class Clock {

    public boolean isMilitary = false;
    private Alarm alarm1;
    private Alarm alarm2;
    private Timer time;
    private int hour;
    private int minute;
    public int seconds;
    private boolean timeStopped = true;
    private static int ONE_SEC = 1000; // 1 second in milliseconds

    public Clock() {
        time = new Timer();
        alarm1 = new Alarm(1);
        alarm2 = new Alarm(2);
        hour = 0;
        minute = 0;
        startTime();
    }

    protected void startTime() {
        timeStopped = false;
        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!timeStopped) {
                    seconds++;
                    if (seconds == 60) {
                        minute++;
                        seconds = 0;
                    }
                    if (minute == 60) {
                        minute = 0;
                        hour++;
                    }
                    if (hour == 24) {
                        hour = 0;
                    }
                    System.out.println(getFullTime());
                    if (seconds == 0) {
                        checkForAlarm();
                    }
                } else {
                    stopTime();
                }
            }
        }, 0, ONE_SEC); // 1 second
    }

    protected void stopTime() {
        timeStopped = true;
        time.cancel();
        time = new Timer();
    }

    protected void setTime(int newHour, int newMin) {
        stopTime();
        hour = newHour;
        minute = newMin;
        startTime();
    }

    protected int getHour() {
        return this.hour;
    }

    protected int getMinute() {
        return this.minute;
    }

    protected String getFullTime() {
        String secFormatted = Integer.toString(seconds);
        if (seconds < 10) {
            secFormatted = "0" + Integer.toString(seconds);
        }

        String minFormatted = Integer.toString(minute);
        if (minute < 10) {
            minFormatted = "0" + Integer.toString(minute);
        }

        String hourFormatted;
        if (!isMilitary) {
            if (hour == 12 || hour == 0) {
                hourFormatted = "12";
            } else if (hour < 10 || (hour % 12) < 10) {
                hourFormatted = "0" + Integer.toString(hour % 12);
            } else {
                hourFormatted = Integer.toString(hour % 12);
            }
        } else { // Military time
            hourFormatted = Integer.toString(hour);
            if (hour < 10) {
                hourFormatted = "0" + Integer.toString(hour);
            }
        }
        return String.format("%s:%s:%s", hourFormatted, minFormatted, secFormatted);
    }

    protected String getTime() {
        String minFormatted = Integer.toString(minute);
        if (minute < 10) {
            minFormatted = "0" + Integer.toString(minute);
        }

        String hourFormatted;
        if (!isMilitary) {
            if (hour == 12 || hour == 0) {
                hourFormatted = "12";
            } else if (hour < 10 || (hour % 12) < 10) {
                hourFormatted = "0" + Integer.toString(hour % 12);
            } else {
                hourFormatted = Integer.toString(hour % 12);
            }
        } else { // Military time
            hourFormatted = Integer.toString(hour);
            if (hour < 10) {
                hourFormatted = "0" + Integer.toString(hour);
            }
        }
        return String.format("%s:%s", hourFormatted, minFormatted);
    }

    protected void changeStandardMilitaryFormat(String format) {
        if (format.equals("military")) {
            isMilitary = true;
            System.out.println("Clock time set to military.");
        } else {
            isMilitary = false;
            System.out.println("Clock time set to standard.");
        }
    }
    
    protected boolean isMilitary(){
        return isMilitary;
    }

    protected String getFormattedTime() {
        if (isMilitary) {
            return this.getTime();
        } else {
            if (hour >= 12) {
                return this.getTime() + " PM";
            } else {
                return this.getTime() + " AM";
            }
        }
    }

    protected String getFormattedAlarmTime(int alarmNum) {
        if (isMilitary) {
            return this.getAlarmTime(alarmNum);
        } else {
            if (this.getAlarm(alarmNum).getAlarmHour() >= 12) {
                return this.getAlarmTime(alarmNum) + " PM";
            } else {
                return this.getAlarmTime(alarmNum) + " AM";
            }
        }
    }

    protected void checkForAlarm() {
        if (this.getTime().equals(this.getAlarmTime(1)) && alarm1.alarmIsSet) {
            alarm1.toggleAlarmIsOn(true);
        }
        if (this.getTime().equals(this.getAlarmTime(2)) && alarm2.alarmIsSet) {
            alarm2.toggleAlarmIsOn(true);
        }
    }

    protected Alarm getAlarm(int alarmNum) {
        return (alarmNum == 1) ? alarm1 : alarm2;
    }

    protected void setAlarm(int alarmNum, int hour, int minute) {
        if (alarmNum == 1) {
            alarm1.setAlarmHour(hour);
            alarm1.setAlarmMinute(minute);
        } else {
            alarm2.setAlarmHour(hour);
            alarm2.setAlarmMinute(minute);
        }
    }

    protected String getAlarmTime(int alarmNum) {
        String hourFormatted;
        String minFormatted;
        Alarm alarm = (alarmNum == 1) ? alarm1 : alarm2;

        int minute = alarm.getAlarmMinute();
        if (minute < 10) {
            minFormatted = "0" + Integer.toString(minute);
        } else {
            minFormatted = Integer.toString(minute);
        }

        int hour = alarm.getAlarmHour();

        if (!isMilitary) {
            if (hour == 12 || hour == 0) {
                hourFormatted = "12";
            } else if (hour < 10 || (hour % 12) < 10) {
                hourFormatted = "0" + Integer.toString(hour % 12);
            } else {
                hourFormatted = Integer.toString(hour % 12);
            }
            return String.format("%s:%s", hourFormatted, minFormatted);
        } else {
            if (hour < 10) {
                hourFormatted = "0" + Integer.toString(hour);
            } else {
                hourFormatted = Integer.toString(hour);
            }
            return String.format("%s:%s", hourFormatted, minFormatted);
        }
    }
}
