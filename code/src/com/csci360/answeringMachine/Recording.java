/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.answeringMachine;

import javax.sound.sampled.*;
import java.io.*;

public class Recording {
	
    private final int BUFFER_SIZE = 128000;
    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    String wavFilePath;
	
    public Recording(String filename){
    	wavFilePath = System.getProperty("user.dir") + "/src/com/csci360/answeringMachine/recordings/" + filename + ".wav";
    	Recorder recorder = new Recorder();
    	recorder.createNewRecording(wavFilePath);
    }
    
    public Recording(){
    	wavFilePath = System.getProperty("user.dir") + "/src/com/csci360/answeringMachine/recordings/" + "greeting" + ".wav";
    	Recorder recorder = new Recorder();
    	recorder.createNewRecording(wavFilePath);
    }
    
    public static void main(String[] args) {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        
        String wavFilePath = System.getProperty("user.dir") + "/src/com/csci360/answeringMachine/recordings/" + "newMessage.wav";
        
    	Recording r = new Recording("message_1");
    	r.play();
    	
    }
    

    
//    public void setRecording(){
//    	Recorder recorder = new Recorder();
//    	recorder.createNewRecording(wavFilePath);
//    }
    
//	public void play(){
//		System.out.println("playing recording");
//	}
	
	
	////https://stackoverflow.com/questions/2416935/how-to-play-wav-files-with-java
    public void play(){ 

        String strFilename = wavFilePath;

        try {
            soundFile = new File(strFilename);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            audioStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        sourceLine.start();

        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }

        sourceLine.drain();
        sourceLine.close();
    }

}

