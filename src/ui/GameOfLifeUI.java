package ui;

import core.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import GUIComponents.*;

import java.util.Optional;


public class GameOfLifeUI extends Application {

    private Scene mainScene;
    private BorderPane mainScenePane;
    private ConwayGameOfLife game;
    private Text generation;
    private GameOfLifeGridPane board;

    public static void run (String[] args) {
        GameOfLifeUI gui = new GameOfLifeUI();
        gui.launch(args);
    }

    public void start(Stage primaryStage) {

        initGame();

        VBox headerPane = new VBox();
        Text applicationName = new Text("Conway's Game Of Life");
        applicationName.setTextAlignment(TextAlignment.CENTER);
        headerPane.getChildren().add(applicationName);
        headerPane.setAlignment(Pos.CENTER);

        HBox generationBox = new HBox();
        generation = new Text(Integer.toString(game.getGeneration()));
        generationBox.getChildren().addAll(new Text("Generation: "), generation);
        generationBox.setAlignment(Pos.CENTER);


        VBox interfacePane = new VBox();
        Button setCellAliveButton = new Button();
        setCellAliveButton.setText("Set cell to Alive");
        setCellAliveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setCellAlive();
            }
        });
        Button setCellDeadButton = new Button();
        setCellDeadButton.setText("Set cell to Dead");
        setCellDeadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setCellDead();
            }
        });
        Button stepButton = new Button();
        stepButton.setText("Step to next generation");
        stepButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                step();
            }
        });
        interfacePane.getChildren().add(setCellAliveButton);
        interfacePane.getChildren().add(setCellDeadButton);
        interfacePane.getChildren().add(stepButton);

        mainScenePane = new BorderPane();
        mainScene = new Scene(mainScenePane);
        mainScenePane.setCenter(board = new GameOfLifeGridPane(game.getGameBoard()));
        mainScenePane.setBottom(generationBox);
        mainScenePane.setTop(headerPane);
        mainScenePane.setRight(interfacePane);
        primaryStage.setTitle("Conway's Game of Life");
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(500);
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

    private void initGame () {
        game = new ConwayGameOfLife(getRowInput("Choose a row length"), getColumnInput("Choose a column length"));
    }

    private void setCellAlive () {
        game.initializeCell(getRowInput("Choose a row"), getColumnInput("Choose a column"));
        updateGameBoardGridPane();
    }

    private int getRowInput (String s) {
        TextInputDialog rowChoice = new TextInputDialog();
        rowChoice.setContentText(s);
        Optional<String> input = rowChoice.showAndWait();
        return Integer.parseInt(input.get());
    }

    private int getColumnInput (String s) {
        TextInputDialog columnChoice = new TextInputDialog();
        columnChoice.setContentText(s);
        Optional<String> input = columnChoice.showAndWait();
        return Integer.parseInt(input.get());
    }

    private void setCellDead () {
        game.setCellDead(getRowInput("Choose a row"), getColumnInput("Choose a column"));
        updateGameBoardGridPane();
    }

    private void step () {
        game.stepGeneration();
        board.update(game.getGameBoard());
    }

    private void updateGameBoardGridPane () {
        board.update(game.getGameBoard());
    }

    // testing method
    public static void main (String[] args) {
        launch(args);
    }
}
