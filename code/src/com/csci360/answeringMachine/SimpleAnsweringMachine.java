/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.answeringMachine;

import javafx.application.Application;
import javafx.scene.control.ToggleButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.InputStream;
import javafx.animation.Timeline;
import javafx.scene.text.Font;


/**
 *
 * @author MeganLandau
 */
public class SimpleAnsweringMachine extends Application {   
    public AnsweringMachine am;
    public Scene initializeScene, passcodeScene, greetingScene, mainMenuScene, settingsScene, 
            newMessagesScene, ownerCallerScene, loginScene, savedMessagesScene, leaveMessageScene;
    public int tries = 3;
    Font fontAwesome;
    Font openSans;
    String styleSheetPath = "/com/csci360/answeringMachine/stylesheet/SimpleAnsweringMachine.css";
    StackPane root = new StackPane();
    GifAnimation anim;
    
        @Override
    public void start(Stage primaryStage) {
        anim = new GifAnimation();
        anim.play();
        
        Pane animPane = new Pane(anim.am);
        animPane.setMinSize(388, 617); //0
        
        root.getChildren().addAll(animPane);
        root.alignmentProperty().set(Pos.CENTER);
        
        InputStream fafont = SimpleAnsweringMachine.class.getResourceAsStream("/com/csci360/answeringMachine/fonts/fontawesome-webfont.ttf");
        fontAwesome = Font.loadFont(fafont, 26);
        
        InputStream osfont = SimpleAnsweringMachine.class.getResourceAsStream("/com/csci360/answeringMachine/fonts/OpenSans-Regular.ttf");
        openSans = Font.loadFont(osfont, 26);
        
        createInitialScene(primaryStage);
//        initializeScene.getStylesheets().add(SimpleAnsweringMachine.class.getResource(styleSheetPath).toExternalForm());
        primaryStage.setTitle("Simple Answering Machine");

//        primaryStage.setScene(initializeScene);
//        primaryStage.setScene(anim.am_scene);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

//        primaryStage.setScene(initializeScene);
        primaryStage.show();
    }  
    
    public void printChildren(){
       System.out.println(root.getChildren().iterator());
        
    
    }
    
    public void createInitialScene(Stage primaryStage){
        
        Label label1= new Label("Welcome");
//        label1.setId("heading");
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 20, 10, 20));
        
        bp.setTop(label1);
        bp.setAlignment(label1, Pos.CENTER); 
        
        Label label2= new Label("Please set up your machine.");

        Label label3= new Label("Enter your name:");

        TextField tf= new TextField();
        tf.setMaxWidth(200);
        tf.setAlignment(Pos.CENTER);
        
        Button button1= new Button("Initialize");
//        button1.setId("bottom-button");
        button1.setOnAction(new EventHandler<ActionEvent>() {
   
            @Override
            public void handle(ActionEvent event) {
                
                String ownerName = tf.getText();
                am = new AnsweringMachine(ownerName);

                createSetPasscodeScene(primaryStage);
//                passcodeScene.getStylesheets().add(SimpleAnsweringMachine.class.getResource(styleSheetPath).toExternalForm());
                
                root.getChildren().get(1).setVisible(false);

//                primaryStage.setScene(passcodeScene);
            }
        });
        
        VBox layout1 = new VBox(20); 
        layout1.setAlignment(Pos.CENTER);
        layout1.setSpacing(25);
        layout1.getChildren().addAll(label2, label3, tf);
//        layout1.setId("center");
        
        bp.setBottom(button1);
        bp.setAlignment(button1, Pos.CENTER);
        
        bp.setCenter(layout1);
        bp.setAlignment(layout1, Pos.CENTER);
