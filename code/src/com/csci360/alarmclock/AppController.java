/*
 * CSCI 360 Semester Project
 * Team 6ix - Dual Alarm Clock Radio
 * Professor: Dr. Bowring
 */
package com.csci360.alarmclock;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

public class AppController implements Initializable {

    public AlarmClockRadio arc;

    @FXML
    public Font fontAwesome;

    public Label timeLabel;
    public Label amLabel;
    public Label pmLabel;
    public Label hourLabel;
    public Label minLabel;
    public Label colonLabel;

    public Button snoozeButton;

    public Button alarm1_On_Button;
    public Button alarm2_On_Button;

    public MediaPlayer alarmMediaPlayer;
    public Button setAlarmTimeButton;
    public Button setTimeButton;
    public Button alarm1Button;
    public Button alarm2Button;
    public boolean selectAlarm1 = false;
    public boolean selectAlarm2 = false;

    public boolean timeSettingOn = false;
    public int hour;
    public int min;
    public String amPm;

    public boolean radioOn = false;
    public boolean amModeOn = true;
    public RadioButton amRadioButton;
    public RadioButton fmRadioButton;
    public Slider amSlider;
    public Slider fmSlider;
    public double amFrequency;
    public double fmFrequency;
    public MediaPlayer player;
    public List<Media> amMediaList = new ArrayList<>();
    public List<Media> fmMediaList = new ArrayList<>();
    public MediaPlayer amMediaPlayer;
    public MediaPlayer fmMediaPlayer;
    public Button radioOnOffButton;

    public Slider volumeSlider;

    public void createMediaLists() {
        for (Station s : arc.getRadio().getStations("am")) {
            Media media = new Media(getClass().getResource("/com/csci360/alarmclock/soundsAM/" + s.name).toString());
            amMediaList.add(media);
        }
        for (Station s : arc.getRadio().getStations("fm")) {
            Media media = new Media(getClass().getResource("/com/csci360/alarmclock/soundsFM/" + s.name).toString());
            fmMediaList.add(media);
        }

    }

