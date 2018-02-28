import java.util.Timer;
import java.util.TimerTask;

public class Clock {
	boolean isMilitary = false;
	Alarm alarm1;
	Alarm alarm2;
	Timer time; 
	int hour;
	int minute;
	int seconds;
	boolean timeStopped = true;
	
	public Clock(){
		time = new Timer();
		alarm1 = new Alarm(1);
		alarm2 = new Alarm(2);
		hour = 0;
		minute = 0;
		startTime();
	}
	
	public void startTime(){
		timeStopped = false;
		time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	if (!timeStopped){
	            	seconds++;
	            	if (seconds == 60){ 
	            		minute++;
	            		seconds = 0;
	            	}
	            	if (minute == 60){
	            		minute = 0;
	            		hour++;
	            		checkForAlarm();
	            	}
	            	if (hour == 24){
	            		hour = 0;
	            	}
	            	//System.out.println(getFullTime());
	            	
            	} else {
            		System.out.println("stopping time");
            		stopTime();
            	}
            }
		}, 0, 1000); // 1 second
	}
	
	public void stopTime(){
		timeStopped = true;
		time.cancel();
		time = new Timer();
	}
	
	public void setTime(int newHour, int newMin){
		stopTime();
		hour = newHour;
		minute = newMin;
		startTime();
	}
	
	public String getFullTime(){
		String secFormatted = Integer.toString(seconds);
		if (seconds < 10){
			secFormatted = "0" + Integer.toString(seconds);
		}
		
		String minFormatted = Integer.toString(minute);
		if (minute < 10){
			minFormatted = "0" + Integer.toString(minute);
		}
		
		String hourFormatted;
		if (!isMilitary){
			hourFormatted = Integer.toString(hour % 12);
			if ((hour % 12) < 10){
				hourFormatted = "0" + Integer.toString(hour % 12);
			}
		} else { // Military time
			hourFormatted = Integer.toString(hour);
			if (hour < 10){
				hourFormatted = "0" + Integer.toString(hour);
			}
		}
		return String.format("%s:%s:%s", hourFormatted, minFormatted, secFormatted);
	}
	
	public String getTime(){
		String minFormatted = Integer.toString(minute);
		if (minute < 10){
			minFormatted = "0" + Integer.toString(minute);
		}
		
		String hourFormatted;
		if (!isMilitary){
			hourFormatted = Integer.toString(hour % 12);
			if ((hour % 12) < 10){
				hourFormatted = "0" + Integer.toString(hour % 12);
			}
		} else { // Military time
			hourFormatted = Integer.toString(hour);
			if (hour < 10){
				hourFormatted = "0" + Integer.toString(hour);
			}
		}
		return String.format("%s:%s", hourFormatted, minFormatted);
	}
	
	public void toggleMilitaryFormat(){
		isMilitary = !isMilitary;
	}
	
	public String getFormattedTime(){
		if (isMilitary){
			return this.getTime();
		} else {
			if (hour >= 12){
				return this.getTime() + " PM";
			} else {
				return this.getTime() + " AM";
			}
		}
	}
	
	public String getFormattedAlarmTime(int alarmNum){
		if (isMilitary){
			return this.getAlarmTime(alarmNum);
		} else {
			if (this.getAlarm(alarmNum).getAlarmHour() >= 12){
				return this.getAlarmTime(alarmNum) + " PM";
			} else {
				return this.getAlarmTime(alarmNum) + " AM";
			}
		}
	}
	
	public void checkForAlarm(){
		if (this.getTime().equals(this.getAlarmTime(1))){
			alarm1.soundAlarm();
		}
		if (this.getTime().equals(this.getAlarmTime(2))){
			alarm2.soundAlarm();
		}
	}
	
	public Alarm getAlarm(int alarmNum){
		return (alarmNum == 1) ? alarm1 : alarm2;
	}
	
	public void setAlarm(int alarmNum, int hour, int minute){
		if (alarmNum == 1){
			alarm1.setAlarmHour(hour);
			alarm1.setAlarmMinute(minute);
		} else {
			alarm2.setAlarmHour(hour);
			alarm2.setAlarmMinute(minute);
		}
	}
	
	public String getAlarmTime(int alarmNum){
		String hourFormatted;
		String minFormatted;
		Alarm alarm = (alarmNum == 1) ? alarm1 : alarm2;
		
		int minute = alarm.getAlarmMinute();
		if (minute < 10){
			minFormatted = "0" + Integer.toString(minute);
		} else {
			minFormatted = Integer.toString(minute);
		}
		
		int hour = alarm.getAlarmHour();
		System.out.println(hour);
		
		if (!isMilitary){
			if ((hour % 12) < 10){
				hourFormatted = "0" + Integer.toString(hour % 12);
			} else {
				hourFormatted = Integer.toString(hour % 12);
			}
			return String.format("%s:%s", hourFormatted, minFormatted);
		} else {
			if (hour < 10){
				hourFormatted = "0" + Integer.toString(hour);
			} else {
				hourFormatted = Integer.toString(hour);
			}
			return String.format("%s:%s", hourFormatted, minFormatted);
		}
	}
	
	public static void main(String[] args) {
		Clock c = new Clock();
		//c.setTime(3, 8);
		//c.toggleMilitaryFormat();
		
		// testing the setTime() function
//		int count = 0;
//		while (!c.getFullTime().equals("00:00:03")){
//		//while (c.seconds < 3){
//			count++;
//		}
//		c.toggleMilitaryFormat();
//		System.out.println("trying to set time");
//		c.setTime(14, 23);
//		while (!c.getFullTime().equals("14:23:10")){
//			count++;
//		}
//		c.toggleMilitaryFormat();
//		System.out.println("set time again");
//		c.setTime(18, 3);
	}

}