//        initializeScene = new Scene(bp, 400, 550);
        
        Pane initPane = new Pane(bp); //1
        root.getChildren().add(initPane);
    }
    
    public void createSetPasscodeScene(Stage primaryStage){
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 20, 10, 20));
        
        Label label4;
        if (!am.isInitialized()){
            label4= new Label("Initial Setup: Passcode");
        } else {
            label4= new Label("Set Passcode");
        }
        
        bp.setTop(label4);
        bp.setAlignment(label4, Pos.CENTER);
        
        Label label3= new Label("Enter your 4-digit passcode:");

        TextField tf= new TextField();
        tf.setMaxWidth(200);
        tf.setAlignment(Pos.CENTER);
        
        Label warning= new Label();
        warning.setMaxWidth(200);
        warning.setAlignment(Pos.CENTER);
        
        Button button2= new Button("Submit");
        button2.setOnAction(new EventHandler<ActionEvent>() {
   
            @Override
            public void handle(ActionEvent event) {
                
//                am.setPasscode(tf.getText());
                String tryPasscode = tf.getText();
                
                if (!am.passcode.validatePasscode(tryPasscode)){
                    warning.setText("Invalid passcode. Passcodes must be exactly 4 digits long.");
                    System.out.println(tryPasscode);
//                    tf.setText("");
//                    tryPasscode = tf.getText();
                } else {
            
                    am.setPasscode(tf.getText());
                    System.out.println("password set");
                    
                    System.out.println(am.isInitialized);
                    
                    if (!am.isInitialized){
                        createNewGreetingScene(primaryStage);
//                        greetingScene.getStylesheets().add(SimpleAnsweringMachine.class.getResource(styleSheetPath).toExternalForm());
                            root.getChildren().get(2).setVisible(false);
                            root.getChildren().get(3).setVisible(true);
//                            root.getChildren().get(4).setVisible(true);
//                        primaryStage.setScene(greetingScene);
                    } else {
                        root.getChildren().get(2).setVisible(false);
                        root.getChildren().get(6).setVisible(true);
//                        primaryStage.setScene(mainMenuScene);
                    }
                }
                

            }
        }); 
        
        VBox layout2= new VBox(20);
        layout2.setAlignment(Pos.CENTER);
        layout2.setSpacing(25);
        layout2.getChildren().addAll(label3, tf, button2, warning);
//        layout2.setId("center");
        
        bp.setCenter(layout2);
        bp.setAlignment(layout2, Pos.CENTER);
        
        if (am.isInitialized()){
            Button button5= new Button("Back");
            button5.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {      
//                    primaryStage.setScene(mainMenuScene);  
                    root.getChildren().get(2).setVisible(false);
                    root.getChildren().get(6).setVisible(true);
                }
            }); 
            bp.setBottom(button5);
            bp.setAlignment(button5, Pos.CENTER);
        }
        

        if (!am.isInitialized()){
            Pane passcodePane = new Pane(bp); //2
            root.getChildren().add(passcodePane);
            
//            createNewGreetingScene(primaryStage);
            
//            root.getChildren().get(2).setVisible(false);
//            root.getChildren().get(3).setVisible(true);
        } else {
            Pane passcodePane = new Pane(bp); //2
            root.getChildren().add(2, passcodePane);
            root.getChildren().remove(3);
//            root.getChildren().add(passcodePane);
//            root.getChildren().get(2).setVisible(false);
//            root.getChildren().get(10).setVisible(true);
        }
//        passcodeScene = new Scene(bp, 400, 550);

    }
      
    public void createNewGreetingScene(Stage primaryStage){
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 20, 10, 20));
        
        Label label4;
        if (!am.isInitialized()){
            label4= new Label("Initial Setup: Greeting");
        } else {
            label4= new Label("Set Greeting");
        }
        bp.setTop(label4);
        bp.setAlignment(label4, Pos.CENTER);
        
        Label label3= new Label("Press the Start button and record your greeting.");
        
        Button button2= new Button("Start");
        button2.setOnAction(new EventHandler<ActionEvent>() {
   
            @Override
            public void handle(ActionEvent event) {
//                button2.setText("Recording");
                am.recordNewGreeting();
//                button2.setText("Finished");
                System.out.println("greeting set");
                if (!am.isInitialized){
                    
//                    root.getChildren().get(3).setVisible(false);
                    System.out.println("sys not initialized, create main menu");
                    createMainMenuScene(primaryStage);
//                    mainMenuScene.getStylesheets().add(SimpleAnsweringMachine.class.getResource(styleSheetPath).toExternalForm());
                    
                    root.getChildren().get(3).setVisible(false);
                } else {
//                primaryStage.setScene(mainMenuScene);


// need some way of telling when voice recording is done!

                    root.getChildren().get(3).setVisible(false);
                    root.getChildren().get(6).setVisible(true);
                }

            }
        }); 
        
        VBox layout2= new VBox(20);
        layout2.setAlignment(Pos.CENTER);
        layout2.setSpacing(25);
        layout2.getChildren().addAll(label3, button2);
