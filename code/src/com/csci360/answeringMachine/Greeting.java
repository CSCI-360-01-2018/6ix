/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.answeringMachine;


public class Greeting {
	
	private Recording greeting;
	
	public Recording getGreeting(){
		return greeting;
	}
	
	public void playGreeting(){
		greeting.play();
	}
	
	public void setGreeting(Recording recording){
		greeting = recording;
	}

	public static void main(String[] args) {
		Greeting g = new Greeting();
		g.setGreeting(new Recording());
		g.playGreeting();
	}

}

