/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;


public class Radio {
	public int amFrequency = 0; // 540-1600 kHZ for 106 possible bands
	public int fmFrequency = 0; // 88.1-108.1 MHz for 100 possible bands
	public String currentMode = "AM";	
	public int volume = 15; // range 0-100
	public boolean radioOn = false;
	
	public void setAmFmMode(String mode){
		currentMode = mode;
	}
	
	public void setVolume(int volume){
		this.volume = volume;
	}
	
	public void setFrequency(String mode, int frequency){
		if (mode.equals("AM")){
			amFrequency = frequency;
		} else {
			fmFrequency = frequency;
		}
	}
	
	public String getCurrentMode(){
		return currentMode;
	}
	
	public int getCurrentFrequency(){
		return (currentMode == "AM") ? amFrequency : fmFrequency;
	}

	public int getVolume(){
		return volume;
	}
	
	public void toggleRadioOnOff(){
		radioOn = !radioOn;
	}
	
	public void play(){
		if (radioOn){
			System.out.println("radio is playing");
			// get frequency? get mode? play frequency and mode?
		} else {
			System.out.println("Radio is not on.");
		}
	}
	
	public static void main(String[] args){
		Radio r = new Radio();
		r.setFrequency("AM", 12);
		r.toggleRadioOnOff();
		r.setVolume(44);
		System.out.println(r.getCurrentFrequency());
		r.play();
	}
}

