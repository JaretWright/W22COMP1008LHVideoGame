package com.example.w22comp1008lhvideogame;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class GameViewController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button startButton;

    /**
     * Set is a data structure that prevents duplicates, otherwise it is somewhat similar
     * to other data structures like an ArrayList
     *
     * KeyCode - is the character pressed on the keyboard
     */
    private HashSet<KeyCode> activeKeys;

    @FXML
    private void startGame(ActionEvent event)
    {
        Canvas canvas = new Canvas(GameConfig.getGameWidth(), GameConfig.getGameHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        anchorPane.getChildren().add(canvas);


        //This creates a "listener" that will add the key pressed to the set
        //The -> is called a "lambda expression", it is a short form of calling a
        //method and passing in a variable
        anchorPane.getScene().setOnKeyPressed(keyPressed -> {
            System.out.println("Keys currently pressed -> "+activeKeys);
            activeKeys.add(keyPressed.getCode());
        });

        anchorPane.getScene().setOnKeyReleased(keyReleased -> {
            activeKeys.remove(keyReleased.getCode());
        });

        //load the background
        Image background = new Image(getClass().getResource("images/space.png").toExternalForm());

        //load an image for our ship
        Ship ship = new Ship(100,100,100,60,5);

        //Add a timer to draw and update the Sprites
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gc.drawImage(background, 0, 0, GameConfig.getGameWidth(), GameConfig.getGameHeight());
                updateShipLocation(ship);
                ship.draw(gc);
            }
        };
        timer.start();
    }

    private void updateShipLocation(Ship ship)
    {
        if (activeKeys.contains(KeyCode.LEFT))
            ship.moveLeft();
        if (activeKeys.contains(KeyCode.RIGHT))
            ship.moveRight();
        if (activeKeys.contains(KeyCode.UP))
            ship.moveUp();
        if (activeKeys.contains(KeyCode.DOWN))
            ship.moveDown();
        if (activeKeys.contains(KeyCode.SPACE))
            ship.shootMissile();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialize the set to hold the keycode pressed by the user
        activeKeys = new HashSet<>();
    }
}

