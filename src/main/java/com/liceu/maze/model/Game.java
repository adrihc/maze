package com.liceu.maze.model;

import com.liceu.maze.services.MazeGame;

public class Game {
    Maze maze;
    Player player;

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
