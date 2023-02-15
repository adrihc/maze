package com.liceu.maze.model;
public class Key implements Item{
    //Obtained és false perque s'afegeixen totes les claus a l'inventari i si trobes una clau es torna true
    boolean obtained = false;
    int id;
    int minimumCoin;

    String itemString = "key";
    public Key(int id, int minimumCoin){
        this.id = id;
        this.minimumCoin = minimumCoin;
    }
    public boolean isObtained() {
        return obtained;
    }

    public void setObtained(boolean obtained) {
        this.obtained = obtained;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMinimumCoin(int minimumCoin) {
        this.minimumCoin = minimumCoin;
    }

    public String getItemString(){
        return itemString;
    }

    public int getMinimumCoin() {
        return minimumCoin;
    }
}
