package com.example.w22comp1008lhvideogame;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Ship extends Sprite{
    private ArrayList<Missile> missiles;
    private int missilesRemaining;

    public Ship(int posX, int posY, int imageWidth, int imageHeight, int speed) {
        super(posX, posY, imageWidth, imageHeight, speed);
        image =  new Image(Main.class.getResource("images/ship.png").toExternalForm());
        missiles = new ArrayList<>();
        missilesRemaining = GameConfig.getInitialMissileCount();
    }

    /**
     * Move the ship down based on the ship's speed.  If the ship gets to the bottom
     * of the screen, stop moving down
     */
    public void moveDown()
    {
        int furthestDown = GameConfig.getGameHeight()-imageHeight;
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


}
