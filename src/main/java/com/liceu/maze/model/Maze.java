package com.liceu.maze.model;

import java.util.HashMap;
import java.util.Map;

public class Maze {
    Map<Integer, Room> map = new HashMap<>();

    public void addRoom(int idRoom, Room room){
        this.map.put(idRoom, room);
    }
    public void replaceRoom(int idRoom, Room room){
        this.map.replace(idRoom,room);
    }
    public Room getRoom(int id){
        return this.map.get(id);
    }

    public Map<Integer, Room> getMap() {
        return map;
    }
}
