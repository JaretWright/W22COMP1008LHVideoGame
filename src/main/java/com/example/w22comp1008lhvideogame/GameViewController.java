package com.example.w22comp1008lhvideogame;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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

        //load the background
        Image background = new Image(getClass().getResource("images/space.png").toExternalForm());

        //load an image for our ship
        Image shipImage = new Image(getClass().getResource("images/ship.png").toExternalForm());
        Sprite ship = new Sprite(shipImage,100,100,100,60,5);

        //Add a timer to draw and update the Sprites
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gc.drawImage(background, 0, 0, GameConfig.getGameWidth(), GameConfig.getGameHeight());
                ship.draw(gc);
                ship.moveRight();
            }
        };
        timer.start();


    }

}

