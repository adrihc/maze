package com.liceu.maze.services;

import com.liceu.maze.model.*;
import org.json.simple.JSONObject;

public class TextService {
    public  String getJsonInfo(Game game) {
        Maze maze = game.getMaze();
        Player playerMaze = game.getPlayer();
        Room actualRoom = maze.getRoom(playerMaze.getCurrentRoom());
        JSONObject root = new JSONObject();

        if (actualRoom.isEnd()){
            root.put("end","end");
        }
        JSONObject walls = new JSONObject();
        Direction n = actualRoom.getN();
        Direction s = actualRoom.getS();
        Direction w = actualRoom.getW();
        Direction e = actualRoom.getE();
        //North
        if (n.getDirection() =="door") {
            Door nDoor = (Door) n;
            if (nDoor.isOpen()){
                walls.put("N", "");
            } else {
                JSONObject door = new JSONObject();
                door.put("door","door");
                door.put("id", nDoor.getIdDoor());
                walls.put("N", door);
            }
        } else {
            walls.put("N", "wall");
        }
        //South
        if (s.getDirection() =="door") {
            Door sDoor = (Door) s;
            if (sDoor.isOpen()){
                walls.put("S", "");
            } else {
                JSONObject door = new JSONObject();
                door.put("door","door");
                door.put("id", sDoor.getIdDoor());
                walls.put("S", door);

            }
        } else {
            walls.put("S", "wall");
        }
        //East
        if (e.getDirection() =="door") {
            Door eDoor = (Door) e;
            if (eDoor.isOpen()){
                walls.put("E", "");
            } else {
                JSONObject door = new JSONObject();
                door.put("door","door");
                door.put("id", eDoor.getIdDoor());
                walls.put("E", door);
            }
        } else {
            walls.put("E", "wall");
        }
        //West
        if (w.getDirection() =="door") {
            Door wDoor = (Door) w;
            if (wDoor.isOpen()){
                walls.put("W", "");
            } else {
                JSONObject door = new JSONObject();
                door.put("door","door");
                door.put("id", wDoor.getIdDoor());
                walls.put("W", door);

            }
        } else {
            walls.put("W", "wall");
        }
        root.put("walls", walls);

        JSONObject door = new JSONObject();

        //Items
        JSONObject items = new JSONObject();
        Key key = (Key) actualRoom.getKey();
        Coin coin1 = (Coin) actualRoom.getCoin1();
        Coin coin2 = (Coin) actualRoom.getCoin2();
        Coin coin3 = (Coin) actualRoom.getCoin3();

        if (key != null){
            items.put("key","true");
            items.put("coinsNeeded", key.getMinimumCoin());
        } else {
            items.put("key","false");

        }
        if (coin1 != null){
            items.put("coin1","true");
        } else {
            items.put("coin1", "false");
        }
        if (coin2 != null){
            items.put("coin2","true");
        } else {
            items.put("coin2", "false");
        }
        if (coin3 != null){
            items.put("coin3","true");
        } else {
            items.put("coin3", "false");
        }

        root.put("items",items);

        //Player
        JSONObject player = new JSONObject();
        player.put("coins", playerMaze.getActualCoins());
        player.put("keys", playerMaze.getActualAmountKeys());
        player.put("coorX", playerMaze.getCoordenadaX());
        player.put("coorY", playerMaze.getCoordenadaY());
        player.put("room", playerMaze.getCurrentRoom());
        JSONObject inventory = new JSONObject();
        int contador = 1;
        for (Key keyInventory: playerMaze.getInventory()) {
            inventory.put("key" + contador,keyInventory.isObtained());
            contador++;
        }
        player.put("inventory", inventory);

        root.put("player", player);

        return root.toJSONString();
    }
}