//        layout2.setId("center");
        
        bp.setCenter(layout2);
        bp.setAlignment(layout2, Pos.CENTER);
        
        System.out.println(am.isInitialized);
        System.out.println(am.isInitialized());
        
        if (am.isInitialized){
            Button button5= new Button("Back");
            button5.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) { 
                    root.getChildren().get(3).setVisible(false);
                    root.getChildren().get(6).setVisible(true);

//                    primaryStage.setScene(mainMenuScene);            
                }
            }); 
            bp.setBottom(button5);
            bp.setAlignment(button5, Pos.CENTER);
        }
        

        
        if (!am.isInitialized()){
            Pane greetingPane = new Pane(bp); //3
            root.getChildren().add(greetingPane);
            
//            createNewGreetingScene(primaryStage);
            
//            root.getChildren().get(2).setVisible(false);
//            root.getChildren().get(3).setVisible(true);
        } else {
            Pane greetingPane = new Pane(bp); //2
            root.getChildren().add(3, greetingPane);
//            root.getChildren().remove(3);
            root.getChildren().remove(4);
//            root.getChildren().add(passcodePane);
//            root.getChildren().get(2).setVisible(false);
//            root.getChildren().get(10).setVisible(true);
        }
//        greetingScene = new Scene(bp, 400, 550);
    }
    
    public void createMainMenuScene(Stage primaryStage){
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 20, 10, 20));
        
        createNewMessagesScene(primaryStage);//4
        createSavedMessagesScene(primaryStage);//5
        createSettingsScene(primaryStage);//6
        createOwnerCallerScene(primaryStage);//9
//printChildren();
        Label label1= new Label("Main Menu");
        bp.setTop(label1);
        bp.setAlignment(label1, Pos.CENTER);
        
        Label label2= new Label("What would you like to do?");
        
        Button button1= new Button("New Messages");
        button1.setOnAction(new EventHandler<ActionEvent>() {
   
            @Override
            public void handle(ActionEvent event) {
                createNewMessagesScene(primaryStage);
                  root.getChildren().get(10).setVisible(false);
                  root.getChildren().get(4).setVisible(true);
//                createNewMessagesScene(primaryStage);
//                newMessagesScene.getStylesheets().add(SimpleAnsweringMachine.class.getResource(styleSheetPath).toExternalForm());

//                primaryStage.setScene(newMessagesScene);
            }
        });
        
        Button button2= new Button("Saved Messages");
        button2.setOnAction(new EventHandler<ActionEvent>() {
   
            @Override
            public void handle(ActionEvent event) {
                createSavedMessagesScene(primaryStage);
                  root.getChildren().get(10).setVisible(false);
                  root.getChildren().get(5).setVisible(true);
//                createSavedMessagesScene(primaryStage);
//                savedMessagesScene.getStylesheets().add(SimpleAnsweringMachine.class.getResource(styleSheetPath).toExternalForm());

//                primaryStage.setScene(savedMessagesScene);
            }
        });
        
        Button button3= new Button("Settings");
        button3.setOnAction(new EventHandler<ActionEvent>() {
   
            @Override
            public void handle(ActionEvent event) {
                  root.getChildren().get(10).setVisible(false);
                  root.getChildren().get(6).setVisible(true);
//                createSettingsScene(primaryStage);
//                settingsScene.getStylesheets().add(SimpleAnsweringMachine.class.getResource(styleSheetPath).toExternalForm());

//                primaryStage.setScene(settingsScene);
            }
        });
        
        Button button4= new Button("Leave");
        button4.setOnAction(new EventHandler<ActionEvent>() {
   
            @Override
            public void handle(ActionEvent event) {
                
//                  createOwnerCallerScene(primaryStage);//9

                  root.getChildren().get(10).setVisible(false);
                  root.getChildren().get(9).setVisible(true);
//                createOwnerCallerScene(primaryStage);
//                ownerCallerScene.getStylesheets().add(SimpleAnsweringMachine.class.getResource(styleSheetPath).toExternalForm());
//                primaryStage.setScene(ownerCallerScene);
            }
        });
        
