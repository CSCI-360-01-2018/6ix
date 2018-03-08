/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.answeringMachine;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author MeganLandau
 */
public class GifAnimation {
    Group am;
    
    String imagePath = "/com/csci360/answeringMachine/images/";
    final Image am_1 =  new Image(GifAnimation.class.getResource(imagePath + "answering_machine_00.png").toString());
    final Image am_2 =  new Image(GifAnimation.class.getResource(imagePath + "answering_machine_01.png").toString());
    final Image am_3 =  new Image(GifAnimation.class.getResource(imagePath + "answering_machine_02.png").toString());
    final Image am_4 =  new Image(GifAnimation.class.getResource(imagePath + "answering_machine_03.png").toString());
    final Image am_5 =  new Image(GifAnimation.class.getResource(imagePath + "answering_machine_04.png").toString());
    final Image am_6 =  new Image(GifAnimation.class.getResource(imagePath + "answering_machine_05.png").toString());

        
    final ImageView am1 = new ImageView(am_1);
    final ImageView am2 = new ImageView(am_2);
    final ImageView am3 = new ImageView(am_3);
    final ImageView am4 = new ImageView(am_4);
    final ImageView am5 = new ImageView(am_5);
    final ImageView am6 = new ImageView(am_6);
    
    Scene am_scene;
    Timeline t;
    
public GifAnimation(){
    List<Image> images = new ArrayList<Image>();
    images.add(am_1);
    images.add(am_2);
    images.add(am_3);
    images.add(am_4);
    images.add(am_5);
    images.add(am_6);

    List<ImageView> imageViews = new ArrayList<ImageView>();
    imageViews.add(am1);
    imageViews.add(am2);
    imageViews.add(am3);
    imageViews.add(am4);
    imageViews.add(am5);
    imageViews.add(am6);
    
    for (ImageView i : imageViews ){
        i.setFitWidth(368);
        i.setFitHeight(597);
    }

    am = new Group(am1);
    t = buildAnimation();
    Group f = new Group(am);
    am.setTranslateX(10);
    am.setTranslateY(10);
    am_scene = new Scene(f, 388, 617);
}

public void play(){
    this.t.play();
}

public void pause(){
    this.t.pause();
}

public Timeline buildAnimation(){
    return TimelineBuilder.create()
            .cycleCount(Animation.INDEFINITE)
            .keyFrames(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent t) {
                        am.getChildren().setAll(am1);
                    }
                }),
                new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent t) {
                         am.getChildren().setAll(am2);
                    }
                }),
                new KeyFrame(Duration.millis(300), new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent t) {
                         am.getChildren().setAll(am3);
                    }
                }),
                new KeyFrame(Duration.millis(400), new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent t) {
                         am.getChildren().setAll(am4);
                    }
                }),
                new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent t) {
                         am.getChildren().setAll(am5);
                    }
                }),
                new KeyFrame(Duration.millis(600), new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent t) {
                         am.getChildren().setAll(am6);
                    }
                }) //, need comma
//                new KeyFrame(Duration.millis(700), new EventHandler<ActionEvent>(){
//                    @Override
//                    public void handle(ActionEvent t) {
//                         dog.getChildren().setAll(dog7);
//                    }
//                }),
//                new KeyFrame(Duration.millis(800), new EventHandler<ActionEvent>(){
//                    @Override
//                    public void handle(ActionEvent t) {
//                         dog.getChildren().setAll(dog8);
//                    }
//                }),
//                new KeyFrame(Duration.millis(900), new EventHandler<ActionEvent>(){
//                    @Override
//                    public void handle(ActionEvent t) {
//                         dog.getChildren().setAll(dog9);
//                    }
//                })
            )
            .build();
    }


}
