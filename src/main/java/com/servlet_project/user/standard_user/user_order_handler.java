package com.servlet_project.user.standard_user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class user_order_handler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
              
        out.print("Please, login first");  
        RequestDispatcher rd=request.getRequestDispatcher("login_page.jsp");  
        rd.include(request,response);    
              
        out.close();      
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  

        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        UserDao userDao = new UserDao();
        Cookie cookies[] = request.getCookies();
        String name = null;
        if(cookies!=null){
            for(Cookie cookie : cookies ){
                if(cookie.getName().equals("login")){
                    name=cookie.getValue();
                }

            }
        } else response.sendRedirect(request.getContextPath()+ "/Login");  
        if(name == null){
            response.sendRedirect(request.getContextPath()+ "/Login"); 
        }
        RequestDispatcher rd = request.getRequestDispatcher("user_page"); 
        
        if(request.getParameter("neworder")!=null) {
            
            if(userDao.insertOrder(name)) out.println("Order successfully created");
            else out.println("Sorry, something went wrong");

            
        }
        if(request.getParameter("showorder")!= null){
            out.println("Your orders: \n<br />");
            out.println();
            ArrayList<String> arrayString =  userDao.showOrders(name);
            for (String string : arrayString) {
                out.println(string + "<br />");
                out.println();
            }
        }
        if(request.getParameter("showorder_payment")!= null){
            out.println("Your unpaid orders: <br />");
            out.println();

            ArrayList<String> arrayString =  userDao.showUnpaidOrders(name);
            for (String string : arrayString) {
                out.println(string + "<br />");
                out.println();
            }        
        }
        rd.include(request,response); 
        out.close();  
    }  
}

