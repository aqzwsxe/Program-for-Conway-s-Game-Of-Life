package com.projectFiles.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class MusicPlayer extends Application {
    private Label selectMusicLabel;
    private GameView gameView;
    private Button PlayMusicButton;
    private Button StopMusicButton;
    private Button closeWindowButton;
    private Scene dialogScene;

    private ListView<String> MusicList;
    private String filename = null;
    private AudioClip audioClip;

    private Boolean musicOn = false;
    private Boolean MusicSelected = false;

    Stage dialog;

    public MusicPlayer(GameView adv) {
        this.gameView = adv;
        selectMusicLabel = new Label(String.format(""));
        
        MusicList = new ListView<>();
        dialog = new Stage(); //dialogue box      stage contains scene; stage.show() to show the stage
        // stage is the basic container of the image
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(gameView.stage);

        VBox dialogVbox = new VBox(20); // the basic GUI box; the root for button and other input
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));
        dialogVbox.setStyle("-fx-background-color: #121212;");

        selectMusicLabel.setId("No music");
        MusicList.setId("MusicList");
        MusicList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        getFiles(MusicList);
        PlayMusicButton = new Button("Change/play Music");

        StopMusicButton = new Button("Stop the music");

        closeWindowButton = new Button("Close Window");
        closeWindowButton.setId("closeWindowButton"); // DO NOT MODIFY ID
        closeWindowButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        closeWindowButton.setPrefSize(200, 50);
        closeWindowButton.setFont(new Font(16));
        closeWindowButton.setOnAction(e -> dialog.close());

        PlayMusicButton.setOnAction(e->{
            // If music is on stop it first
            if (musicOn) {
                audioClip.stop();
                musicOn = false;
            }
            selectMusic(selectMusicLabel,MusicList);
            if (MusicSelected) {
                audioClip.play();
                musicOn = true;
            }

        });
        
        StopMusicButton.setOnAction(e->{
            if (musicOn) {
                audioClip.stop();
                musicOn = false;
            }



            
        });
        

        VBox selectMusicBox = new VBox(10, selectMusicLabel, MusicList, PlayMusicButton,StopMusicButton);

        MusicList.setPrefHeight(100);
        selectMusicLabel.setStyle("-fx-text-fill: #e8e6e3");
        selectMusicLabel.setFont(new Font(16));
        
        PlayMusicButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        PlayMusicButton.setPrefSize(200, 50);
        PlayMusicButton.setFont(new Font(16));

        PlayMusicButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        PlayMusicButton.setPrefSize(200, 50);
        PlayMusicButton.setFont(new Font(16));
        
        
        selectMusicBox.setAlignment(Pos.CENTER);
        dialogVbox.getChildren().add(selectMusicBox);
        dialogScene = new Scene(dialogVbox, 400, 400);
        dialog.setScene(dialogScene);
        dialog.show();

    }

    /*
    This is a function to populate all of the music
     */
    private void getFiles(ListView<String> listView) {
        File folder = new File("Module1/src/com/projectFiles/GameFiles/music");
        File[] files = folder.listFiles();
        if (files.length >= 1) {
            for (File file : files) {
                if (file.getName().endsWith(".mp3")) {
                    listView.getItems().add(file.getName());
                }
            }
        }
    }


    private void selectMusic(Label selectMusicLabel, ListView<String> musicList){
        filename = musicList.getSelectionModel().getSelectedItem();
        System.out.println(filename);
        if (filename == null) {
            MusicSelected = false;
            return;
        }
        MusicSelected = true;
        String filePath = "Module1/src/com/projectFiles/GameFiles/music/"+ filename;
        audioClip = new AudioClip(new File(filePath).toURI().toString());
    }




    @Override
    public void start(Stage stage) throws Exception {

    }
}
