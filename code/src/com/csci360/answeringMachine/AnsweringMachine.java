/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.answeringMachine;

import java.util.Scanner;

public class AnsweringMachine {
	
	public Passcode passcode = new Passcode();
	public Greeting greeting = new Greeting();
	public Messages newMessages = new Messages("New");
	public Messages savedMessages = new Messages("Saved");
        public boolean isSetUp = false;
        public String ownerName;
        public boolean isInitialized = false;
	
	public AnsweringMachine(String owner){
		System.out.println("Initializing Answering Machine...");
		//setPasscode();
		//recordNewGreeting();
                isSetUp = true;
                this.ownerName = owner;
                System.out.println(owner);
	}
        
        public boolean isInitialized(){
            return this.isInitialized;
        }
        
        public void setInitialized(boolean set){
            isInitialized = (set == true) ? true : false;
        }
        
        public String getOwnerName(){
            return this.ownerName;
        }
	
	public void recordNewGreeting(){
            System.out.println("Please record your greeting...");

            Recording newGreeting = new Recording();

            greeting.setGreeting(newGreeting);
            System.out.println("Your greeting has been recorded!");
	}
	
	public void listenToGreeting(){
            greeting.playGreeting();
	}
	
	public boolean authenticatePasscode(String enteredPasscode){
            if (enteredPasscode.equals(this.passcode.getPasscode())){
                    return true;
            } else {
                    return false;
            }
	}
	
	public void enterPasscode(){
            System.out.println("Please enter your passcode.");

            Scanner scan = new Scanner(System.in);
            Passcode p = new Passcode();

            String tryPasscode = scan.nextLine();

            int tries = 3;
            while (!authenticatePasscode(tryPasscode)){
                    System.out.println("Invalid passcode. You have " + Integer.toString(tries) + " more tries.");
                    tries--;
                    tryPasscode = scan.nextLine();
            }

            System.out.println("Passcode authenticated.");
	}
        
        public void setPasscode(String inpPasscode){
            passcode.setPasscode(inpPasscode);
        }
	
//	public void setPasscode(String inpPasscode){
//		System.out.println("Please set your passcode. Passcodes must be exactly 4 digits long.");
//		
////		Scanner scan = new Scanner(System.in);
//
////		String tryPasscode = scan.nextLine();
//                String tryPasscode = inpPasscode;
//		
//		while (!passcode.validatePasscode(tryPasscode)){
//			System.out.println("Invalid passcode. Passcodes must be exactly 4 digits long.");
//			tryPasscode = scan.nextLine();
//		}
//		
//		System.out.println("Passcode set!");
//		
//		passcode.setPasscode(tryPasscode);
//		
//		System.out.println(passcode.getPasscode());
//	}
	
	public void playMessage(String inboxName, int messageId){
            if (inboxName.equals("New")){
                    newMessages.playMessage(messageId);
            } else {
                    savedMessages.playMessage(messageId);
            }
	}
	
	public void deleteMessage(String inboxName, int messageId){
            if (inboxName.equals("New")){
                    newMessages.removeMessage(messageId);
            } else {
                    savedMessages.removeMessage(messageId);
            }
	}
	
	public void moveNewMessageToSavedMessages(int messageId){
            if (savedMessages.isFull()){
                    System.out.println("Saved Messages is full. Delete a message in Saved Messages in order to save this message.");
            } else {
                    Message message = newMessages.removeMessage(messageId);
                    // Don't need to worry about messageId because newMessages changes it.
                    savedMessages.addMessage(message); 
            }
	}
	
	public void deleteAllMessages(String inboxName){
            Messages inbox = (inboxName.equals("New")) ? newMessages : savedMessages;
		inbox.removeAllMessages();
	}
	
	public boolean haveNewMessages(){
            return (newMessages.getNumMessages() > 0) ? true : false;
	}
	
	
	// For leaving messages
	public void leaveMessage(String senderName){
            Message message = new Message(this.newMessages.getLastIdEntered(), senderName);
            if (newMessages.isFull()){
                    System.out.println("This inbox is full. You cannot leave a message at this time.");
            } else {
                    newMessages.addMessage(message); 
            }
	}
	
	// this is basically the user
	public static void main(String[] args) {
		AnsweringMachine a = new AnsweringMachine("Megan");
		
		System.out.println("enter passcode");
		a.enterPasscode();
		//Message m = new Message(a.newMessages.getLastIdEntered());
		
		//System.out.println("creating new message");
		//a.newMessages.addMessage(m);
		//System.out.println("a has " + a.newMessages.getNumMessages() + " messages");
		
//		System.out.println("this is your greeting");
//		a.listenToGreeting();
//		a.newMessages.addMessage(a.newMessages.getLastIdEntered());
//		a.newMessages.addMessage(a.newMessages.getLastIdEntered());
		
//		for (int i = 0; i < 20; i++) {
//			a.savedMessages.addMessage(new Recording());
//		}
//		System.out.println("playing new message number 1");
//		a.playMessage("New", 1);
////		a.playMessage("Saved", 14);
//		System.out.println("moving message 1 to saved messages");
//		a.moveNewMessageToSavedMessages(1);
//		
//		System.out.println("num of saved messages = " + a.savedMessages.getNumMessages());
//		System.out.println("playing all saved messages");
//		a.newMessages.playAllMessages();
//		System.out.println("deleting all saved messages");
//		a.deleteAllMessages("Saved");
////		a.moveNewMessageToSavedMessages(1);
////		System.out.println(a.savedMessages.getNumMessages());
	}

}

