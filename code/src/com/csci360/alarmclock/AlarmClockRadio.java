/*
 * CSCI 360 Semester Project
 * Team 6ix - Dual Alarm Clock Radio
 * Professor: Dr. Bowring
 */
package com.csci360.alarmclock;

import java.io.IOException;

/**
 * The AlarmClockRadio class interfaces with the GUI, providing all the public methods for the user to
 * manipulate the system. This class contains the system Clock and Radio objects, which in turn contain 
 * the Alarm, Radio, and Station objects, respectively. 
 */
public class AlarmClockRadio {

    Clock clock;
    Radio radio;

    public AlarmClockRadio() {
        clock = new Clock();
        radio = new Radio();
    }

    public Clock getClock() {
        return this.clock;
    }

    public Alarm getAlarm(int alarmNum) {
        return this.clock.getAlarm(alarmNum);
    }

    public Radio getRadio() {
        return this.radio;
    }

    public void setTime(int hour, int minute) {
        clock.setTime(hour, minute);
    }

    public String getTime() {
        return clock.getFormattedTime();
    }
    
    public int getHour(){
        return clock.getHour();
    }
    
    public int getMinute(){
        return clock.getMinute();
    }

    public int getAlarmHour(int alarmNum){
        return clock.getAlarm(alarmNum).getAlarmHour();
    }
    
    public int getAlarmMinute(int alarmNum){
        return clock.getAlarm(alarmNum).getAlarmMinute();
    }

    public String getFormattedAlarmTime(int alarmNum) {
        return clock.getFormattedAlarmTime(alarmNum);
    }

    public void setAlarmTime(int alarmNum, int hour, int minute) {
        clock.setAlarm(alarmNum, hour, minute);
    }

    public void changeTimeFormat(String format) {
        clock.changeStandardMilitaryFormat(format);
    }
    
    public boolean isClockMilitary(){
        return clock.isMilitary();
    }

    public void setAlarmToSound(int alarmNum) {
        clock.getAlarm(alarmNum).toggleAlarmIsSet(true);
    }

    public void setAlarmTo_NOT_Sound(int alarmNum) {
        clock.getAlarm(alarmNum).toggleAlarmIsSet(false);
    }

    public void turnAlarmSoundingOff(int alarmNum) { // change to IF alarm1 is sounding...
        clock.getAlarm(alarmNum).toggleAlarmIsOn(false);
    }

    public void snoozeAlarm(int alarmNum) { // change to if alarm 1 is sounding....
        clock.getAlarm(alarmNum).snoozeAlarm();
    }

    public void turnRadioOnOrOff() throws IOException {
        radio.toggleRadioOnOff();
    }

    public void setFrequency(String mode, double frequency) {
        radio.setFrequency(mode, frequency);
    }

    public void setVolume(double volume) {
        radio.setVolume(volume / 100);
    }

    public double getVolume() {
        return radio.getVolume();
    }

    public void setAmFmMode(String mode) {
        radio.setAmFmMode(mode);
    }
}
