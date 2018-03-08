/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.answeringMachine;

import java.util.Scanner;

public class Passcode {
	
	public String passcode;

	public String getPasscode(){
		return this.passcode;
	}
	
	public void setPasscode(String newPasscode){
		this.passcode = newPasscode;
	}
	
	public boolean validatePasscode(String passcode){
		if (passcode.matches("[0-9]{4}") && passcode.length() == 4) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Passcode p = new Passcode();
		String tryPasscode = scan.nextLine();
		
		while (!p.validatePasscode(tryPasscode)){
			tryPasscode = scan.nextLine();
		}
		
		p.setPasscode(tryPasscode);
		System.out.println(p.getPasscode());

	}

}

