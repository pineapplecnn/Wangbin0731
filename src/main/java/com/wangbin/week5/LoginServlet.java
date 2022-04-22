package com.wangbin.week5;

import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;
import com.wangbin.dao.UserDao;
import com.wangbin.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    Connection con=null;
    @Override
    public void init() throws ServletException {
        super.init();
        /// TODO 1: GET 4 CONTEXT PARAM - DRIVER , URL , USERNAME , PASSWORD
        // TODO 2: GET JDBC connection
        //only one one
         con = (Connection) getServletContext().getAttribute("con");
         //check the video live demo#4
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // doPost(request,response);//call dopost
        //when user click Login from menu- method is get
    request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out= response.getWriter();
    // TODO 3: GET REQUEST PARAMETER - USERNAME AND PASSWORD
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        try {

        //lets change code to make MVC

        //TODO 4: VALIDATE USER - SELEECT * FROM USERTABLE WHERE USERNAME='XXX'
        // AND PASSWORD='YYY'
        /*String sql="select id,username,password,email,gender,birthdate from usertable where username='"+username+"' and password='"+password+"'";

            ResultSet rs =con.createStatement().executeQuery(sql);
            if (rs.next()){//login success
                //week 5 code
                //out.print("Login Successful!!!");
                //out.print("Welcome"+username);
                //get from rs and set into resquest attribute

                request.setAttribute("id",rs.getInt("id"));
                request.setAttribute("username",rs.getString("username"));
                request.setAttribute("password",rs.getString("password"));
                request.setAttribute("email",rs.getString("email"));
                request.setAttribute("gender",rs.getString("gender"));
                request.setAttribute("birthDate",rs.getString("birthdate"));
*/
                //Week 7 MVC
                //forward to userInfo.jsp
            UserDao userDao = new UserDao();
            User user = userDao.findByUsernamePassword(con,username,password);
            if(user != null){
                // login success

                // week 8 code - add session code
                // 2 methods for session
                // 1. use cookie - Demo #1
                // s1. creat cookies object
//                Cookie c = new Cookie("SessionId","" + user.getId()); // sessionid = 70
                // s2. set age of cookie - in sec
//                c.setMaxAge(50); //20 seconds (PS: sec -> min eg: 20 * 60 means 20min)
                // s3. add cookie into response
//                response.addCookie(c);
                // 2. use HttpSession object - Demo #2
                //don't need to do method 1
                //request.getSession(); // always not null - if have session - return session don't have creat a new one
                HttpSession session = request.getSession();
                System.out.println("Session id --> "+session.getId());
                session.setMaxInactiveInterval(20);// 20min
                //step 8
                //request.setAttribute("user",user);
                //week 8
                session.setAttribute("user",user); // get in many jsps

                //step 9
                //request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);

            }else{
              //out.print("Username or password Error!!!");
                request.setAttribute("message","Username or Password Error!!!");
                //Week7 MVC
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);

            }//end of else


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
