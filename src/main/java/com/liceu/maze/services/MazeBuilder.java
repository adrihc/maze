package com.liceu.maze.services;

import com.liceu.maze.model.*;

import java.util.ArrayList;
import java.util.Optional;

public class MazeBuilder {

    public Room buildRoom(int idRoom, Direction N, Direction S, Direction W, Direction E){
        Room room = new Room(idRoom);
        room.setN(N);
        room.setS(S);
        room.setW(W);
        room.setE(E);
        return room;
    }
    public void buildDoor(int idActualRoom, int nextId,int idDoor, boolean isOpen, String direction, Maze maze){
        Door door = new Door(idActualRoom,nextId,idDoor, isOpen);
        Door inversaDoor = new Door(nextId,idActualRoom,idDoor,isOpen);

        if (direction == "N"){
            maze.getRoom(idActualRoom).setN(door);
            maze.getRoom(nextId).setS(inversaDoor);
        } else if (direction == "S"){
            maze.getRoom(idActualRoom).setS(door);
            maze.getRoom(nextId).setN(inversaDoor);
        }else if (direction == "E"){
            maze.getRoom(idActualRoom).setE(door);
            maze.getRoom(nextId).setW(inversaDoor);
        } else if (direction == "W") {
            maze.getRoom(idActualRoom).setW(door);
            maze.getRoom(nextId).setE(inversaDoor);
        }
    }
    public void insertKey(int idRoom, Key key, Maze maze){
        Room room = maze.getRoom(idRoom);
        room.setKey(key);
    }
    public void insertCoin(int idRoom,int position, Coin coin, Maze maze){
        Room room = maze.getRoom(idRoom);
        if (position == 1){
            room.setCoin1(coin);
        } else if (position == 2){
            room.setCoin2(coin);
        } else if (position == 3){
            room.setCoin3(coin);
        }
    }
    public void setEnd(Maze maze, int id){
        Room room = maze.getRoom(id);
        room.setEnd(true);
    }
}