//        createNewMessagesScene(primaryStage);//5
//        createSavedMessagesScene(primaryStage);//6
//        createSettingsScene(primaryStage);//7
//        createOwnerCallerScene(primaryStage);//8
//        
//        root.getChildren().get(5).setVisible(false);
//        root.getChildren().get(6).setVisible(false);
//        root.getChildren().get(7).setVisible(false);
//        root.getChildren().get(8).setVisible(false);
        
        VBox layout1 = new VBox(20); 
        layout1.setAlignment(Pos.CENTER);
        layout1.setSpacing(25);
        layout1.getChildren().addAll(label2, button1, button2, button3);
//        layout1.setId("center");
        
        bp.setCenter(layout1);
        bp.setAlignment(layout1, Pos.CENTER);
        
        bp.setBottom(button4);
        bp.setAlignment(button4, Pos.CENTER);
        
        Pane mainMenuPane = new Pane(bp);
        root.getChildren().add(mainMenuPane); //10
        am.setInitialized(true);
//        root.getChildren().get(4).setVisible(false);
        
//        System.out.println(root.getChildren().size());
//                root.getChildren().get(4).setVisible(false);
//                root.getChildren().get(5).setVisible(true);
//        mainMenuScene = new Scene(bp, 400, 550);
    }
    
    public void createSavedMessagesScene(Stage primaryStage){
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 20, 10, 20));
        
        Label label1= new Label("Saved Messages");
        label1.setAlignment(Pos.CENTER);
        bp.setTop(label1);
        bp.setAlignment(label1, Pos.CENTER);
        
        VBox vb = new VBox();
        
        for (Message m : am.savedMessages.getMessages()){
            HBox hb = new HBox();
            
            Label name = new Label(m.getSenderName());
            name.setAlignment(Pos.CENTER);
//            name.setId("name");
            
            hb.getChildren().add(name);
            
            Button listenButton = new Button("\uf04b");
            listenButton.setFont(fontAwesome);
            
            listenButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    am.savedMessages.playMessage(m.messageId);
                }
            });      
            hb.getChildren().add(listenButton);
            
            Button trashButton = new Button("\uf1f8");
            trashButton.setFont(fontAwesome);
            
            trashButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    am.deleteMessage("Saved", m.getMessageId());
                  root.getChildren().get(5).setVisible(false);
                  root.getChildren().get(5).setVisible(true);
//                    primaryStage.setScene(savedMessagesScene);
                    System.out.println("Message deleted");
                }
            });      
            hb.getChildren().add(trashButton);

            hb.setSpacing(10);
            
            vb.getChildren().add(hb);
        }
        
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(25);
        vb.setId("center");
        
        bp.setCenter(vb);
        bp.setAlignment(vb, Pos.CENTER);
               
        Button button2= new Button("Back");
        button2.setAlignment(Pos.CENTER);
        button2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                  root.getChildren().get(5).setVisible(false);
                  root.getChildren().get(10).setVisible(true);
//                primaryStage.setScene(mainMenuScene);

            }
        });
        bp.setBottom(button2);
        bp.setAlignment(button2, Pos.CENTER);
        

