package com.wangbin.lab1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Assignment1Servlet", value = "/Assignment1Servlet")
public class Assignment1Servlet extends HttpServlet {

    int index = 0;

    @Override
    public void init() throws ServletException {
        super.init();
        index = 0;
        System.out.println("I Am from default constructor");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String val="since loading ,this servlet has been accessed " + index + "times.";
        index ++;
        request.setAttribute("val",val);
        request.getRequestDispatcher("/exercise1.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
