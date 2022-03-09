package com.example.w22comp1008lhvideogame;

public class GameConfig {
    private static int gameHeight = 800;
    private static int gameWidth = 1000;
    private static int maxSpriteHeight = 100;
    private static int maxSpriteWidth = 150;

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
