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
public class AlarmTest {

    @Test
    public void createAlarm() {
        Alarm a = new Alarm(1);
        assertEquals(a.alarmHour, 0);
        assertEquals(a.alarmMinute, 0);
        assertEquals(a.alarmIsOn, false);
        assertEquals(a.name, "Alarm 1");
        assertNotEquals(a.name, "Alarm 2");
        assertEquals(a.alarmIsSet, false);
        assertEquals(a.snoozeOn, false);    
    }
    
    @Test
    public void setAlarmTime() {
        Alarm a = new Alarm(1);
        
        a.setAlarmHour(12);
        a.setAlarmMinute(44);
        
        assertEquals(a.alarmHour, 12);
        assertEquals(a.alarmMinute, 44);
        
        assertEquals(a.getAlarmHour(), 12);
        assertEquals(a.getAlarmMinute(), 44);   
    }
    
    @Test
    public void testAlarmSounding() {
        Alarm a = new Alarm(1);
        a.setAlarmHour(12);
        a.setAlarmMinute(2);
        
        a.toggleAlarmIsSet(true);
        a.toggleAlarmIsOn(true);
        
        a.soundAlarm();
        assertEquals(true, a.alarmIsOn);
        
        a.turnAlarmSoundOff();
        assertEquals(false, a.alarmIsOn);
    }
    
    @Test
    public void testAlarmIsSet() {
        Alarm a = new Alarm(1);
        a.setAlarmHour(12);
        a.setAlarmMinute(2);
        
        a.toggleAlarmIsSet(true);
        assertEquals(true, a.alarmIsSet);
        
    }
    
    @Test
    public void snoozeAlarm() {
        Alarm a = new Alarm(1);
        a.setAlarmHour(12);
        a.setAlarmMinute(2);
        
        a.toggleAlarmIsSet(true);
        a.toggleAlarmIsOn(true);
        
        a.soundAlarm();
        assertEquals(true, a.alarmIsOn);
        
        a.snoozeAlarm();
        assertEquals(true, a.isSnoozing());
        assertEquals(true, a.snoozeOn);

        assertEquals(false, a.alarmIsOn);
    }
}
