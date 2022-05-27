package com.wangbin.controller;

import com.wangbin.dao.ProductDao;
import com.wangbin.model.Category;
import com.wangbin.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ShopServlet", value = "/ShopServlet")
public class ShopServlet extends HttpServlet {

    Connection con = null;
    @Override
    public void init() throws ServletException {
        con =(Connection)getServletContext().getAttribute("dbConn");

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = new Category();;
        List<Category> categoryList=category.findAllCategory(con);
        request.setAttribute("categoryList",categoryList);

        ProductDao productDao = new ProductDao();
        List<Product> productList = null;
        try {
            if(request.getParameter("categoryId")==null) {
                productList = productDao.findAll(con);
            } else {
                int categoryId =  Integer.parseInt(request.getParameter("categoryId"));
                productList = productDao.findByCategoryId(categoryId,con);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("productlist",productList);
        String path = "/WEB-INF/views/shop.jsp";
        request.getRequestDispatcher(path).forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
