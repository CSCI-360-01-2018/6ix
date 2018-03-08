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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author MeganLandau
 */
public class AlarmClock extends Application {
    
    AlarmClockRadio arc;
    Font fontAwesome;
    
    @Override
    public void start(Stage primaryStage) {
        InputStream fafont = AlarmClock.class.getResourceAsStream("fontawesome-webfont.ttf");
        fontAwesome = Font.loadFont(fafont, 52);
        
        Button btn = new Button("\uf017");
        btn.setFont(fontAwesome);
        btn.setStyle("-fx-text-fill: #63665b;-fx-background-color:#45463f;");
//        btn.setStyle("-fx-background-color:#45463f;");
        
//        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 500, 500);
        
        primaryStage.setTitle("Hello World!");
        
        scene.getStylesheets().add(AlarmClock.class.getResource("alarmClock.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