    public void setSlidersToMedia() {

        amSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (amModeOn && !amSlider.isValueChanging()) {
                amMediaPlayer.stop();
                amFrequency = newValue.doubleValue();
                arc.setFrequency("am", amFrequency);
                int index = arc.getRadio().convertToPlayableStation();

                if (radioOn) {
                    if (amModeOn) {
                        amMediaPlayer = new MediaPlayer(amMediaList.get(index));
                        amMediaPlayer.setVolume(arc.getVolume());
                        amMediaPlayer.play();
                    }
                }
            }
        });

        fmSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!amModeOn && !fmSlider.isValueChanging()) {
                fmMediaPlayer.stop();
                fmFrequency = newValue.doubleValue();
                arc.setFrequency("fm", fmFrequency);
                int index = arc.getRadio().convertToPlayableStation();

                if (radioOn) {
                    if (!amModeOn) {
                        fmMediaPlayer = new MediaPlayer(fmMediaList.get(index));
                        fmMediaPlayer.setVolume(arc.getVolume());
                        fmMediaPlayer.play();
                    }
                }
            }
        });

    }

    @FXML
    public void turnRadioOnOff(ActionEvent event) throws IOException {
        if (!radioOn) {
            radioOn = true;
            radioOnOffButton.setStyle("-fx-effect: null; -fx-background-color: #dc6a02; -fx-background-radius: 4px;");
            arc.turnRadioOnOrOff();
            if (amModeOn) {
                amFrequency = amSlider.getValue();
                arc.setFrequency("am", amFrequency);
                int index = arc.getRadio().convertToPlayableStation();
                amMediaPlayer = new MediaPlayer(amMediaList.get(index));
                amMediaPlayer.setVolume(arc.getVolume());
                amMediaPlayer.play();
            } else {
                fmFrequency = fmSlider.getValue();
                arc.setFrequency("fm", fmFrequency);
                int index = arc.getRadio().convertToPlayableStation();
                fmMediaPlayer = new MediaPlayer(fmMediaList.get(index));
                fmMediaPlayer.setVolume(arc.getVolume());
                fmMediaPlayer.play();
            }
        } else {
            radioOn = false;
            radioOnOffButton.setStyle("-fx-effect: null; -fx-background-color: #fe8f2d; -fx-background-radius: 4px;");
            arc.turnRadioOnOrOff();
        }
    }

    @FXML
    public void toggleFMOnOff(ActionEvent event) {
        if (radioOn) {
            amModeOn = false;
            amRadioButton.setSelected(false);
            arc.setAmFmMode("fm");
            fmFrequency = fmSlider.getValue();
            arc.setFrequency("fm", fmFrequency);
            fmRadioButton.setSelected(true);
            amMediaPlayer.stop();
            int index = arc.getRadio().convertToPlayableStation();
            fmMediaPlayer = new MediaPlayer(fmMediaList.get(index));
            fmMediaPlayer.play();
        } else {
            amModeOn = false;
            amRadioButton.setSelected(false);
            arc.setAmFmMode("fm");
            fmRadioButton.setSelected(true);
        }
    }

    @FXML
    public void toggleAMOnOff(ActionEvent event) {
        if (radioOn) {
            amModeOn = true;
            amRadioButton.setSelected(true);
            arc.setAmFmMode("am");
            amFrequency = amSlider.getValue();
            arc.setFrequency("am", amFrequency);
            fmRadioButton.setSelected(false);
            fmMediaPlayer.stop();
            int index = arc.getRadio().convertToPlayableStation();
            amMediaPlayer = new MediaPlayer(amMediaList.get(index));
            amMediaPlayer.play();
        } else {
            amModeOn = true;
            amRadioButton.setSelected(true);
            arc.setAmFmMode("am");
            fmRadioButton.setSelected(false);
        }
    }

    @FXML
    public void turnStandardOn(ActionEvent event) {
        arc.changeTimeFormat("standard");
        amLabel.setVisible(true);
        pmLabel.setVisible(true);
    }

    @FXML
    public void turnMilitaryOn(ActionEvent event) {
        arc.changeTimeFormat("military");
        amLabel.setVisible(false);
        pmLabel.setVisible(false);
    }

    @FXML
    public void incrementHour(ActionEvent event) {
        if (timeSettingOn) {
            this.hour++;
            this.hour = this.hour % 24;

            String hourFormatted;
            if (!arc.isClockMilitary()) {
                if (hour == 12 || hour == 0) {
                    hourFormatted = "12";
                } else if (hour < 10 || (hour % 12) < 10) {
                    hourFormatted = "0" + Integer.toString(hour % 12);
                } else {
                    hourFormatted = Integer.toString(hour % 12);
                }

            } else { // Military time
                hourFormatted = Integer.toString(hour);
                if (hour < 10) {
                    hourFormatted = "0" + Integer.toString(hour);
                }
            }

            hourLabel.setText(hourFormatted);
        }
    }

    @FXML
    public void incrementMinute(ActionEvent event) {
        if (timeSettingOn) {
            this.min++;
            this.min = this.min % 60;
            String minFormatted = Integer.toString(min);
            if (min < 10) {
                minFormatted = "0" + Integer.toString(min);
            }
            minLabel.setText(minFormatted);
        }
    }

    @FXML
    public void setTime(ActionEvent event) {
        Timer timeSettingTimer = new Timer();
        timeSettingTimer.scheduleAtFixedRate(new TimerTask() {

            boolean flashOn = false;

            @Override
            public void run() {
                if (flashOn) {
                    colonLabel.setVisible(true);
                } else {
                    colonLabel.setVisible(false);
                }
                flashOn = !flashOn;

                if (hour < 12) { // am must light up
                    amLabel.setStyle("-fx-text-fill: #dc6a02;");
                    pmLabel.setStyle("-fx-text-fill: #63665b;");
                } else { // pm must light up
                    amLabel.setStyle("-fx-text-fill: #63665b;");
                    pmLabel.setStyle("-fx-text-fill: #dc6a02;");
                }

                if (!AppController.this.timeSettingOn) {
                    colonLabel.setVisible(false);
                    timeSettingTimer.cancel();
                }
            }
        }, 0, 1000);

        if (!timeSettingOn) { //entering set time mode
            timeSettingOn = true;

            hour = arc.getHour();
            min = arc.getMinute();

            String minFormatted = Integer.toString(min);
            if (min < 10) {
                minFormatted = "0" + Integer.toString(min);
            }

            String hourFormatted;
            if (!arc.isClockMilitary()) {
                if (hour == 12 || hour == 0) {
                    hourFormatted = "12";
                } else if (hour < 10 || (hour % 12) < 10) {
                    hourFormatted = "0" + Integer.toString(hour % 12);
                } else {
                    hourFormatted = Integer.toString(hour % 12);
                }
            } else { // Military time
                hourFormatted = Integer.toString(hour);

                if (hour < 10) {
                    hourFormatted = "0" + Integer.toString(hour);
                }
            }
            hourLabel.setText(hourFormatted);
            minLabel.setText(minFormatted);
            timeLabel.setVisible(false);
            hourLabel.setVisible(true);
            minLabel.setVisible(true);
            colonLabel.setVisible(true);
        } else {
            timeSettingOn = false;
            arc.setTime(hour, min);
            timeLabel.setVisible(true);
            hourLabel.setVisible(false);
            minLabel.setVisible(false);
            colonLabel.setVisible(false);
            displayTime();
        }
    }

    @FXML
    public void selectAlarm1(ActionEvent event) {
        selectAlarm1 = !selectAlarm1;

        if (selectAlarm1) {
            selectAlarm2 = false;
            alarm2Button.setStyle("-fx-effect: null; -fx-background-color: #fe8f2d; -fx-background-radius: 4px;");
            alarm1Button.setStyle("-fx-effect: null; -fx-background-color: #dc6a02; -fx-background-radius: 4px;");
            setAlarmTimeButton.setVisible(true);
            setTimeButton.setVisible(false);
        } else {
            alarm1Button.setStyle("-fx-effect: null; -fx-background-color: #fe8f2d; -fx-background-radius: 4px;");
            setAlarmTimeButton.setVisible(false);
            setTimeButton.setVisible(true);
        }
    }

    @FXML
    public void selectAlarm2(ActionEvent event) {
        selectAlarm2 = !selectAlarm2;

        if (selectAlarm2) {
            selectAlarm1 = false;
            alarm1Button.setStyle("-fx-effect: null; -fx-background-color: #fe8f2d; -fx-background-radius: 4px;");
            alarm2Button.setStyle("-fx-effect: null; -fx-background-color: #dc6a02; -fx-background-radius: 4px;");
            setAlarmTimeButton.setVisible(true);
            setTimeButton.setVisible(false);
        } else {
            alarm2Button.setStyle("-fx-effect: null; -fx-background-color: #fe8f2d; -fx-background-radius: 4px;");
            setAlarmTimeButton.setVisible(false);
            setTimeButton.setVisible(true);
        }
    }

    @FXML
    public void setAlarmTime(ActionEvent event) {
        Timer timeSettingTimer = new Timer();
        timeSettingTimer.scheduleAtFixedRate(new TimerTask() {

            boolean flashOn = false;

            @Override
            public void run() {
                if (flashOn) {
                    colonLabel.setVisible(true);
                } else {
                    colonLabel.setVisible(false);
                }
                flashOn = !flashOn;

                if (hour < 12) { // am must light up
                    amLabel.setStyle("-fx-text-fill: #dc6a02;");
                    pmLabel.setStyle("-fx-text-fill: #63665b;");
                } else { // pm must light up
                    amLabel.setStyle("-fx-text-fill: #63665b;");
                    pmLabel.setStyle("-fx-text-fill: #dc6a02;");
                }

                if (!AppController.this.timeSettingOn) {
                    colonLabel.setVisible(false);
                    timeSettingTimer.cancel();
                }
            }
        }, 0, 1000);

        if (!timeSettingOn) {
            timeSettingOn = true;

            if (selectAlarm1) {
                hour = arc.getAlarmHour(1);
                min = arc.getAlarmMinute(1);
            }

            if (selectAlarm2) {
                hour = arc.getAlarmHour(2);
                min = arc.getAlarmMinute(2);
            }

            String minFormatted = Integer.toString(min);
            if (min < 10) {
                minFormatted = "0" + Integer.toString(min);
            }

            String hourFormatted;
            if (!arc.isClockMilitary()) {
                if (hour == 12 || hour == 0) {
                    hourFormatted = "12";
                } else if (hour < 10 || (hour % 12) < 10) {
                    hourFormatted = "0" + Integer.toString(hour % 12);
                } else {
                    hourFormatted = Integer.toString(hour % 12);
                }
            } else { // Military time
                hourFormatted = Integer.toString(hour);

                if (hour < 10) {
                    hourFormatted = "0" + Integer.toString(hour);
                }
            }

            hourLabel.setText(hourFormatted);
            minLabel.setText(minFormatted);

            timeLabel.setVisible(false);

            hourLabel.setVisible(true);
            minLabel.setVisible(true);
            colonLabel.setVisible(true);

        } else {
            timeSettingOn = false;

            if (selectAlarm1) {
                arc.setAlarmTime(1, hour, min);
                arc.getAlarm(1).toggleAlarmIsSet(true);
                alarm1_On_Button.setStyle("-fx-text-fill: #dc6a02;-fx-background-color: #45463f;");
                alarm1Button.setStyle("-fx-effect: null; -fx-background-color: #fe8f2d; -fx-background-radius: 4px;");
                selectAlarm1 = false;
            }

            if (selectAlarm2) {
                arc.setAlarmTime(2, hour, min);
                arc.getAlarm(2).toggleAlarmIsSet(true);
                alarm2_On_Button.setStyle("-fx-text-fill: #dc6a02;-fx-background-color: #45463f;");
                alarm2Button.setStyle("-fx-effect: null; -fx-background-color: #fe8f2d; -fx-background-radius: 4px;");
                selectAlarm2 = false;
            }

            timeLabel.setVisible(true);

            hourLabel.setVisible(false);
            minLabel.setVisible(false);
            colonLabel.setVisible(false);

            setAlarmTimeButton.setVisible(false);
            setTimeButton.setVisible(true);

            displayTime();
        }
    }

    @FXML
    public void toggleAlarm1ToGoOnOff(ActionEvent event) {
        arc.getAlarm(1).alarmIsSet = !arc.getAlarm(1).alarmIsSet;
        if (arc.getAlarm(1).alarmIsSet) {
            alarm1_On_Button.setStyle("-fx-text-fill: #dc6a02;-fx-background-color: #45463f;");
        } else {
            alarm1_On_Button.setStyle("-fx-text-fill: #63665b;-fx-background-color: #45463f;");
        }
    }

    @FXML
    public void toggleAlarm2ToGoOnOff(ActionEvent event) {
        arc.getAlarm(2).alarmIsSet = !arc.getAlarm(2).alarmIsSet;
        if (arc.getAlarm(2).alarmIsSet) {
            alarm2_On_Button.setStyle("-fx-text-fill: #dc6a02;-fx-background-color: #45463f;");
        } else {
            alarm2_On_Button.setStyle("-fx-text-fill: #63665b;-fx-background-color: #45463f;");
        }
    }

    @FXML
    public void snooze(ActionEvent event) {
        if (arc.getAlarm(1).alarmIsOn) {
            alarmMediaPlayer.stop();
            arc.getAlarm(1).snoozeAlarm();
        }
        if (arc.getAlarm(2).alarmIsOn) {
            alarmMediaPlayer.stop();
            arc.getAlarm(2).snoozeAlarm();
        }
    }

    @FXML
    public void turnAlarmOff(ActionEvent event) {
        if (arc.getAlarm(1).alarmIsOn) {
            alarmMediaPlayer.stop();
            arc.getAlarm(1).turnAlarmSoundOff();
        }
        if (arc.getAlarm(2).alarmIsOn) {
            alarmMediaPlayer.stop();
            arc.getAlarm(2).turnAlarmSoundOff();
        }
    }

    public void displayTime() {
        hourLabel.setVisible(false);
        minLabel.setVisible(false);
        colonLabel.setVisible(false);
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                String time = arc.getTime();//02:15 PM
                String hour = time.substring(0, 2);
                String min = time.substring(3, 5);

                Platform.runLater(new Runnable() {
                    public void run() {
                        timeLabel.setText(hour + ":" + min);
                    }
                });

                String amPm;
                if (!arc.isClockMilitary()) {
                    amPm = time.substring(6);

                    if (amPm.equalsIgnoreCase("AM")) {
                        amLabel.setStyle("-fx-text-fill: #dc6a02;");
                        pmLabel.setStyle("-fx-text-fill: #63665b;");
                    } else {
                        amLabel.setStyle("-fx-text-fill: #63665b;");
                        pmLabel.setStyle("-fx-text-fill: #dc6a02;");
                    }
                }

                if (AppController.this.timeSettingOn) {
                    t.cancel();
                }

                if (arc.clock.getAlarm(1).isSounding()) {
                    alarmMediaPlayer.play();
                }

                if (arc.clock.getAlarm(2).isSounding()) {
                    alarmMediaPlayer.play();
                }
            }
        }, 0, 1000);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        arc = new AlarmClockRadio();

        createMediaLists();
        setSlidersToMedia();

        InputStream fafont = AppController.class.getResourceAsStream("/com/csci360/alarmclock/fonts/fontawesome-webfont.ttf");
        fontAwesome = Font.loadFont(fafont, 34);

        hourLabel.setVisible(false);
        minLabel.setVisible(false);
        colonLabel.setVisible(false);
        setAlarmTimeButton.setVisible(false);

        alarm1_On_Button.setText("\uf017");
        alarm1_On_Button.setFont(fontAwesome);
        alarm1_On_Button.setPadding(new Insets(0, 0, 0, 0));

        alarm2_On_Button.setText("\uf017");
        alarm2_On_Button.setFont(fontAwesome);
        alarm2_On_Button.setPadding(new Insets(0, 0, 0, 0));

        amFrequency = amSlider.getValue();
        fmFrequency = fmSlider.getValue();
        arc.setFrequency("am", amFrequency);
        arc.setFrequency("fm", fmFrequency);

        volumeSlider.setMin(0.0);
        volumeSlider.setMax(100.0);

        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                arc.setVolume(volumeSlider.getValue());
                if (radioOn) {
                    if (amModeOn) {
                        amMediaPlayer.setVolume(arc.getVolume());
                    } else {
                        fmMediaPlayer.setVolume(arc.getVolume());
                    }
                }
            }
        });

        Media alarmMedia = new Media(getClass().getResource("/com/csci360/alarmclock/alarmSound/longAlarmSound2.m4a").toString());
        alarmMediaPlayer = new MediaPlayer(alarmMedia);

        displayTime();
    }
}
