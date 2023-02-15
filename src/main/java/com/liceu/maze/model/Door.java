package com.liceu.maze.model;
public class Door implements Direction{
    private int room1, room2;
    String direction = "door";
    int idDoor;
    boolean open= false;

    public Door(int room1, int room2, int idDoor, boolean isOpen){
        this.room1 = room1;
        this.room2 = room2;
        this.idDoor = idDoor;
        this.open = isOpen;
    }
    public void open(int idKeys){
        if (idKeys == idDoor){
            this.open = true;
        } else {
            System.out.println("No se puede abrir");
        }
    }
    public boolean isOpen(){
        return this.open;
    }

    public int getRoom1() {
        return room1;
    }

    public int getRoom2() {
        return room2;
    }

    public String getDirection(){
        return direction;
    }

    public int getIdDoor() {
        return idDoor;
    }

    public void setIdDoor(int idDoor) {
        this.idDoor = idDoor;
    }
}
