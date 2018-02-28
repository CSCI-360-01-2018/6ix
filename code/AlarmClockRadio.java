
public class AlarmClockRadio {
	Clock clock;
	Radio radio;
	
	public AlarmClockRadio(){
		clock = new Clock();
		radio = new Radio();
	}
	
	public void setTime(int hour, int minute){
		clock.setTime(hour, minute);
	}
	
	public String getTime(){
		return clock.getFormattedTime();
	}
	
	public void setAlarmTime(int alarmNum, int hour, int minute){
		clock.setAlarm(alarmNum, hour, minute);
	}
	
	public String getAlarmTime(int alarmNum){
		return clock.getAlarmTime(alarmNum);
	}

	public void toggleStandardOrMilitaryFormat(){
		clock.toggleMilitaryFormat();
	}
	
	public static void main(String[] args) {
		Clock clock = new Clock();
		clock.setTime(1, 5);
		//clock.toggleMilitaryFormat();
		System.out.println(clock.isMilitary);
		System.out.println(clock.getFormattedTime());
		
		System.out.println(clock.getFormattedAlarmTime(1));
		clock.setAlarm(1, 19, 35);
		System.out.println(clock.getFormattedAlarmTime(1));

	}

}
