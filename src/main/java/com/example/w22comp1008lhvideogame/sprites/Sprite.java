package com.example.w22comp1008lhvideogame.sprites;

import com.example.w22comp1008lhvideogame.GameConfig;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite {
    protected Image image;
    protected int posX, posY, imageWidth, imageHeight, speed;
    private boolean alive;

    public Sprite(int posX, int posY, int imageWidth, int imageHeight, int speed) {
        setPosX(posX);
        setPosY(posY);
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
        setSpeed(speed);
        alive = true;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        int furthestRight = GameConfig.getGameWidth()+300;
        if (posX>=0 && posX <= furthestRight)
            this.posX = posX;
        else
            throw new IllegalArgumentException("posX must be in the range of 0-"+furthestRight);
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        int furthestDown = GameConfig.getGameHeight()-imageHeight;
        if (posY >= 0 && posY <= furthestDown)
            this.posY = posY;
        else
            throw new IllegalArgumentException("posY must be in the range of 0-"+furthestDown);
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        if (imageWidth >= 10 && imageWidth <= GameConfig.getMaxSpriteWidth())
            this.imageWidth = imageWidth;
        else
            throw new IllegalArgumentException("imageWidth must be in the range 10-"+GameConfig.getMaxSpriteWidth());
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        if (imageHeight >= 10 && imageHeight <= GameConfig.getMaxSpriteHeight())
            this.imageHeight = imageHeight;
        else
            throw new IllegalArgumentException("imageHeight must in the range of 10-"+GameConfig.getMaxSpriteHeight());
    }

    public int getSpeed() {
        return speed;
    }

    /**
     * How many pixels should the sprite move each time it is drawn
     * @param speed
     */
    public void setSpeed(int speed) {
        if (speed >= 0 && speed <= 10)
            this.speed = speed;
        else
            throw new IllegalArgumentException("speed must be in the range of 0-10");
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * posX is the left most part of the image
     * posY is the top of the image
     * imageWidth and imageHeight reflect the size in pixels
     * @param gc
     */
    public void draw(GraphicsContext gc)
    {
        gc.drawImage(image,posX,posY,imageWidth,imageHeight);
    }

    public void moveRight()
    {
        posX = posX+speed;

        if (posX >= GameConfig.getGameWidth()-imageWidth)
            posX = GameConfig.getGameWidth()-imageWidth;
    }

    /**
     * This method returns true if 2 sprite's collide
     */
    public boolean collidesWith(Sprite sprite)
    {
        return ((posX + imageWidth/2 > sprite.posX) && (posX < sprite.posX + sprite.imageWidth/2) &&
                (posY + imageHeight/2 > sprite.posY) && (posY < sprite.posY + sprite.imageHeight/2));
    }
}
