package com.example.w22comp1008lhvideogame;

import com.example.w22comp1008lhvideogame.sprites.Alien;
import com.example.w22comp1008lhvideogame.sprites.Missile;
import com.example.w22comp1008lhvideogame.sprites.Ship;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

public class GameViewController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button startButton;

    private AnimationTimer timer;

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
        //This creates a "listener" that will add the key pressed to the set
        //The -> is called a "lambda expression", it is a short form of calling a
        //method and passing in a variable
        anchorPane.getScene().setOnKeyPressed(keyPressed -> {
            activeKeys.add(keyPressed.getCode());
        });

        anchorPane.getScene().setOnKeyReleased(keyReleased -> {
            activeKeys.remove(keyReleased.getCode());
        });

        startButton.setVisible(false);

        Canvas canvas = new Canvas(GameConfig.getGameWidth(), GameConfig.getGameHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //load the background
        Image background = new Image(getClass().getResource("images/space.png").toExternalForm());

        //load an image for our ship
        Ship ship = new Ship(100,100,100,60,5);

        ArrayList<Alien> aliens = new ArrayList<>();
        SecureRandom rng = new SecureRandom();

        //Add Alien's to the scene
        for (int i = 1; i<=5 ; i++)
        {
            aliens.add(new Alien(rng.nextInt(600,900),
                            rng.nextInt(30,GameConfig.getGameHeight()-80-GameConfig.getAlienHeight())));
        }

        //Add a timer to draw and update the Sprites
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gc.drawImage(background, 0, 0, GameConfig.getGameWidth(), GameConfig.getGameHeight());
                updateShipLocation(ship);

                ship.draw(gc);

                //This is a lamdba expression
                //it loops over the collection of aliens and calls the method isAlive() for each Alien
                //if the !alien.isAlive() evaluates to true, it removes the Alien from the collection
                aliens.removeIf(alien -> !alien.isAlive());

                //loop over the aliens, draw them and check for collisions
                for (Alien alien : aliens)
                {
                    alien.draw(gc);

                    for (Missile missile : ship.getActiveMissiles())
                    {
                        if (missile.collidesWith(alien))
                        {
                            //show an explosion
                            missile.setAlive(false);
                            alien.setAlive(false);
                        }
                    }

                    if (alien.collidesWith(ship))
                    {
                        finalMessage(gc, "The Aliens got you!!", Color.RED);
                        timer.stop();
                    }
                }

                updateStats(gc, aliens);

                if (aliens.size()==0)
                {
                    finalMessage(gc, "Congratulations - you saved the universe!", Color.WHITE);
                    timer.stop();
                }
            }
        };
        anchorPane.getChildren().add(canvas);
        timer.start();
    }

    private void updateStats(GraphicsContext gc, ArrayList<Alien> aliens)
    {
        //draw a black rectangle at the bottom of the screen
        gc.setFill(Color.BLACK);
        gc.fillRect(0,GameConfig.getGameHeight()-80, GameConfig.getGameWidth(), 80 );

        //draw how many aliens are remaining
        Font font = Font.font("Arial", FontWeight.NORMAL, 32);
        gc.setFont(font);
        gc.setFill(Color.WHITE);
        gc.fillText("Aliens remaining: " + aliens.size(), GameConfig.getGameWidth()-300, GameConfig.getGameHeight()-40);
    }

    /**
     * This method should be called when the game is over and you want to display information the user
     */
    private void finalMessage(GraphicsContext gc, String message, Color color)
    {
        Font font = Font.font("Arial", FontWeight.NORMAL, 32);
        gc.setFont(font);
        gc.setFill(color);
        gc.fillText(message, 250, 350);
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

