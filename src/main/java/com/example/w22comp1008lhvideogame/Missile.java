package com.example.w22comp1008lhvideogame;

import javafx.scene.image.Image;

public class Missile extends Sprite {

    /**
     * This constructor creates a missile object, which is also a Sprite object.  Because
     * we are creating a Sprite object, we have to call the constructor for the Sprite class.
     * The line "super(image, posX, posY,...);" is calling the super class constructor.
     * @param posX
     * @param posY
     * @param imageWidth
     * @param imageHeight
     * @param speed
     */
    public Missile(int posX, int posY, int imageWidth, int imageHeight, int speed) {
        super(posX, posY, imageWidth, imageHeight, speed);
        image = new Image(Main.class.getResource("images/missile.png").toExternalForm());
    }
}
