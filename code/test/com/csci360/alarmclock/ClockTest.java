/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

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
public class ClockTest {
    
    @Test
    public void initializeClock() {
        Clock c = new Clock();

        assertEquals("Alarm 1", c.getAlarm(1).name);
        assertEquals("Alarm 2", c.getAlarm(2).name); 
        assertEquals("00:00:01", c.getFullTime()); 
        assertEquals("00:00", c.getTime()); 
    }

    @Test
    public void setAlarmTime() {
        Clock c = new Clock();
        
        c.setAlarm(1, 1, 12);
        c.setAlarm(2, 14, 44);
        
        assertEquals("01:12 AM", c.getFormattedAlarmTime(1));
        assertEquals("02:44 PM", c.getFormattedAlarmTime(2));
        assertEquals("01:12", c.getAlarmTime(1));
        assertEquals("02:44", c.getAlarmTime(2)); 
    }
    
    @Test
    public void setMilitaryAndStandardFormat() {
        Clock c = new Clock();
        
        c.setTime(15, 6);
        
        assertEquals("03:06 PM", c.getFormattedTime());
        assertEquals("03:06", c.getTime()); 
        
        c.changeStandardMilitaryFormat("military");
        
        assertEquals("15:06", c.getFormattedTime());
        assertEquals("15:06", c.getTime()); 
    }
    
}
