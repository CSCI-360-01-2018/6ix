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
	
	public Alarm( int alarmClockNum ){
		name = (alarmClockNum == 1) ? "Alarm 1" : "Alarm 2";
		snoozeOn = false;
		alarmIsSet = false;
		alarmHour = 0;
		alarmMinute = 0;
		alarmIsOn = false;
	}
	
	public void toggleAlarmIsSet(boolean setting){
            alarmIsSet = (setting) ? true : false;
//            if (alarmIsSet){
//                    alarmIsOn = true;
//            } else {
//                    alarmIsOn = false;
//            }
	}

	public void toggleAlarmIsOn(boolean setting){
		alarmIsOn = (setting) ? true : false;
                if (!alarmIsOn){
                    System.out.println("Alarm " + this.name + " turned off.");
                } else {
                    System.out.println("Alarm " + this.name + " turned on.");
                }
	}
        
        public boolean isSounding(){
            return alarmIsOn;
        }
	
	public void setAlarmHour( int hour ){
		alarmHour = hour;
	}
	
	public int getAlarmHour(){
		return alarmHour;
	}
	
	public void setAlarmMinute( int minute ){
		alarmMinute = minute;
	}
	
	public int getAlarmMinute(){
		return alarmMinute;
	}
	
	public boolean isSnoozing(){
		return snoozeOn;
	}
	
//	public String getAlarmTime(){
//		String hourFormatted;
//		String minFormatted;
//		if (alarmHour < 10){
//			hourFormatted = "0" + Integer.toString(alarmHour);
//		} else {
//			hourFormatted = Integer.toString(alarmHour);
//		}
//		
//		if (alarmMinute < 10){
//			minFormatted = "0" + Integer.toString(alarmMinute);
//		} else {
//			minFormatted = Integer.toString(alarmMinute);
//		}
//		return String.format("%s:%s", hourFormatted, minFormatted);
//	}
	
	public void snoozeAlarm(){
		System.out.println("\nAlarm " + name + " is snoozing");
		
		snoozeOn = true;
		alarmIsOn = false;
		
		Timer snooze = new Timer();
		snooze.schedule(new TimerTask() {
                @Override
                public void run() {


                    System.out.println("\nfinished snoozing");

                    alarmIsOn = true;
                    snoozeOn = false;
                    soundAlarm();
                    }
                }, SNOOZETIME);

		System.out.println("\n...time continues");
	}
	
	public void soundAlarm(){ // need to change to playing some kind of mp3 file!
		if (alarmIsOn){
			System.out.println(this.name + ": beeep beeeep beeeeeep beeep beeeep!!!!");
		}
	}
	
	public void turnAlarmSoundOff(){
		alarmIsOn = false;
	}

	public static void main(String[] args) {
		Alarm a = new Alarm(1);
		//System.out.println(a.getAlarmTime());
		a.setAlarmHour(1);
		a.setAlarmMinute(1);
		System.out.println();
		
		

		// Testing the snooze function... if you change the delay from SNOOZETIME to 5000 (equivalent to 5 seconds, the 
		// alarm will "snooze" for 3 turns before beeping and then the main() will call for it to cancel the alarm
		// i.e. the user has woken up and turned the alarm off after snoozing 3 times
		
		a.toggleAlarmIsOn(true);
		a.snoozeAlarm();
//
//		Timer t = new Timer(); 
//		t.schedule(new TimerTask() {
//            @Override
//            public void run() {
//	            a.toggleAlarmIsOn(false);
//	            System.out.println("alarm turned off");
//            }
//		}, 15000);
		
		
	}

}