//        savedMessagesScene = new Scene(bp, 400, 550);
        if (!am.isInitialized){
            Pane savedMessagesPane = new Pane(bp); //5
            root.getChildren().add(savedMessagesPane);

            root.getChildren().get(5).setVisible(false);
        } else {
            Pane savedMessagesPane = new Pane(bp);
            root.getChildren().add(5, savedMessagesPane); //5
            root.getChildren().remove(6);
            root.getChildren().get(5).setVisible(false);
        }
    }
    
    public void createNewMessagesScene(Stage primaryStage){
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 20, 10, 20));
        
        Label label1= new Label("New Messages");
        label1.setAlignment(Pos.TOP_CENTER);
        label1.setId("heading");
        
        bp.setTop(label1);
        bp.setAlignment(label1, Pos.CENTER);
        
        VBox vb = new VBox();

        for (Message m : am.newMessages.getMessages()){
            HBox hb = new HBox();
            
            Label name = new Label(m.getSenderName());
            name.setAlignment(Pos.CENTER);
//            name.setId("name");
            
            hb.getChildren().add(name);
            
            Button listenButton = new Button("\uf04b");
            listenButton.setFont(fontAwesome);
            
            listenButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    am.newMessages.playMessage(m.messageId);
//                    primaryStage.setScene(newMessagesScene);
                }
            });      
            hb.getChildren().add(listenButton);

            Button trashButton = new Button("\uf1f8");
            trashButton.setFont(fontAwesome);
            
            trashButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    am.deleteMessage("New", m.getMessageId());
//                    primaryStage.setScene(newMessagesScene);
                    System.out.println("Message deleted");
//                  root.getChildren().get(4).setVisible(false);
//                  root.getChildren().get(4).setVisible(true);
                }
            });      
            hb.getChildren().add(trashButton);
            
            Button saveButton = new Button("\uf0c7");
            saveButton.setFont(fontAwesome);

            saveButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    am.moveNewMessageToSavedMessages(m.getMessageId());
//                    primaryStage.setScene(newMessagesScene);
                    System.out.println("Message saved");
//                  root.getChildren().get(4).setVisible(false);
//                  root.getChildren().get(4).setVisible(true);
                }
            });      
            hb.getChildren().add(saveButton);

            hb.setSpacing(10);
            vb.getChildren().add(hb);
        }
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(25);
//        vb.setId("center");
        
        bp.setCenter(vb);
        bp.setAlignment(vb, Pos.CENTER);
               
        Button button2= new Button("Back");
//        button2.setId("bottom-button");
        button2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

//                primaryStage.setScene(mainMenuScene);
                  root.getChildren().get(4).setVisible(false);
                  root.getChildren().get(10).setVisible(true);
            }
        });
        button2.setAlignment(Pos.BOTTOM_CENTER);

        bp.setBottom(button2);
        bp.setAlignment(button2, Pos.CENTER);
        
        if (!am.isInitialized){
            Pane newMessagesPane = new Pane(bp);
            root.getChildren().add(newMessagesPane); //4
            root.getChildren().get(4).setVisible(false);
        } else {
            Pane newMessagesPane = new Pane(bp);
            
            root.getChildren().add(4, newMessagesPane); //4
            root.getChildren().remove(5);
            root.getChildren().get(4).setVisible(false);
        }

