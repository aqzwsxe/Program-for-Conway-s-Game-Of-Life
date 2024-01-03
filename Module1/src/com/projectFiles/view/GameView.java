package com.projectFiles.view;

import com.projectFiles.player.BaiscGame;
import com.projectFiles.player.Clock;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameView extends Application {

    BaiscGame model;
    Stage stage;

    GridPane gridPane = new GridPane(90, 90);
    VBox vBox = new VBox(gridPane);
    Button[][] cellButtons = new Button[9][9];
    Boolean green = true;
    GridPane large_grid = new GridPane();
    VBox buttonBox = new VBox();
    Button timeButton, musicButton, start_or_pauseButton, instructionButton;

    Boolean timeToggle = false;
    Boolean instructionToggle = false;


    VBox boxTime = new VBox();
    VBox boxInstruction = new VBox();

    public void createGrid() {
        gridPane.setHgap(2);
        gridPane.setVgap(2);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Button button = new Button();
                button.setMinHeight(35);
                button.setMinWidth(35);
                button.setPrefSize(10, 10);
                button.setStyle("-fx-background-color: green;");
                button.setOnAction(s -> {
                    if (green) {
                        button.setStyle("-fx-background-color: blue;");
                        green = false;
                    } else {
                        button.setStyle("-fx-background-color: green;");
                        green = true;
                    }
                });

                gridPane.add(button, i, j);
            }
        }

    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("The Game view");
        createGrid();
        large_grid.setPadding(new Insets(60));
        large_grid.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#FFFFFF"),
                new CornerRadii(0),
                new Insets(0)
        )));

        timeButton = new Button("Time");
        timeButton.setId("TimeButton");
        customizeButton(timeButton, 90, 50);
        addTimeEvent();

        musicButton = new Button("music");
        musicButton.setId("music button");
        customizeButton(musicButton, 90, 50);
        musicButton.setOnAction(s -> {
            MusicPlayer musicPlayer = new MusicPlayer(this);
            System.out.println("music player");

        });

        instructionButton = new Button("instruction");
        instructionButton.setId("instruction button");
        customizeButton(instructionButton, 150, 50);
        addInstructionEvent();

        vBox.getChildren().addAll(musicButton, timeButton, instructionButton);
        vBox.setSpacing(5);


        large_grid.add(gridPane, 0, 0);
        large_grid.add(vBox, 1, 0, 1, 1);
        large_grid.setHgap(10);
        large_grid.setVgap(10);
        Scene scene = new Scene(large_grid, 700, 500);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    /**
     * customizeButton
     * __________________________
     *
     * @param inputButton the button to make stylish :)
     * @param w           width
     * @param h           height
     */
    public void customizeButton(Button inputButton, int w, int h) {
        inputButton.setPrefSize(w, h);
        inputButton.setFont(new Font("Arial", 16));
        inputButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;");
    }


    /*
     * This method handles the event related to the
     * Time button.
     */
    public void addTimeEvent() {
        large_grid.getChildren().remove(0,0);
        timeButton.setOnAction(e -> {
            // if timeTaggle = false; post the time and set it to true

            if (this.timeToggle) {
                // remove the time and turn on the grid

                this.large_grid.getChildren().remove(boxTime);

                this.large_grid.add(gridPane, 0, 0); // add the boxTime to the cell (0,0)
                this.timeToggle = false;
            } else {
                // remove the grid and turn on the time
                this.large_grid.getChildren().remove(gridPane);
                Clock instanceClock = Clock.getInstanceClock();
                String timeStr = instanceClock.CurrentTime();
                Text text = new Text(timeStr);

                text.setFont(new Font(30));
                text.setStyle("-fx-fill: #FFFFFF"); // set the color of the text to white
                text.setWrappingWidth(200);// set the length of each line
                // of word
                boxTime.getChildren().add(text);
                boxTime.setPadding(new Insets(10));
                boxTime.setAlignment(Pos.TOP_LEFT);
                boxTime.setStyle("-fx-background-color: " + "#000000" + ";");

                this.large_grid.add(boxTime, 0, 0);
                this.timeToggle = true;

            }
        });


    }


    public void addInstructionEvent() {
        instructionButton.setOnAction(s->{
                String instruction =
                        "Each cell with one or no neighbors dies, as if by solitude.\n" +
                        "\n" +
                        "\n" +
                        "Each cell with four or more neighbors dies, as if by overpopulation.\n" +
                        "\n" +
                        "\n" +
                        "Each cell with two or three neighbors survives.\n" +
                        "\n" +
                        "\n" +
                        "For a space that is empty or unpopulated:\n" +
                        "Each cell with three neighbors becomes populated.";

                // If it is false
            if (!instructionToggle) {
                Text text = new Text(instruction);
                boxInstruction.getChildren().add(text);
                large_grid.getChildren().remove(gridPane);
                large_grid.add(boxInstruction, 0,0);
                this.instructionToggle = true;
            } else {
                large_grid.getChildren().remove(boxInstruction);
                large_grid.add(gridPane,0,0);
                this.instructionToggle= false;
            }
        });

    }


}
