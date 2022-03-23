package com.example.w22comp1008lhvideogame.sprites;

import com.example.w22comp1008lhvideogame.GameConfig;
import com.example.w22comp1008lhvideogame.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Ship extends Sprite {
    private ArrayList<Missile> activeMissiles;
    private int missilesRemaining;
    private final int REFRESH_RATE = 20;
    private int missilePause;

    public Ship(int posX, int posY, int imageWidth, int imageHeight, int speed) {
        super(posX, posY, imageWidth, imageHeight, speed);
        image =  new Image(Main.class.getResource("images/ship.png").toExternalForm());
        activeMissiles = new ArrayList<>();
        missilesRemaining = GameConfig.getInitialMissileCount();
        missilePause = REFRESH_RATE;
    }

    public ArrayList<Missile> getActiveMissiles() {
        return activeMissiles;
    }

    /**
     * This method will shoot a missile from the current position of the ship
     * Once a ship runs out of activeMissiles, it will need to wait for a reload
     */
    public void shootMissile()
    {
        if (missilePause <0)
        {
            missilesRemaining--;
            activeMissiles.add(new Missile(posX+imageWidth,posY+(imageHeight/2-GameConfig.getMissileHeight()/2)));
            missilePause = REFRESH_RATE;
        }
    }

    /**
     * Move the ship down based on the ship's speed.  If the ship gets to the bottom
     * of the screen, stop moving down
     */
    public void moveDown()
    {
        //-80 is for the bottom score section, we do not want the ship drawn behind that
        int furthestDown = GameConfig.getGameHeight()-imageHeight-80;
        if (posY >= furthestDown)
            this.posY = furthestDown;
        else
            posY += speed;
    }

    /**
     * move the ship up based on it's speed until it reaches the highest point the
     * ship can go and still be drawn on the screen
     */
    public void moveUp()
    {
        if (posY < 0)
            this.posY = 0;
        else
            posY -= speed;
    }

    /**
     * When moving to the left, the furthest the ship can go is 0
     */
    public void moveLeft()
    {
        posX = posX - speed;
        if (posX <0)
            posX = 0;
    }

    public void draw(GraphicsContext gc)
    {
        missilePause--;

        //draw the ship
        super.draw(gc);

        //remove the missiles that have hit an alien or gone off the screen
        activeMissiles.removeIf(missile -> !missile.isAlive());

        //loop over all the active missiles and draw them on the canvas
        for (Missile missile : activeMissiles)
        {
            missile.draw(gc);
        }
    }
}
