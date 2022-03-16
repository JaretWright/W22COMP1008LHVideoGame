package com.example.w22comp1008lhvideogame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Missile extends Sprite {

    /**
     * This constructor creates a missile object, which is also a Sprite object.  Because
     * we are creating a Sprite object, we have to call the constructor for the Sprite class.
     * The line "super(image, posX, posY,...);" is calling the super class constructor.
     * @param posX
     * @param posY
     */
    public Missile(int posX, int posY) {
        super(posX, posY, GameConfig.getMissileWidth(), GameConfig.getMissileHeight(), GameConfig.getMissileSpeed());
        image = new Image(Main.class.getResource("images/missile.png").toExternalForm());
    }

    /**
     * Missiles can only move to the right, so if they receive a command to move up, down or left, ignore the
     * request
     */
    public void moveUp() { }
    public void moveDown() { }
    public void moveLeft() { }

    @Override
    public void moveRight() {
        if (getPosX()>GameConfig.getGameWidth())
            setAlive(false);
        else
            setPosX( getPosX()+getSpeed());
    }

    public void draw(GraphicsContext gc)
    {
        if (isAlive())
        {
            gc.drawImage(image,posX,posY,imageWidth,imageHeight);
            moveRight();
        }
    }
}
