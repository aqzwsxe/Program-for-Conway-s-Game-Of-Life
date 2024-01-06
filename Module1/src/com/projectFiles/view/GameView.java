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
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class GameView extends Application {

    BaiscGame model;
    Stage stage;

    static GridPane gridPane = new GridPane(90, 90);
    VBox vBox = new VBox(gridPane);
    static Boolean pink = false;
    GridPane large_grid = new GridPane();
    Button timeButton, musicButton, start_or_pauseButton, instructionButton, clearButton, randomGenerate;

    Boolean timeToggle = false;
    Boolean instructionToggle = false;
    Boolean started = false; // If the game started


    VBox boxTime = new VBox();
    VBox boxInstruction = new VBox();

    /*
    pink = 1 = alive
    blue = 0 = dead
     */
    public static void createOrUpdateGridPane() {

        gridPane.setHgap(2);
        gridPane.setVgap(2);
        gridPane.getChildren().removeAll();
        int[][] grid = BaiscGame.grid;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                Button button = new Button();
                button.setMinHeight(35);
                button.setMinWidth(35);
                button.setPrefSize(10, 10);


                if (grid[i][j]==1) {
                    button.setStyle("-fx-background-color: pink;");
                    pink = true;
                } else {
                    button.setStyle("-fx-background-color: blue;");
                    pink = false;
                }

                final int row = i; // Make 'i' effectively final
                final int col = j; // Make 'j' effectively final

                button.setOnAction(s -> {
                    Background background = button.getBackground();
                    final Color fillColor = (Color) background.getFills().get(0).getFill();
                    if (fillColor.equals(Color.PINK)) {
                        button.setStyle("-fx-background-color: blue;");
                        grid[row][col] = 0;
                        pink = false;
                    } else {
                        button.setStyle("-fx-background-color: pink;");
                        grid[row][col] = 1;
                        pink = true;
                    }
                });

                gridPane.add(button, i, j);
            }
        }

    }


    @Override
    public void start(Stage stage) throws Exception {
        boxTime.setStyle("-fx-background-color: black");
        boxInstruction.setStyle("-fx-background-color: black");



        stage.setTitle("The Game view");
        createOrUpdateGridPane();
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

        start_or_pauseButton = new Button("Start/pause");
        start_or_pauseButton.setId("Start/pause");
        customizeButton(start_or_pauseButton, 150, 50);
        addStartPauseEvent();

        clearButton = new Button("Clear");
        clearButton.setId("clear");
        customizeButton(clearButton, 100, 50);
        addClearEvent();

        randomGenerate = new Button("randomGenerate");
        randomGenerate.setId("randomGenerate");
        customizeButton(randomGenerate, 150, 50);
        addRandomEvent();


        vBox.getChildren().addAll(musicButton, timeButton, instructionButton, start_or_pauseButton, clearButton, randomGenerate);
        vBox.setSpacing(5);


        large_grid.add(gridPane, 0, 0);
        large_grid.add(vBox, 1, 0, 1, 1);
        large_grid.setHgap(10);
        large_grid.setVgap(10);

        File file = new File("Module1/src/com/projectFiles/GameFiles/images/background2.jpg");
        System.out.println(file.exists());
        Image backgroundImage = new Image(file.toURI()+"", 1300, 1000,true,true);

        // Create a background image
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );

        // Set the background image to the root pane

        large_grid.setBackground(new Background(background));

        // Create the scene
        Scene scene = new Scene(large_grid, 1200, 900);
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

        timeButton.setOnAction(e -> {
            // if timeTaggle = false; post the time and set it to true
            large_grid.getChildren().remove(gridPane);
            large_grid.getChildren().remove(boxInstruction);
            large_grid.getChildren().remove(boxTime);
            boxTime = new VBox();

            if (this.timeToggle) {
                // remove the time and turn on the grid

                this.large_grid.getChildren().remove(boxTime);
                this.large_grid.getChildren().remove(boxInstruction);

                this.large_grid.add(gridPane, 0, 0); // add the boxTime to the cell (0,0)
                this.timeToggle = false;
            } else {
                // remove the grid and turn on the time
                large_grid.getChildren().remove(gridPane);
                large_grid.getChildren().remove(boxInstruction);

                Clock instanceClock = Clock.getInstanceClock();
                String timeStr = instanceClock.CurrentTime();
                Text text = new Text(timeStr);

                text.setFont(new Font(30));
                text.setStyle("-fx-fill: #FFFFFF"); // set the color of the text to white
                text.setWrappingWidth(200);// set the length of each line
                // of word
                boxTime.getChildren().removeAll();
                boxTime.getChildren().add(text);
                boxTime.setPadding(new Insets(10));
                boxTime.setAlignment(Pos.TOP_LEFT);
                boxTime.setStyle("-fx-background-color: " + "#000000" + ";");


                this.large_grid.add(boxTime, 0, 0);
                this.timeToggle = true;

            }
        });


    }


    /*
     Show the instructions of how to play the game
     */
    public void addInstructionEvent() {

        instructionButton.setOnAction(s -> {
            large_grid.getChildren().remove(gridPane);
            large_grid.getChildren().remove(boxInstruction);
            large_grid.getChildren().remove(boxTime);


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
                text.setFont(new Font(15));
                text.setStyle("-fx-fill: #000000"); // set the color of the text to white
                boxInstruction  = new VBox();
                boxInstruction.getChildren().add(text);
                large_grid.getChildren().remove(gridPane);
                large_grid.getChildren().remove(boxTime);

                large_grid.add(boxInstruction, 0, 0);
                this.instructionToggle = true;
            } else {
                this.large_grid.getChildren().remove(boxTime);
                this.large_grid.getChildren().remove(boxInstruction);
                large_grid.add(gridPane, 0, 0);
                this.instructionToggle = false;
            }
        });

    }


    /*
    Clear the grid
     */
    public void addClearEvent() {
        clearButton.setOnAction(s -> {
            BaiscGame.clearGrid();
            final int size = gridPane.getChildren().size();
            for (int i = 0; i < size; i++) {
                final Button button1 = (Button) gridPane.getChildren().get(i);
                button1.setStyle("-fx-background-color: blue;");
            }

            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    BaiscGame.grid[i][j] = 0;
                }
            }
        });


    }


    /*
        Randomly generate a grid
     */

    public void addRandomEvent() {
        randomGenerate.setOnAction(s -> {
            BaiscGame.generateGrid();
            final int[][] grid = BaiscGame.grid;

            gridPane.setHgap(2);
            gridPane.setVgap(2);
            gridPane.getChildren().removeAll();
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    Button button = new Button();
                    button.setMinHeight(35);
                    button.setMinWidth(35);
                    button.setPrefSize(10, 10);
                    final int row = i;
                    final int col = j;
                    button.setOnAction(s1 -> {
                        Background background = button.getBackground();
                        final Color fillColor = (Color) background.getFills().get(0).getFill();
                        if (fillColor.equals(Color.PINK)) {
                            button.setStyle("-fx-background-color: blue;");
                            grid[row][col] = 0;
                            pink = false;
                        } else {
                            button.setStyle("-fx-background-color: pink;");
                            grid[row][col] = 1;
                            pink = true;
                        }
                    });
                    if (grid[i][j] == 1) {
                        button.setStyle("-fx-background-color: pink;");

                    } else {
                        button.setStyle("-fx-background-color: blue;");

                    }
                    gridPane.add(button, i, j);
                }
            }
        });


    }



    /*
        Add start and pause event
     */

    public void addStartPauseEvent(){
        start_or_pauseButton.setOnAction(s->{
            if (!started) {
                System.out.println("going to start");
                started = true;
                new Thread(() -> {
                    BaiscGame.start = true;
                    BaiscGame.startGame();
                }).start();


            }
            else {
                System.out.println("going to the start pause event else");
                started = false;
                BaiscGame.start = false;
                System.out.println("Base game start = flase");

            }
        });

    }






}
