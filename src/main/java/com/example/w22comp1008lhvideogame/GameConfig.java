package com.example.w22comp1008lhvideogame;

public class GameConfig {
    private static int gameHeight = 800;
    private static int gameWidth = 1000;
    private static int maxSpriteHeight = 100;
    private static int maxSpriteWidth = 150;
    private static int initialMissileCount = 30;
    private static int missileWidth = 50;
    private static int missileHeight = 30;
    private static int missileSpeed = 9;

    public static int getMissileWidth() {
        return missileWidth;
    }

    public static void setMissileWidth(int missileWidth) {
        GameConfig.missileWidth = missileWidth;
    }

    public static int getMissileHeight() {
        return missileHeight;
    }

    public static void setMissileHeight(int missileHeight) {
        GameConfig.missileHeight = missileHeight;
    }

    public static int getMissileSpeed() {
        return missileSpeed;
    }

    public static void setMissileSpeed(int missileSpeed) {
        GameConfig.missileSpeed = missileSpeed;
    }

    public static int getInitialMissileCount() {
        return initialMissileCount;
    }

    public static void setInitialMissileCount(int initialMissileCount) {
        if (initialMissileCount>0 && initialMissileCount<=50)
            GameConfig.initialMissileCount = initialMissileCount;
        else
            throw new IllegalArgumentException("initial missile count must be 1-50");
    }

    public static int getGameHeight() {
        return gameHeight;
    }

    public static void setGameHeight(int gameHeight) {
        GameConfig.gameHeight = gameHeight;
    }

    public static int getGameWidth() {
        return gameWidth;
    }

    public static void setGameWidth(int gameWidth) {
        GameConfig.gameWidth = gameWidth;
    }

    public static int getMaxSpriteHeight() {
        return maxSpriteHeight;
    }

    public static void setMaxSpriteHeight(int maxSpriteHeight) {
        GameConfig.maxSpriteHeight = maxSpriteHeight;
    }

    public static int getMaxSpriteWidth() {
        return maxSpriteWidth;
    }

    public static void setMaxSpriteWidth(int maxSpriteWidth) {
        GameConfig.maxSpriteWidth = maxSpriteWidth;
    }
}
