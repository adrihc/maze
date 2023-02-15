package com.liceu.maze.filters;

import org.apache.commons.lang.time.StopWatch;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/getkey","/getcoin","/nav","/open","/reset","/endform"})
public class StopwatchFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (req.getSession().getAttribute("watchIsStop") == null) {
            res.sendRedirect("/start");
            return;
        }
        boolean watchIsStop = (boolean) req.getSession().getAttribute("watchIsStop");
        if (watchIsStop){
            res.sendRedirect("/start");
            return;
        }
        chain.doFilter(req,res);
    }
}
