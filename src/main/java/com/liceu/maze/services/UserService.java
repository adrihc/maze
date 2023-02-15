package com.liceu.maze.services;

import com.liceu.maze.DAO.MazeDAO;
import com.liceu.maze.DAO.MazeDaoInterface;

import java.sql.SQLException;

public class UserService {
    MazeDaoInterface mz = new MazeDAO();
    public void newWinner(String username, int mapid, long result) throws SQLException {
        mz.newWinner(username, mapid, result);
    }
}
