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
	public double amFrequency = 0; // 540-1600 kHZ for 106 possible bands
	public double fmFrequency = 0; // 88.1-108.1 MHz for 100 possible bands
	public String currentMode = "AM";	
	public double volume = 15; // range 0-100
	public boolean radioOn = false;
        List<Station> amStations = new ArrayList<Station>();
        List<Station> fmStations = new ArrayList<Station>();
        
        public static final int numRadioStations = 5;
        public int fmMin = 88;
        public int fmMax = 108;
        public int amMin = 54;
        public int amMax = 160;
        // (int) (freq - min) / ((max-min) / numRadioStations)
        
        public Radio(){
            generateStations("am");
            generateStations("fm");
        }
        
        public int convertToPlayableStation(){
            
            
            int stationNum = 0;
            if (currentMode.equalsIgnoreCase("am")){
                System.out.println("am frequency: " + amFrequency);
                stationNum = (int) ((amFrequency - amMin) / ((amMax - amMin) / (numRadioStations - 1)));
            } else {
                System.out.println("fm frequency: " + fmFrequency);
                stationNum = (int) ((fmFrequency - fmMin) / ((fmMax - fmMin) / (numRadioStations - 1)));
            }
            return stationNum;
        }
        
        public List<Station> getStations(String amFm){
            return amFm.equalsIgnoreCase("am") ? amStations : fmStations;
        }
        
        public void generateStations(String amFm){
            String filePath = System.getProperty("user.dir");
            
            if (amFm.equalsIgnoreCase("am")){
                filePath += "/src/com/csci360/alarmclock/soundsAM";
                System.out.println(filePath);
                File music = new File(filePath);

                File[] songs = music.listFiles();

                for (File song : songs) {
                    System.out.println(song.getPath());
                    if (!song.toString().contains(".DS_Store")){
                        amStations.add(new Station(song));
                    }
                }
            } else {
                filePath += "/src/com/csci360/alarmclock/soundsFM";
                File music = new File(filePath);
                File[] songs = music.listFiles();
                for (File song : songs) {
                    System.out.println(song.getPath());
                    if (!song.toString().contains(".DS_Store")){
                        fmStations.add(new Station(song));
                    }

                }
            }
        }
	
	public void setAmFmMode(String mode){
            currentMode = mode;
	}
	
	public void setVolume(double volume){
            this.volume = volume;
	}
	
	public void setFrequency(String mode, double frequency){
            if (mode.equalsIgnoreCase("AM")){
                    amFrequency = frequency;
                    System.out.println("am frequency = " + this.amFrequency);
            } else {
                    fmFrequency = frequency;
                    System.out.println("fm frequency = " + this.fmFrequency);
            }
	}
	
	public String getCurrentMode(){
		return currentMode;
	}
	
	public double getCurrentFrequency(){
		return (currentMode.equalsIgnoreCase("AM")) ? amFrequency : fmFrequency;
	}

	public double getVolume(){
		return volume;
	}
	
	public void toggleRadioOnOff() throws IOException{
            radioOn = !radioOn;
            if (radioOn){
                play();
            } else {
                System.out.println("Radio Off. Stopping radio stations for " + currentMode);
                for (Station stat : this.getStations(currentMode)){
                    stat.stop();
                }
            }
	}
	
	public void play() throws IOException {
            if (radioOn){
                System.out.println("radio is playing");
                // get frequency? get mode? play frequency and mode?
//                System.out.println("length of am stations: " + amStations.size());
//                System.out.println("length of fm stations: " + fmStations.size());
//                int stationIndex = convertToPlayableStation();
//                System.out.println("station index: " + Integer.toString(stationIndex));
////                for (Station stat : this.getStations(currentMode)){
////                    stat.stop();
////                }
//                Station playStation = this.getStations(currentMode).get(stationIndex);
//                playStation.play();
                
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

