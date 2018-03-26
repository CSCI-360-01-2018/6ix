/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Station {
    int BUFFER_SIZE = 4096;
    private SourceDataLine dataLine;
    private AudioFormat format;
    private AudioInputStream inputStream;
    public String path;
    public String name;

    public Station(File audioFile){
        path = audioFile.getPath();
        name = audioFile.getName();
        try {
            this.inputStream = AudioSystem.getAudioInputStream(audioFile);
            this.format = inputStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            this.dataLine = (SourceDataLine) AudioSystem.getLine(info);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    public String getFilePath(){
        return this.path;
    }

    public void play() throws IOException {
        try {
            this.dataLine.open(this.format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        this.dataLine.start();
        byte[] byteBuffer = new byte[BUFFER_SIZE];
        int bytesRead;

        while((bytesRead = this.inputStream.read(byteBuffer)) != -1){
            this.dataLine.write(byteBuffer, 0, bytesRead);
        }
    }

    public void stop() throws IOException {
        this.dataLine.drain();
        this.dataLine.stop();
//        this.dataLine.close();
//        this.inputStream.close();
    }

}
