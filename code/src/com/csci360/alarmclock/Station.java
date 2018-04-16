/*
 * CSCI 360 Semester Project
 * Team 6ix - Dual Alarm Clock Radio
 * Professor: Dr. Bowring
 */
package com.csci360.alarmclock;

import java.io.File;

/**
 * Station objects represent stations that can be played by the radio. For this system, stations are
 * sound files stored within the soundsAM or soundsFM folders.
 */
public class Station {
    protected String path;
    protected String name;

    protected Station(File audioFile){
        path = audioFile.getPath();
        name = audioFile.getName();
    }
    
    protected String getFilePath(){
        return this.path;
    }
}