//        newMessagesScene = new Scene(bp, 400, 550);
    }
    
    public void createOwnerCallerScene(Stage primaryStage){
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 20, 10, 20));
        
        Label label1= new Label("Welcome to " + am.getOwnerName() + "'s voicemail!");
        bp.setTop(label1);
        bp.setAlignment(label1, Pos.CENTER);
        
        Label label2= new Label("If you are the owner, you can log in.");
        
        Label label3= new Label("If you are a caller, you can leave a message.");
        
        Button button1= new Button("Owner");
        button1.setOnAction(new EventHandler<ActionEvent>() {
   
            @Override
            public void handle(ActionEvent event) {

                
//                loginScene.getStylesheets().add(SimpleAnsweringMachine.class.getResource(styleSheetPath).toExternalForm());
                  root.getChildren().get(9).setVisible(false);
                  root.getChildren().get(7).setVisible(true);
//                primaryStage.setScene(loginScene);
            }
        });    
        
        createLoginScene(primaryStage); //7
        createLeaveMessageScene(primaryStage); //8
        
        Button button4= new Button("Leave a Message");
        button4.setOnAction(new EventHandler<ActionEvent>() {
   
            @Override
            public void handle(ActionEvent event) {
//                createLeaveMessageScene(primaryStage);
                  root.getChildren().get(9).setVisible(false);
                  root.getChildren().get(8).setVisible(true);
//                leaveMessageScene.getStylesheets().add(SimpleAnsweringMachine.class.getResource(styleSheetPath).toExternalForm());

//                primaryStage.setScene(leaveMessageScene);
            }
        });
        
        VBox layout1 = new VBox(20);
//        layout1.setId("center");
        layout1.setAlignment(Pos.CENTER);
        layout1.setSpacing(25);
        layout1.getChildren().addAll(label2, button1, label3, button4);
        
        bp.setCenter(layout1);
        bp.setAlignment(layout1, Pos.CENTER);
        
        Pane ownerCallerPane = new Pane(bp);
        root.getChildren().add(ownerCallerPane); //9
        root.getChildren().get(9).setVisible(false);
//        root.getChildren().get(8).setVisible(false);
//        root.getChildren().get(7).setVisible(false);
//        ownerCallerScene = new Scene(bp, 400, 550);
    }
    
    public void createLeaveMessageScene(Stage primaryStage){
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 20, 10, 20));
        
        Label label1= new Label("Leave Message For " + am.getOwnerName());
        bp.setTop(label1);
        bp.setAlignment(label1, Pos.CENTER);
        
        Label label3= new Label("Please enter your name:");
        
        TextField tf= new TextField();
        tf.setMaxWidth(200);
        tf.setAlignment(Pos.CENTER);
        
        Label info= new Label();
        info.setMaxWidth(200);
        info.setAlignment(Pos.CENTER);
                
        Button button1= new Button("Record Message");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
//                button1.setText("Recording");
                String senderName = tf.getText();
                am.leaveMessage(senderName);
                tf.clear();
                
//                button1.setText("Finished");
                info.setText("Thank you for leaving a message!");

                  root.getChildren().get(8).setVisible(false);
                  root.getChildren().get(9).setVisible(true);
            }
        });
        
        Button button2= new Button("Back");
        button2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

//                primaryStage.setScene(ownerCallerScene);
                  root.getChildren().get(8).setVisible(false);
                  root.getChildren().get(9).setVisible(true);
            }
        });
        bp.setBottom(button2);
        bp.setAlignment(button2, Pos.CENTER);
        
        VBox layout1 = new VBox(20); 
        layout1.setAlignment(Pos.CENTER);
        layout1.setSpacing(25);
        layout1.getChildren().addAll(label3, tf, button1, info);
//        layout1.setId("center");
        
        bp.setCenter(layout1);
        bp.setAlignment(layout1, Pos.CENTER);
        
        Pane leaveMessagesPane = new Pane(bp); //8
        root.getChildren().add(leaveMessagesPane);
        root.getChildren().get(8).setVisible(false);
