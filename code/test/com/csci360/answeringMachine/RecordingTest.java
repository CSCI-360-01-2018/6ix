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
public class RecordingTest {
    
     @Test
     public void recordingsNotTheSame() {
         Recording r = new Recording("0");
         Recording x = new Recording("1");
         
         assertNotEquals(r, x);
         assertNotEquals(r.wavFilePath, x.wavFilePath);
     }
     
     @Test
     public void greetingRecordingsOverwrite() {
         Recording r = new Recording();
         Recording x = new Recording();

         assertEquals(r.wavFilePath, x.wavFilePath);
         
     }
     
}
