/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.util.Timer;
import java.util.TimerTask;

public class AlarmClockRadio {
	Clock clock;
	Radio radio;
	
	public AlarmClockRadio(){
		clock = new Clock();
		radio = new Radio();
		
		System.out.println("Alarm Clock Radio initialized. Please set the current time.");
	}
	
	public void setTime(int hour, int minute){
		clock.setTime(hour, minute);
	}
	
	public String getTime(){
		return clock.getFormattedTime();
	}
	
	public String getFormattedAlarmTime(int alarmNum){
		return clock.getFormattedAlarmTime(alarmNum);
	}
	
	public void setAlarmTime(int alarmNum, int hour, int minute){
		clock.setAlarm(alarmNum, hour, minute);
	}

	public void changeTimeFormat(String format){
		clock.changeStandardMilitaryFormat(format);
	}
	
	public void setAlarmToSound(int alarmNum){
		clock.getAlarm(alarmNum).toggleAlarmIsSet(true);
	}
	
	public void setAlarmTo_NOT_Sound(int alarmNum){
		clock.getAlarm(alarmNum).toggleAlarmIsSet(false);
	}
	
	public void turnAlarmSoundingOff(int alarmNum){ // change to IF alarm1 is sounding...
		clock.getAlarm(alarmNum).toggleAlarmIsOn(false);
	}
	
	public void snoozeAlarm(int alarmNum){ // change to if alarm 1 is sounding....
		clock.getAlarm(alarmNum).snoozeAlarm();
	}
	
	
	
	
	public void turnRadioOnOrOff(){
		radio.toggleRadioOnOff();
	}
	
	public void setFrequency(String mode, int frequency){
		radio.setFrequency(mode, frequency);
	}
	
	public void setVolume(int volume){
		radio.setVolume(volume);
	}
	
	public void setAmFmMode(String mode){
		radio.setAmFmMode(mode);
	}
	
	
	
	
	// this is basically the user
	public static void main(String[] args) {
		AlarmClockRadio a = new AlarmClockRadio();
		a.setTime(5, 16);
		
		System.out.println();
		System.out.println("Would you like the clock to be military or standard format?");
		a.changeTimeFormat("military");
		
		System.out.println();
		// testing the setTime() function
		int count = 0;
		while (!a.clock.getFullTime().equals("05:16:03")){
			count++;
		}
		
		System.out.println("Current time is: ");
		System.out.println(a.getTime());
		
		System.out.println();
		System.out.println("Setting time to 2:20 PM");
		
		// NOTE: Must use military ints to set correct time
		a.setTime(14, 20);
//		while (!a.clock.getFullTime().equals("02:20:05")){ // standard
		while (!a.clock.getFullTime().equals("14:20:05")){
			count++;
		}
		System.out.println();
		System.out.println("Current time is: ");
		System.out.println(a.getTime());
		
		
		// Set alarm times
		System.out.println();
		System.out.println("Set alarm for alarm 1 to 2:21 PM"); // ALARM WILL GO OFF!!
		a.setAlarmTime(1, 14, 21);
		System.out.println("Set alarm for alarm 2 to 12:45 AM");
		a.setAlarmTime(2, 0, 45);
		
		// Although time has been set, you have not specified that the alarm should go off, so now:
		a.setAlarmToSound(1);
		
		System.out.println("\nJumping forward 50 seconds...");
		a.clock.seconds+=50;
		
		System.out.println();
		System.out.println("Alarm time for alarm 1: " + a.getFormattedAlarmTime(1));
		System.out.println("Alarm time for alarm 2: " + a.getFormattedAlarmTime(2));
		
		// Let's let it beep for a few seconds.
		while (!a.clock.getFullTime().equals("14:21:05")){
			count++;
		}
		System.out.println();
		
		a.snoozeAlarm(1);
		// For testing, alarm 1 will snooze for 5 seconds
		
		// after it snoozes, we will snooze it again
		Timer t = new Timer();
		t.schedule(new TimerTask() {
            @Override
            public void run() {
            	System.out.println("\nsnooze it again");
            	a.snoozeAlarm(1);
            }
		}, 10000);
		
		
		// when the alarm goes off again, we will turn it off properly
		Timer k = new Timer();
		k.schedule(new TimerTask() {
            @Override
            public void run() {
            	System.out.println("\nTurning alarm off");
            	a.turnAlarmSoundingOff(1);
            	System.out.println();
            	System.out.println(a.clock.getAlarm(1).alarmIsSet);
        		System.out.println("\nJumping forward 25 seconds...");
        		a.clock.seconds+=25;
            }
		}, 20000);
		
		a.setAlarmTime(2, 14, 22); // set alarm 2, turn it on so it sounds
		a.setAlarmToSound(2);
		
		Timer r = new Timer();
		r.schedule(new TimerTask() {
            @Override
            public void run() {
            	System.out.println("\nTurning alarm off");
            	a.turnAlarmSoundingOff(2);
            }
		}, 40000); // these are set to 10,20, and 40 seconds after clicking "run" for testing purposes
		
		
		/* WHAT JUST HAPPENED?
		 * initialize clock, set time to 05:16
		 * choose to use military format
		 * get current time
		 * change time to 2:20pm (14:20 in military)
		 * get current time (14:20)
		 * set alarm times
		 * jump forward 50 seconds b/c it's boring to wait
		 * print alarm times
		 * 
		 * wait until alarm 1 goes off at 14:21 for 5 seconds
		 * snooze alarm 1 for 5 seconds
		 * alarm 1 goes off again for 5 seconds
		 * alarm 1 is snoozed again for 5 seconds - SNOOZETIME
		 * alarm 1 goes off again for 5 seconds
		 * alarm 1 is turned off - the true under that says that alarm 1 is set to go off again the following day
		 * 
		 * alarm 2's time is set for 14:22
		 * alarm 2 is set to go off alarmIsSet == true
		 * alarm 2 goes off at 14:22 for 10 seconds
		 * alarm 2 is turned off
		 * 
		 * time continues
		 */

	}

}