//        leaveMessageScene = new Scene(bp, 400, 550);
    }
    
    public void createLoginScene(Stage primaryStage){
        Label label1= new Label("Welcome back " + am.getOwnerName() + "!");
        
        Label label3= new Label("Enter your passcode:");
        
        TextField tf= new TextField();
        tf.setMaxWidth(200);
        tf.setAlignment(Pos.CENTER);
        
        Label warning= new Label();
        warning.setMaxWidth(200);
        warning.setAlignment(Pos.CENTER);
                
        Button button1= new Button("Login");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {

                String tryPasscode = tf.getText();
                tf.clear();
                if (!am.authenticatePasscode(tryPasscode)){
                    warning.setText("Invalid passcode. You have " + tries + " tries left.");
                    tries--;
                    root.getChildren().get(7).setVisible(false);
                    root.getChildren().get(7).setVisible(true);
//                    primaryStage.setScene(loginScene);
                } else if (tries == 0){
                    warning.setText("You have failed to enter the correct passcode. Please try again later.");
//                    primaryStage.setScene(ownerCallerScene);
                    root.getChildren().get(7).setVisible(false);
                    root.getChildren().get(9).setVisible(true);
                } else {
                    System.out.println("Passcode authenticated.");
//                    primaryStage.setScene(mainMenuScene);
                    root.getChildren().get(7).setVisible(false);
                    root.getChildren().get(10).setVisible(true);
                }
            }
        });
        
        Button button2= new Button("Back");
        button2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

//                primaryStage.setScene(ownerCallerScene);
                  root.getChildren().get(7).setVisible(false);
                  root.getChildren().get(9).setVisible(true);
            }
        });
        
        VBox layout1 = new VBox(20); 
        layout1.setAlignment(Pos.CENTER);
        layout1.setSpacing(25);
        layout1.setId("center");
        layout1.getChildren().addAll(label1, label3, tf, button1, warning, button2);
        
        Pane loginPane = new Pane(layout1);
        root.getChildren().add(loginPane); //7
        root.getChildren().get(7).setVisible(false);
//        loginScene = new Scene(layout1, 400, 550);
    }
    
    public void createSettingsScene(Stage primaryStage){
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 20, 10, 20));
        
        Label label4= new Label("Settings");
        bp.setTop(label4);
        bp.setAlignment(label4, Pos.CENTER);
        
//        createSetPasscodeScene(primaryStage);
        
        Button button2= new Button("Change Passcode");
        button2.setOnAction(new EventHandler<ActionEvent>() {
   
            @Override
            public void handle(ActionEvent event) {    
                createSetPasscodeScene(primaryStage);
//                passcodeScene.getStylesheets().add(SimpleAnsweringMachine.class.getResource(styleSheetPath).toExternalForm());
                  root.getChildren().get(6).setVisible(false);
                  root.getChildren().get(2).setVisible(true);
//                primaryStage.setScene(passcodeScene);
            }
        }); 
        
        Button button3= new Button("Play Greeting");
        button3.setOnAction(new EventHandler<ActionEvent>() {
   
            @Override
            public void handle(ActionEvent event) {      
                am.listenToGreeting();
            }
        }); 
        
        Button button4= new Button("Record New Greeting");
        button4.setOnAction(new EventHandler<ActionEvent>() {
   
            @Override
            public void handle(ActionEvent event) {     
                createNewGreetingScene(primaryStage);
//                greetingScene.getStylesheets().add(SimpleAnsweringMachine.class.getResource(styleSheetPath).toExternalForm());
                  root.getChildren().get(6).setVisible(false);
                  root.getChildren().get(3).setVisible(true);
//                primaryStage.setScene(greetingScene);            
            }
        }); 
        
        Button button5= new Button("Back");
        button5.setOnAction(new EventHandler<ActionEvent>() {
   
            @Override
            public void handle(ActionEvent event) {  
                  root.getChildren().get(6).setVisible(false);
                  root.getChildren().get(10).setVisible(true);
//                primaryStage.setScene(mainMenuScene);            
            }
        }); 
        bp.setBottom(button5);
        bp.setAlignment(button5, Pos.CENTER);
        
        VBox layout2= new VBox(20);
        layout2.setAlignment(Pos.CENTER);
        layout2.setSpacing(25);
        layout2.getChildren().addAll(button2, button3, button4);
//        layout2.setId("center");
        
        bp.setCenter(layout2);
        bp.setAlignment(layout2, Pos.CENTER);
        
        Pane settingsPane = new Pane(bp);
        root.getChildren().add(settingsPane); //6
        root.getChildren().get(6).setVisible(false);
//        settingsScene = new Scene(bp,400, 550);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
