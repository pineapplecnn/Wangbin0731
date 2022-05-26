package com.wangbin.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "UserListServlet",value = "/admin/userList")
public class UserListServlet extends HttpServlet {
    Connection con = null;
    public void init() throws ServletException{
        con = (Connection) getServletContext().getAttribute("con");
    }
public void destroy(){
        super.destroy();
}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/admin/userList.jsp").forward(request,response);
    }
}

