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
public class MessageTest {
    
     @Test
     public void constructMessage() {
         Message m = new Message(1, "Dave");

         assertEquals("Dave", m.getSenderName());
         assertEquals(1, m.getMessageId());
     }
     
     @Test
     public void setNewMessageId() {
         Message m = new Message(3, "Mimi");
         m.setMessageId(12);
         assertEquals(12, m.getMessageId());
         assertNotEquals(3, m.getMessageId());
     }
}
