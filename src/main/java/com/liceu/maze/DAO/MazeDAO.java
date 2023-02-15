package com.liceu.maze.DAO;

import com.liceu.maze.model.Winner;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import static com.liceu.maze.utils.Session.getConnection;

public class MazeDAO implements MazeDaoInterface{

    @Override
    public void newWinner(String user, int mapid, long puntuacion) throws SQLException {
        Connection connection = getConnection();
        String insert = "INSERT INTO `users` (`id`, `username`, `score`) VALUES(?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setString(1, user);
            statement.setInt(2, mapid);
            statement.setLong(3, puntuacion);
            statement.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Winner> returnWinners() {
        List<Winner> winners = new ArrayList<>();
        PreparedStatement stmnt = null;
        String select = "SELECT * FROM winner";
        try{
            Connection connection = getConnection();
            stmnt = connection.prepareStatement(select);
            var result = stmnt.executeQuery();

            while (result.next()){
                Winner winner = new Winner();
                winner.setId(result.getInt("id"));
                winner.setName(result.getString("username"));
                winner.setTime(result.getLong("score"));
                winner.setMapid(result.getInt("mapid"));
                System.out.println(winner.getId() + winner.getName());
                winners.add(winner);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return winners;
    }
}
