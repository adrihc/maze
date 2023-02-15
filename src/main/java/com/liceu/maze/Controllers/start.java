package com.liceu.maze.Controllers;

import com.liceu.maze.model.Game;
import com.liceu.maze.services.MazeGame;
import com.liceu.maze.services.TextService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.apache.commons.lang.time.StopWatch;
@WebServlet("/start")
public class start extends HttpServlet {
    TextService textService = new TextService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("game", null);
        req.getSession().setAttribute("watchIsStop",true);

        RequestDispatcher dispatcher=
                req.getRequestDispatcher("/WEB-INF/jsp/start.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mapid = req.getParameter("joc");
        req.getSession().setAttribute("watchIsStop",false);
        StopWatch watch = new StopWatch();
        watch.start();
        req.getSession().setAttribute("watch", watch);

        Game game = MazeGame.createMaze(Integer.parseInt(mapid));
        req.getSession().setAttribute("game", game);

        req.getSession().setAttribute("mapid",Integer.parseInt(mapid));
        String myjson = textService.getJsonInfo(game);
        req.setAttribute("myjson", myjson);
        RequestDispatcher dispatcher=
                req.getRequestDispatcher("/WEB-INF/jsp/maze.jsp");
        dispatcher.forward(req, resp);    }
}
