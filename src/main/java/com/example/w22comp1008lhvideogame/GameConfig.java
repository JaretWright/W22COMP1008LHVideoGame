package com.example.w22comp1008lhvideogame;

public class GameConfig {
    private static int gameHeight = 800;
    private static int gameWidth = 1000;

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
}
