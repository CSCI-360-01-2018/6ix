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
    public double volume = 0.5; // range 0-1 for media players
    public boolean radioOn = false;
    List<Station> amStations = new ArrayList<Station>();
    List<Station> fmStations = new ArrayList<Station>();

    public static final int numRadioStations = 5;
    public int fmMin = 88;
    public int fmMax = 108;
    public int amMin = 54;
    public int amMax = 160;

    public Radio() {
        generateStations("am");
        generateStations("fm");
    }

    public int convertToPlayableStation() {

        int stationNum = 0;
        if (currentMode.equalsIgnoreCase("am")) {
            stationNum = (int) ((amFrequency - amMin) / ((amMax - amMin) / (numRadioStations - 1)));
        } else if (currentMode.equalsIgnoreCase("fm")) {
            stationNum = (int) ((fmFrequency - fmMin) / ((fmMax - fmMin) / (numRadioStations - 1)));
        }
        return stationNum;
    }

    public List<Station> getStations(String amFm) {
        return amFm.equalsIgnoreCase("am") ? amStations : fmStations;
    }

    public void generateStations(String amFm) {
        String filePath = System.getProperty("user.dir");

        if (amFm.equalsIgnoreCase("am")) {
            filePath += "/src/com/csci360/alarmclock/soundsAM";
            File music = new File(filePath);

            File[] songs = music.listFiles();

            for (File song : songs) {
                if (!song.toString().contains(".DS_Store")) {
                    amStations.add(new Station(song));
                }
            }
        } else {
            filePath += "/src/com/csci360/alarmclock/soundsFM";
            File music = new File(filePath);
            File[] songs = music.listFiles();
            for (File song : songs) {
                if (!song.toString().contains(".DS_Store")) {
                    fmStations.add(new Station(song));
                }
            }
        }
    }

    public void setAmFmMode(String mode) {
        currentMode = mode;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setFrequency(String mode, double frequency) {
        if (mode.equalsIgnoreCase("AM")) {
            amFrequency = frequency;
        } else if (mode.equalsIgnoreCase("FM")) {
            fmFrequency = frequency;
        }
    }

    public String getCurrentMode() {
        return currentMode;
    }

    public double getCurrentFrequency() {
        return (currentMode.equalsIgnoreCase("AM")) ? amFrequency : fmFrequency;
    }

    public double getVolume() {
        return volume;
    }

    public void toggleRadioOnOff() throws IOException {
        radioOn = !radioOn;
    }
}
