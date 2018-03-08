/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.answeringMachine;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
	
	public int messageId;
	public Recording message;
	public String timestamp;
	public String sender;
	
	public Message(int messageId, String sender){
		this.messageId = messageId;
		String messageFileName = "message_" + Integer.toString(messageId);
		message = new Recording(messageFileName);
		this.sender = sender;
                
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		timestamp = dateFormat.format(date); //2016/11/16 12:08:43
		
		//String date = timestamp.split(" ")
		
	}
        
        public String getSenderName(){
            return this.sender;
        }
	
	public int getMessageId(){
		return this.messageId;
	}
	
	public void setMessageId(int id){
		this.messageId = id;
	}
	
	public Recording getMessage(){
		return this.message;
	}
	
	public void playMessage(){
		message.play();
		System.out.println("playing message " + Integer.toString(messageId));
	}

	public static void main(String[] args) {
//		Recording r = new Recording(1);
		Message m = new Message(1, "joe");
		m.playMessage();
		System.out.println(m.getMessageId());
	}

}
