package com.liceu.maze.Controllers;

import com.liceu.maze.DAO.MazeDAO;
import com.liceu.maze.services.CalculateTime;
import com.liceu.maze.services.UserService;
import org.apache.commons.lang.time.StopWatch;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/endform")
public class endform extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StopWatch watch = (StopWatch) req.getSession().getAttribute("watch");
        watch.stop();
        long result = watch.getTime();
        req.getSession().setAttribute("result", result);
        CalculateTime ct = new CalculateTime();
        int seconds = ct.seconds(result);
        int minutes = ct.minutes(seconds);
        int hores = ct.hores(minutes);

        while (seconds >=60){
            seconds = seconds -60;
        }
        while (minutes >= 60 ){
            minutes = minutes-60;
        }

        req.setAttribute("seconds", seconds);
        req.setAttribute("minutes", minutes);
        req.setAttribute("hores", hores);
        req.setAttribute("result", result);
        RequestDispatcher dispatcher=
                req.getRequestDispatcher("/WEB-INF/jsp/endform.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("user");
        int mapid = (int) req.getSession().getAttribute("mapid");
        long result = (Long) req.getSession().getAttribute("result");

        UserService database = new UserService();
        try {
            database.newWinner(username, mapid, result);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        req.getSession().setAttribute("username", username);
        req.getSession().setAttribute("game", null);
        req.getSession().setAttribute("watchIsStop",true);

        resp.sendRedirect("/winners");
    }
}
