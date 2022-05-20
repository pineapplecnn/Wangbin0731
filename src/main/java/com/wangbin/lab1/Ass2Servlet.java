package com.wangbin.lab1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Ass2Servlet", value = "/Ass2Servlet")
public class Ass2Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/ass2.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name=request.getParameter("name");
        String cla=request.getParameter("class");
        String id=request.getParameter("id");
        request.setAttribute("name",name);
        request.setAttribute("cla",cla);
        request.setAttribute("id",id);
        request.getRequestDispatcher("/MyDear.jsp").forward(request,response);

    }
}
