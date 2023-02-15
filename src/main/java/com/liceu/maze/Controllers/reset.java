package com.liceu.maze.Controllers;

import com.liceu.maze.model.Game;
import com.liceu.maze.services.MazeGame;
import com.liceu.maze.services.TextService;
import org.apache.commons.lang.time.StopWatch;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reset")
public class reset extends HttpServlet {
    TextService textService = new TextService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int mapid = (int) req.getSession().getAttribute("mapid");
        Game game = MazeGame.createMaze(mapid);
        req.getSession().setAttribute("game", game);

        StopWatch watch = new StopWatch();
        watch.start();
        req.getSession().setAttribute("watch", watch);


        String myjson = textService.getJsonInfo(game);
        req.setAttribute("myjson", myjson);
        RequestDispatcher dispatcher=
                req.getRequestDispatcher("/WEB-INF/jsp/maze.jsp");
        dispatcher.forward(req, resp);
    }
}
