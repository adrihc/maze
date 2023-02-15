package com.liceu.maze.filters;


import com.liceu.maze.model.Game;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebFilter(urlPatterns = {"/getkey","/getcoin","/nav","/open","/reset","/endform"})
public class MazeFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        Game game = (Game) session.getAttribute("game");
        if (game == null){
            res.sendRedirect("/start");
            return;
        } else if (req.getSession() == null) {
            res.sendRedirect("/start");
            return;
        }
        chain.doFilter(req,res);
    }
}
