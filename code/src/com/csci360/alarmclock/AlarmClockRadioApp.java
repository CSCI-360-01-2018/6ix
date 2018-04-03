/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
