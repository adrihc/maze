package com.liceu.maze.model;
public class Room {
    int roomId;
    Direction N,S,E,W;
    Item key,coin1,coin2,coin3;
    boolean isEnd = false;

    public Room(int roomId){
        this.roomId = roomId;
    }
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setN(Direction n) {
        N = n;
    }

    public void setS(Direction s) {
        S = s;
    }

    public void setE(Direction e) {
        E = e;
    }

    public void setW(Direction w) {
        W = w;
    }

    public void setKey(Item key) {
        this.key = key;
    }

    public void setCoin1(Item coin1) {
        this.coin1 = coin1;
    }

    public void setCoin2(Item coin2) {
        this.coin2 = coin2;
    }

    public void setCoin3(Item coin3) {
        this.coin3 = coin3;
    }

    public Direction getN() {
        return N;
    }

    public Direction getS() {
        return S;
    }

    public Direction getE() {
        return E;
    }

    public Direction getW() {
        return W;
    }

    public Item getKey() {
        return key;
    }

    public Item getCoin1() {
        return coin1;
    }

    public Item getCoin2() {
        return coin2;
    }

    public Item getCoin3() {
        return coin3;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }
}
