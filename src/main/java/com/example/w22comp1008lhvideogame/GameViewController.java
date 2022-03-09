package com.example.w22comp1008lhvideogame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class GameViewController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button startButton;

    @FXML
    private void startGame(ActionEvent event)
    {
        Canvas canvas = new Canvas(GameConfig.getGameWidth(), GameConfig.getGameHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        anchorPane.getChildren().add(canvas);
    }

}

