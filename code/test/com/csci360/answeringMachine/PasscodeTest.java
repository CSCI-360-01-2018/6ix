/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.answeringMachine;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MeganLandau
 */
public class PasscodeTest {
    
     @Test
     public void setPasscode() {
         Passcode p = new Passcode();
         p.setPasscode("1212");
         assertEquals("1212", p.getPasscode());
     }
     
     @Test
     public void getPasscode() {
         Passcode p = new Passcode();
         p.setPasscode("1212");
         
         assertEquals("1212", p.getPasscode());
     }
     
     @Test
     public void validatePasscode() {
         Passcode p = new Passcode();
         assertEquals(false, p.validatePasscode("99999"));
         assertEquals(false, p.validatePasscode(""));
         assertEquals(false, p.validatePasscode("badpassword"));
         assertEquals(false, p.validatePasscode("0"));
         assertEquals(false, p.validatePasscode("dd"));
         
         assertEquals(true, p.validatePasscode("0000"));
         assertEquals(true, p.validatePasscode("1212"));
     }   
     
}
