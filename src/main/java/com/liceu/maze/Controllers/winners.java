package com.liceu.maze.Controllers;

import com.liceu.maze.DAO.MazeDAO;
import com.liceu.maze.model.Winner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/winners")
public class winners extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        MazeDAO md = new MazeDAO();
        req.getSession().setAttribute("game", null);
        req.setAttribute("winners", md.returnWinners());
        req.getSession().removeAttribute("game");
        RequestDispatcher dispatcher=
                req.getRequestDispatcher("/WEB-INF/jsp/winners.jsp");
        dispatcher.forward(req, resp);
    }
}
