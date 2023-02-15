package com.liceu.maze.DAO;

import com.liceu.maze.model.Winner;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MazeDaoInterface{
    void newWinner(String user, int mapid, long puntuacion) throws SQLException;
    List<Winner> returnWinners();
}
