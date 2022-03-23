package com.example.w22comp1008lhvideogame.sprites;

import com.example.w22comp1008lhvideogame.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Explosion extends Sprite{
    private final int REFRESH_RATE = 5;
    private int currentCount;
    private int explosionPosition;
    private int[] spriteStartX;

    /**
     * This is the constructor for the Explosion class
     *
     * @param posX        - left most position of the sprite
     * @param posY        - top most position of the sprite
     * @param imageWidth  - how wide the sprite will be when drawn
     * @param imageHeight - how tall the sprite will be when drawn
     */
    public Explosion(int posX, int posY, int imageWidth, int imageHeight) {
        super(posX, posY, imageWidth, imageHeight, 0);
        image = new Image(Main.class.getResource("images/fullExplosion2.png").toExternalForm());
        explosionPosition = 0;
        spriteStartX = new int[]{0, 170, 330, 520, 710};
        currentCount = REFRESH_RATE;
    }

    @Override
    public void draw(GraphicsContext gc)
    {
        currentCount--;
        if (currentCount < 0)
        {
            explosionPosition++;
            currentCount = REFRESH_RATE;
        }

        //check if the explosion should be drawn otherwise set the "isAlive" parameter to be false
        if (explosionPosition==spriteStartX.length)
        {
            setAlive(false);
        }
        else
        {
            //sx = source starting x position, sy = source starting y position (the x,y points in the image)
            //dx = destination x position (where it should be drawn on the canvas)
            //image, sx, sy, sw, sh, dx, dy, dw, dh
            gc.drawImage(image, spriteStartX[explosionPosition], 0,184,368,posX, posY, imageWidth, imageHeight );
        }
    }
}
