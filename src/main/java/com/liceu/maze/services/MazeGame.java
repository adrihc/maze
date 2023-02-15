package com.liceu.maze.services;

import com.liceu.maze.model.*;

public class MazeGame {

    public static Game createMaze(int i){
        if (i == 1){
            //Maze 1
            Maze maze = new Maze();
            Player player = new Player();
            player.setCurrentRoom(1);
            MazeBuilder mazeBuilder = new MazeBuilder();
            Wall wall = new Wall();

            //Crea habitaciones
            maze.addRoom(1,mazeBuilder.buildRoom(1,wall,wall,wall,wall));
            maze.addRoom(2,mazeBuilder.buildRoom(1,wall,wall,wall,wall));
            maze.addRoom(3,mazeBuilder.buildRoom(1,wall,wall,wall,wall));
            maze.addRoom(4,mazeBuilder.buildRoom(1,wall,wall,wall,wall));
            maze.addRoom(5,mazeBuilder.buildRoom(1,wall,wall,wall,wall));


            //Crea items
            Key key = new Key(1,1);
            Coin coin = new Coin();

            //Crea puertas
            mazeBuilder.buildDoor(1,3,1,false,"N", maze);
            mazeBuilder.buildDoor(1,2,1,true,"E", maze);
            mazeBuilder.buildDoor(4,3,1,true,"W", maze);
            mazeBuilder.buildDoor(4,2,1,true,"S", maze);
            mazeBuilder.buildDoor(1,5,1,true,"W", maze);

            //Coge la habitacion actual
            Room actualRoom = maze.getRoom(player.getCurrentRoom());
            //Inserta items
            mazeBuilder.insertKey(1,key,maze);
            mazeBuilder.insertCoin(2,1,coin, maze);
            mazeBuilder.insertCoin(3,2,coin, maze);
            mazeBuilder.insertCoin(4,3,coin, maze);

            //Set end
            mazeBuilder.setEnd(maze,5);

            player.addItem(key);

            Game game = new Game();
            game.setMaze(maze);
            game.setPlayer(player);
            return game;
        } else if (i == 2) {
            //Maze 2
            Maze maze = new Maze();
            Player player = new Player();
            player.setCurrentRoom(1);
            MazeBuilder mazeBuilder = new MazeBuilder();
            Wall wall = new Wall();

            //Crea habitaciones
            for (int j = 1; j <= 7; j++) {
                maze.addRoom(j,mazeBuilder.buildRoom(j,wall,wall,wall,wall));
            }


            //Crea items
            Key key1 = new Key(3,1);
            Key key2 = new Key(4,2);
            Key key3 = new Key(5,3);
            Key key4 = new Key(6,4);
            Coin coin = new Coin();

            //Crea puertas
            mazeBuilder.buildDoor(1,2,1,true,"S", maze);
            mazeBuilder.buildDoor(1,3,2,true,"E", maze);
            mazeBuilder.buildDoor(3,4,3,false,"N", maze);
            mazeBuilder.buildDoor(4,5,4,false,"E", maze);
            mazeBuilder.buildDoor(4,6,5,false,"N", maze);
            mazeBuilder.buildDoor(1,7,6,false,"W", maze);

            //Coge la habitacion actual
            Room actualRoom = maze.getRoom(player.getCurrentRoom());

            //Inserta items
            mazeBuilder.insertKey(2,key1,maze);
            mazeBuilder.insertKey(3,key2,maze);
            mazeBuilder.insertKey(5,key3,maze);
            mazeBuilder.insertKey(6,key4,maze);

            mazeBuilder.insertCoin(2,1,coin, maze);
            mazeBuilder.insertCoin(3,2,coin, maze);
            mazeBuilder.insertCoin(4,3,coin, maze);
            mazeBuilder.insertCoin(5,3,coin, maze);

            //Afegir claus a l'inventari en fals
            player.addItem(key1);
            player.addItem(key2);
            player.addItem(key3);
            player.addItem(key4);

            //Crear el final
            mazeBuilder.setEnd(maze,7);

            Game game = new Game();
            game.setMaze(maze);
            game.setPlayer(player);
            return game;
        } else if (i == 3) {
            //Aquí se crea todo el laberinto
            Maze maze = new Maze();
            Player player = new Player();
            player.setCurrentRoom(1);
            MazeBuilder mazeBuilder = new MazeBuilder();
            Wall wall = new Wall();

            //Crea habitaciones
            for (int j = 1; j <= 9; j++) {
                maze.addRoom(j,mazeBuilder.buildRoom(j,wall,wall,wall,wall));
            }

            //Crea items
            Key key2 = new Key(2,1);
            Key key4 = new Key(4,2);
            Key key6 = new Key(6,3);
            Key key8 = new Key(8,4);
            Key key9 = new Key(9,5);
            Coin coin = new Coin();

            //Crea puertas
            mazeBuilder.buildDoor(1,2,1,true,"E", maze);
            mazeBuilder.buildDoor(2,3,2,false,"S", maze);
            mazeBuilder.buildDoor(3,4,3,true,"S", maze);
            mazeBuilder.buildDoor(4,5,4,false,"W", maze);
            mazeBuilder.buildDoor(5,6,5,true,"W", maze);
            mazeBuilder.buildDoor(6,7,6,false,"N", maze);
            mazeBuilder.buildDoor(7,8,7,true,"N", maze);
            mazeBuilder.buildDoor(8,1,8,false,"E", maze);
            mazeBuilder.buildDoor(1,9,9,false,"N", maze);

            //Crear el final
            mazeBuilder.setEnd(maze,9);

            //Coge la habitacion actual
            Room actualRoom = maze.getRoom(player.getCurrentRoom());
            //Inserta items
            mazeBuilder.insertKey(1,key2,maze);
            mazeBuilder.insertKey(3,key4,maze);
            mazeBuilder.insertKey(6,key6,maze);
            mazeBuilder.insertKey(7,key8,maze);
            mazeBuilder.insertKey(2,key9,maze);

            mazeBuilder.insertCoin(1,2,coin, maze);
            mazeBuilder.insertCoin(4,3,coin, maze);
            mazeBuilder.insertCoin(6,1,coin, maze);
            mazeBuilder.insertCoin(8,3,coin, maze);
            mazeBuilder.insertCoin(7,1,coin, maze);


            player.addItem(key2);
            player.addItem(key4);
            player.addItem(key6);
            player.addItem(key8);
            player.addItem(key9);


            Game game = new Game();
            game.setMaze(maze);
            game.setPlayer(player);
            return game;
        }
        return null;
    }

}
