package com.liceu.maze.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    int coordenadaX;
    int coordenadaY;
    private int currentRoom;
    private List<Key> inventory = new ArrayList<>();

    int actualCoins = 0;
    int actualAmountKeys = 0;
    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void addItem(Key newItem){ this.inventory.add(newItem);};

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(int coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public int getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(int coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public List<Key> getInventory() {
        return inventory;
    }
    public int getCurrentRoom() {
        return this.currentRoom;
    }

    public int getActualCoins() {
        return actualCoins;
    }

    public void addCoins(int amountOfCoins) {
        this.actualCoins += amountOfCoins;
    }

    public int getActualAmountKeys() {
        return actualAmountKeys;
    }

    public void addKeys(int add) {
        this.actualAmountKeys += add;
    }

    public void setInventory(List<Key> inventory) {
        this.inventory = inventory;
    }
}
