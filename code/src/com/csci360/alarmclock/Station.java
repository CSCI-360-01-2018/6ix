/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.io.File;

public class Station {
    public String path;
    public String name;

    public Station(File audioFile){
        path = audioFile.getPath();
        name = audioFile.getName();
    }
    
    public String getFilePath(){
        return this.path;
    }
}
