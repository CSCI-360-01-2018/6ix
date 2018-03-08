/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.answeringMachine;

import java.util.ArrayList;
import java.util.List;
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
public class AnsweringMachineTest {

     @Test
     public void constructAnsweringMachine() {
         AnsweringMachine am = new AnsweringMachine("Megan");

         assertEquals("Megan", am.getOwnerName());
         assertEquals(true, am.isSetUp);
     }
     
     @Test
     public void authenticatePasscode() {
         AnsweringMachine am = new AnsweringMachine("Megan");
         
         am.setPasscode("0000");
         assertEquals(false, am.authenticatePasscode("0001"));
         assertEquals(true, am.authenticatePasscode("0000"));
         assertEquals(false, am.authenticatePasscode("0001"));
     }
     
     @Test
     public void validatePasscode() {
         AnsweringMachine am = new AnsweringMachine("Megan");
         
         assertEquals(false, am.passcode.validatePasscode("99999"));
         assertEquals(false, am.passcode.validatePasscode(""));
         assertEquals(false, am.passcode.validatePasscode("badpassword"));
         assertEquals(false, am.passcode.validatePasscode("0"));
         assertEquals(false, am.passcode.validatePasscode("dd"));
         
         assertEquals(true, am.passcode.validatePasscode("0000"));
         assertEquals(true, am.passcode.validatePasscode("1212"));
     }
     
//     @Test
//     public void constructAnsweringMachine() {
//         AnsweringMachine am = new AnsweringMachine("Megan");
//
//         assertEquals("Megan", am.getOwnerName());
//         assertEquals(true, am.isSetUp);
//     }
}
