package com.liceu.maze.filters;


import com.liceu.maze.model.Game;
import com.liceu.maze.model.Maze;
import com.liceu.maze.model.Player;
import com.liceu.maze.model.Room;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebFilter(urlPatterns = {"/endform"})
public class IsEndFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        Game game = (Game) req.getSession().getAttribute("game");
        Maze maze = game.getMaze();
        Player player = game.getPlayer();
        Room currentRoom = maze.getRoom(player.getCurrentRoom());
        if (!currentRoom.isEnd()){
            res.sendRedirect("/start");
        }
        chain.doFilter(req,res);
    }


}
