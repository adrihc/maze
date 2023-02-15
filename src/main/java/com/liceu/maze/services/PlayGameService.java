package com.liceu.maze.services;

import com.liceu.maze.model.*;

import java.util.ArrayList;
import java.util.List;

public class PlayGameService {
    public Game addCoin(int coin, Game game){
        //Get player and add coin
        Player player = game.getPlayer();


        //Get Room and delete coin
        Maze maze = game.getMaze();
        Room room = maze.getRoom(player.getCurrentRoom());
        if (coin == 1 && room.getCoin1() != null){
            player.addCoins(1);
            room.setCoin1(null);
        } else if (coin == 2 && room.getCoin2() != null) {
            room.setCoin2(null);
            player.addCoins(1);
        } else if (coin == 3 && room.getCoin3() != null){
            room.setCoin3(null);
            player.addCoins(1);
        }
        maze.addRoom(player.getCurrentRoom(), room);

        //Set game again
        game.setMaze(maze);
        game.setPlayer(player);
        return game;
    }

    public Game addKey(Game game){
        Player player = game.getPlayer();
        Maze maze = game.getMaze();
        Room room = maze.getRoom(player.getCurrentRoom());
        Key key = (Key) room.getKey();
        List<Key> inventory = player.getInventory();
        if (room.getKey() != null && player.getActualCoins() >= key.getMinimumCoin() ) {
            int contador = 0;
            for (Key keyInventory: inventory) {
                if (key.getId() == keyInventory.getId()){
                keyInventory.setObtained(true);
                inventory.set(contador, keyInventory);
                }
                contador++;
            }
            player.setInventory(inventory);
            player.addKeys(1);
            room.setKey(null);
        } else {
            System.out.println("No se pot agafar");
        }
        //Get Room and delete key

        maze.addRoom(player.getCurrentRoom(), room);
        //Get player and add key

        //Set game again
        game.setMaze(maze);
        game.setPlayer(player);
        return game;
    }
    public Game moveTo(Game game, String direction){
        Player player = game.getPlayer();
        Maze maze = game.getMaze();
        Room room = maze.getRoom(player.getCurrentRoom());
        if (direction.equals("N")){
            Direction N = room.getN();
            if (N.getDirection() == "door"){
                Door nDir = (Door) N;
                if (nDir.isOpen() == true) {
                    int roomTo = nDir.getRoom2();
                    player.setCurrentRoom(roomTo);
                }
            }
        }
        if (direction.equals("S")){
            Direction S = room.getS();
            if (S.getDirection() == "door"){
                Door sDir = (Door) S;
                if (sDir.isOpen()) {
                    int roomTo = sDir.getRoom2();
                    player.setCurrentRoom(roomTo);
                }
            }
        }
        if (direction.equals("E")){
            Direction E = room.getE();
            if (E.getDirection() == "door"){
                Door eDir = (Door) E;
                if (eDir.isOpen()) {
                    int roomTo = eDir.getRoom2();
                    player.setCurrentRoom(roomTo);
                }
            }
        }
        if (direction.equals("W")){
            Direction W = room.getW();
            if (W.getDirection() == "door"){
                Door wDir = (Door) W;
                if (wDir.isOpen()) {
                    int roomTo = wDir.getRoom2();
                    player.setCurrentRoom(roomTo);
                }
            }
        }
        game.setPlayer(player);
        return game;
    }

    public Game openDoor(Game game, String direction) {
        Player player = game.getPlayer();
        Maze maze = game.getMaze();
        Room room = maze.getRoom(player.getCurrentRoom());
        List<Key> inventory = player.getInventory();
        Direction N = room.getN();
        Direction S = room.getS();
        Direction E = room.getE();
        Direction W = room.getW();

        //North
        if (direction.equals("N") && N.getDirection().equals("door") ){
            for (Key key: inventory) {
                if (key.isObtained()){
                    Door door = (Door) N;
                    door.open(key.getId());
                    int id1 = door.getRoom1();
                    int id2 = door.getRoom2();
                    Room room1 = maze.getRoom(id1);
                    Room room2 = maze.getRoom(id2);
                    Door door2 = (Door) room2.getS();
                    door2.open(key.getId());
                    maze.replaceRoom(id1, room1);
                    maze.replaceRoom(id2, room2);
                }
            }
            //South
        } else if (direction.equals("S") && S.getDirection().equals("door") ){
            for (Key key: inventory) {
                if (key.isObtained()){
                    Door door = (Door) S;
                    door.open(key.getId());
                    int id1 = door.getRoom1();
                    int id2 = door.getRoom2();
                    Room room1 = maze.getRoom(id1);
                    Room room2 = maze.getRoom(id2);
                    Door door2 = (Door) room2.getN();
                    door2.open(key.getId());
                    maze.replaceRoom(id1, room1);
                    maze.replaceRoom(id2, room2);
                }
            }
            //West
        } else if (direction.equals("W") && W.getDirection().equals("door") ){
            for (Key key: inventory) {
                if (key.isObtained()){
                    Door door = (Door) W;
                    door.open(key.getId());
                    int id1 = door.getRoom1();
                    int id2 = door.getRoom2();
                    Room room1 = maze.getRoom(id1);
                    Room room2 = maze.getRoom(id2);
                    Door door2 = (Door) room2.getE();
                    door2.open(key.getId());
                    maze.replaceRoom(id1, room1);
                    maze.replaceRoom(id2, room2);
                }
            }
            //East
        } else if (direction.equals("E") && E.getDirection().equals("door") ){
            for (Key key: inventory) {
                if (key.isObtained()){
                    Door door = (Door) E;
                    door.open(key.getId());
                    int id1 = door.getRoom1();
                    int id2 = door.getRoom2();
                    Room room1 = maze.getRoom(id1);
                    Room room2 = maze.getRoom(id2);
                    Door door2 = (Door) room2.getW();
                    door2.open(key.getId());
                    maze.replaceRoom(id1, room1);
                    maze.replaceRoom(id2, room2);
                }
            }
        }
        game.setPlayer(player);
        game.setMaze(maze);
        return game;
        }
    }
