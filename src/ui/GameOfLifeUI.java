package ui;

import core.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
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
        interfacePane.getChildren().addAll(setCellAliveButton, setCellDeadButton, stepButton);

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
        try {
            game = new ConwayGameOfLife(getUserInput("Choose a row length"), getUserInput("Choose a column length"));
        } catch (Exception e) {
            showAlert("An input was invalid....");
            initGame();
        }
    }

    private void setCellAlive () {
        try {
            game.initializeCell(getUserInput("Choose a row"), getUserInput("Choose a column"));
        } catch (Exception e) {
            showAlert("An input was invalid......");
            setCellAlive();
        }
        updateGameBoardGridPane();
    }

    private int getUserInput (String s) throws Exception {
        TextInputDialog columnChoice = new TextInputDialog();
        columnChoice.setContentText(s);
        Optional<String> input = columnChoice.showAndWait();
        return Integer.parseInt(input.get());
    }

    private void setCellDead () {
        try {
            game.setCellDead(getUserInput("Choose a row"), getUserInput("Choose a column"));
        } catch (Exception e) {
            showAlert("An input was invalid......");
            setCellAlive();
        }
        updateGameBoardGridPane();
    }

    private void step () {
        game.stepGeneration();
        board.update(game.getGameBoard());
    }

    private void updateGameBoardGridPane () {
        board.update(game.getGameBoard());
    }

    private void showAlert (String s) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(s);
        a.showAndWait();
    }

}
