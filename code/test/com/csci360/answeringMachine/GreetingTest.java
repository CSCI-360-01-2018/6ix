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
public class GreetingTest {
    
     @Test
     public void getGreeting() {
         Greeting g = new Greeting();
         Recording r = new Recording();
         g.setGreeting(r);
         
         assertEquals(r, g.getGreeting());
     }

     @Test
     public void setGreeting() {
         Greeting g = new Greeting();
         Recording r = new Recording();
         g.setGreeting(r);
         
         assertEquals(r, g.getGreeting());
     }
     
}
