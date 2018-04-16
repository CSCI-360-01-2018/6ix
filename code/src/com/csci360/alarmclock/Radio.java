/*
 * CSCI 360 Semester Project
 * Team 6ix - Dual Alarm Clock Radio
 * Professor: Dr. Bowring
 */
package com.csci360.alarmclock;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Radio class is responsible for playing AM and FM stations for the system. User can turn the 
 * radio on, change the AM or FM modes, change the frequency to hear different stations play, and 
 * adjust the volume of the system.
 * 
 * On initialization, the Radio creates stations for both AM and FM modes using the Station class.
 * 
 */
public class Radio {

    protected double amFrequency = 0; // 540-1600 kHZ for 106 possible bands
    protected double fmFrequency = 0; // 88.1-108.1 MHz for 100 possible bands
    protected String currentMode = "AM";
    protected double volume = 0.5; // range 0-1 for media players
    protected boolean radioOn = false;
    protected List<Station> amStations = new ArrayList<>();
    protected List<Station> fmStations = new ArrayList<>();

    private static final int numRadioStations = 5;
    private int fmMin = 88;
    private int fmMax = 108;
    private int amMin = 54;
    private int amMax = 160;

    public Radio() {
        generateStations("am");
        generateStations("fm");
    }

    protected int convertToPlayableStation() {

        int stationNum = 0;
        if (currentMode.equalsIgnoreCase("am")) {
            stationNum = (int) ((amFrequency - amMin) / ((amMax - amMin) / (numRadioStations - 1)));
        } else if (currentMode.equalsIgnoreCase("fm")) {
            stationNum = (int) ((fmFrequency - fmMin) / ((fmMax - fmMin) / (numRadioStations - 1)));
        }
        return stationNum;
    }

    protected List<Station> getStations(String amFm) {
        return amFm.equalsIgnoreCase("am") ? amStations : fmStations;
    }

    protected void generateStations(String amFm) {
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

    protected void setAmFmMode(String mode) {
        currentMode = mode;
    }

    protected void setVolume(double volume) {
        this.volume = volume;
    }

    protected void setFrequency(String mode, double frequency) {
        if (mode.equalsIgnoreCase("AM")) {
            amFrequency = frequency;
        } else if (mode.equalsIgnoreCase("FM")) {
            fmFrequency = frequency;
        }
    }

    protected String getCurrentMode() {
        return currentMode;
    }

    protected double getCurrentFrequency() {
        return (currentMode.equalsIgnoreCase("AM")) ? amFrequency : fmFrequency;
    }

    protected double getVolume() {
        return volume;
    }

    protected void toggleRadioOnOff() throws IOException {
        radioOn = !radioOn;
    }
}
