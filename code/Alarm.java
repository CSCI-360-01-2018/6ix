import java.util.Timer;
import java.util.TimerTask;

public class Alarm {
	String name;
	boolean alarmIsSet;
	int alarmHour;
	int alarmMinute;
	boolean snoozeOn;
	boolean alarmIsOn;
	boolean alarmSounding = false;
	final static int SNOOZETIME = 600000; // 10 minutes in milliseconds
	
	public Alarm( int alarmClockNum ){
		name = (alarmClockNum == 1) ? "Alarm 1" : "Alarm 2";
		alarmIsSet = false;
		snoozeOn = false;
		alarmHour = 0;
		alarmMinute = 0;
		alarmIsOn = false;
	}

	public void toggleAlarmIsOn(boolean setting){
		alarmIsOn = (setting) ? true : false;
	}
	
	public void toggleAlarmIsSet(){
		alarmIsSet = !alarmIsSet;
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
		snoozeOn = true;
		Timer snooze = new Timer();
		snooze.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
	            if (!alarmIsOn){
	            	snooze.cancel();
	            	snoozeOn = false;
	            	System.out.println("snooze cancelled");
	            } else {
	            	soundAlarm();
	            } 
            }
		}, 0, 5000); // Make sure this is SNOOZETIME for "production"!
	}
	
	public void soundAlarm(){ // need to change to playing some kind of mp3 file!
		if (alarmIsOn){
			System.out.println("beeep beeeep beeeeeep beeep beeeep!!!!");
		}
	}
	
	public void turnAlarmOff(){
		alarmIsOn = false;
	}

	public static void main(String[] args) {
		Alarm a = new Alarm(1);
		//System.out.println(a.getAlarmTime());
		a.setAlarmHour(8);
		a.setAlarmMinute(1);
		//System.out.println(a.getAlarmTime());
		
		

		// Testing the snooze function... if you change the delay from SNOOZETIME to 5000 (equivalent to 5 seconds, the 
		// alarm will "snooze" for 3 turns before beeping and then the main() will call for it to cancel the alarm
		// i.e. the user has woken up and turned the alarm off after snoozing 3 times
		
		a.toggleAlarmIsOn(true);
		a.snoozeAlarm();

		Timer t = new Timer(); 
		t.schedule(new TimerTask() {
            @Override
            public void run() {
	            a.toggleAlarmIsOn(false);
	            System.out.println("alarm turned off");
            }
		}, 15000);
		
		
	}

}
