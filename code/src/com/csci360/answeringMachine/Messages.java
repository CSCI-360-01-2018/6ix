/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.answeringMachine;

import java.util.ArrayList;
import java.util.List;

public class Messages {
	
	private String inboxName;
	private List<Message> messages = new ArrayList<Message>();
	public static int CAPACITY = 20;
	public int lastMessageIdEntered;
	
	public Messages(String inboxName){
		this.inboxName = inboxName;
	}
	
	public String getInboxName(){
		return this.inboxName;
	}
	
        public List<Message> getMessages(){
            return this.messages;
        }
        
	public int getNumMessages(){
		return messages.size();
	}
	
	public void playAllMessages(){
		for (Message m : messages){
			m.playMessage();
		}
	}
	
	public void playMessage(int messageId){
		for (int i = 0; i < messages.size(); i++){
			if (messages.get(i).getMessageId() == messageId){
				messages.get(i).playMessage();
			}
		}
	}
	
	public Message removeMessage(int messageId){
		for (int i = 0; i < messages.size(); i++){
			if (messages.get(i).getMessageId() == messageId){
				return messages.remove(i);
			}
		}
		return null;
	}
	
	public Message getMessage(int messageId){
		for (int i = 0; i < messages.size(); i++){
			if (messages.get(i).getMessageId() == messageId){
				return messages.get(i);
			}
		}
		return null;
	}
	
	public boolean addMessage(Message message){
		if (!isFull()){
			lastMessageIdEntered++;
			message.setMessageId(lastMessageIdEntered);
			messages.add(message);
			return true;
		} else {
			System.out.println(inboxName + " messages is full.");
			return false;
		}
	}
	
	public boolean isFull(){
		if (this.getNumMessages() < CAPACITY){
			return false;
		} else {
			return true;
		}
	}
	
	public void removeAllMessages(){
		messages.clear();
	}
	
	public int getLastIdEntered(){
		return this.lastMessageIdEntered;
	}

	public static void main(String[] args) {
		Messages m = new Messages("Saved");
		
		Messages x = new Messages("New");
		
		Message a = new Message(m.getLastIdEntered(), "bill");
		
		m.addMessage(a);
//		x.addMessage(new Recording());
//		x.playAllMessages();
		Message c = m.removeMessage(m.getLastIdEntered());
		
//		for (int i = 0; i < 20; i++){
//			Recording f = new Recording();
//			m.addMessage(f);
//			
//		}
//		Recording a = new Recording();
		
		while (!m.isFull()){
//			Recording f = new Recording();
			m.addMessage(new Message(m.getLastIdEntered(), "joe"));
		}
		
		//m.playAllMessages();
		
		m.removeMessage(4);
		m.removeMessage(14);
		m.removeMessage(1);
		m.removeMessage(18);
		m.removeMessage(21);
		System.out.println(m.getNumMessages());
		m.playAllMessages();
		while (!m.isFull()){
//			Recording f = new Recording();
//			m.addMessage(f);
		}
		
		m.removeAllMessages();
		
		System.out.println(m.getNumMessages());
		m.playAllMessages();
		
		boolean wasAdded = m.addMessage(c);
		System.out.println(wasAdded);
		
		
		
		System.out.println(m.getNumMessages());
		m.playAllMessages();
	}

}

