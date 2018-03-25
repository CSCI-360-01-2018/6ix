/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.io.InputStream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author MeganLandau
 */
public class AlarmClockRadioApp extends Application {
    
    Font fontAwesome;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/csci360/alarmclock/clock.fxml"));

//        InputStream fafont = AlarmClockRadioApp.class.getResourceAsStream("/com/csci360/alarmclock/fonts/fontawesome-webfont.ttf");
//        fontAwesome = Font.loadFont(fafont, 14);

        stage.setTitle("Alarm Clock Radio");
        Scene scene = new Scene(root);
        
        scene.getStylesheets().add(getClass().getResource("/com/csci360/alarmclock/stylesheets/alarmClock.css").toExternalForm());
        
        stage.setScene(scene);
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
