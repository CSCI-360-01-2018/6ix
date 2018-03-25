/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Radio {
	public int amFrequency = 0; // 540-1600 kHZ for 106 possible bands
	public int fmFrequency = 0; // 88.1-108.1 MHz for 100 possible bands
	public String currentMode = "AM";	
	public int volume = 15; // range 0-100
	public boolean radioOn = false;
        List<Station> amStations = new ArrayList<Station>();
        List<Station> fmStations = new ArrayList<Station>();
        
        public Radio(){
            generateStations("am");
            generateStations("fm");
        }
        
        public List<Station> getStations(String amFm){
            return amFm.equals("am") ? amStations : fmStations;
        }
        
        public void generateStations(String amFm){
            String filePath = System.getProperty("user.dir");
            
            if (amFm.equals("am")){
                filePath += "/src/com/csci360/alarmclock/soundsAM";
                System.out.println(filePath);
                File music = new File(filePath);

                File[] songs = music.listFiles();

                for (File song : songs) {
                    System.out.println(song.getPath());
                    amStations.add(new Station(song));
                }
            } else {
                filePath += "/src/com/csci360/alarmclock/soundsFM";
                File music = new File(filePath);
                File[] songs = music.listFiles();
                for (File song : songs) {
                    fmStations.add(new Station(song));
                }
            }
        }
	
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

            try {
                for (Station stat : r.getStations("am")){
                    stat.play();
//                    stat.stop();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
//		Radio r = new Radio();
//		r.setFrequency("AM", 12);
//		r.toggleRadioOnOff();
//		r.setVolume(44);
//		System.out.println(r.getCurrentFrequency());
//		r.play();
	}
}

