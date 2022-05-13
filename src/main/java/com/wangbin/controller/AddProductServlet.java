package com.wangbin.controller;

import com.wangbin.dao.ProductDao;
import com.wangbin.model.Category;
import com.wangbin.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/admin/addProduct")
@MultipartConfig(maxFileSize = 16177215)
public class AddProductServlet extends HttpServlet {

    private Connection con = null;
    private static final Logger log = Logger.getLogger(AddProductServlet.class);

    public void destory(){
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = new Category();
        List<Category> categoryList = category.findAllCategory(con);
        request.setAttribute("categoryList",categoryList);
        String path = "/WEB-INF/views/admin/addProduct.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String productName=request . getParameter(  "productName") ;
        Double price =request . getParameter("price") !=null?Double.parseDouble (request. getParameter("price")):0.0;
        int categoryId=request . getParameter( "categoryId") !=null?Integer.parseInt (request . getParameter("categoryId")):0;
        String productDescription =request . getParameter("productDescription");

        //picture
        InputStream inputstream = null; // input stream of the upLoad file
        Part filePart = request.getPart("picture");// obtains the upLoad file part in this multipart request
        if (filePart != null) {
                // prints out some information for debugging
            System.out.println("file name :" + filePart.getName() + " size" + filePart.getSize() + "file type" + filePart.getContentType());
            inputstream = filePart.getInputStream(); // obtains input stream of the upLoad file
        }
        //set in model
        Product product=new Product();
        product.setProductName(productName);
        product.setPrice(price);
        product.setProductDescription(productDescription);
        product.setCategoryId(categoryId);

        ProductDao dao = new ProductDao();
        int i = 0;
        try {
            i = dao.save(product,inputstream,con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(i > 0){
//            response.sendRedirect("productList");//next class;
        }
    }//end post

}
