package com.liceu.maze.Controllers;

import com.liceu.maze.model.Game;
import com.liceu.maze.model.Maze;
import com.liceu.maze.model.Player;
import com.liceu.maze.model.Room;
import com.liceu.maze.services.PlayGameService;
import com.liceu.maze.services.TextService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getkey")
public class getkey extends HttpServlet {
    TextService textService = new TextService();
    PlayGameService pgs = new PlayGameService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Game game = (Game) req.getSession().getAttribute("game");
        req.getSession().setAttribute("game", pgs.addKey(game));
        String myjson = textService.getJsonInfo(game);
        req.setAttribute("myjson", myjson);
        RequestDispatcher dispatcher=
                req.getRequestDispatcher("/WEB-INF/jsp/maze.jsp");
        dispatcher.forward(req, resp);
    }
}
