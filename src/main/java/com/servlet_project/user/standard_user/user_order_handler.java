package com.servlet_project.user.standard_user;

import java.io.IOException;
import java.io.PrintWriter;

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
        String n = null;
        if(cookies!=null){
            for(Cookie cookie : cookies ){
                if(cookie.getName().equals("login")){
                    n=cookie.getValue();
                    break;
                }

            }
        } else response.sendRedirect(request.getContextPath()+ "/Login");  
        if(n == null){
            response.sendRedirect(request.getContextPath()+ "/Login"); 
        }
        String name = null;
        
        if(request.getParameter("neworder")!=null) {
            
            if(userDao.insertOrder(name)) out.println("Order successfully created");
            else out.println("Sorry, something went wrong");

            
        } else
       
        if(request.getParameter("showorder")!= null){
            out.println("Your orders: \n");
            out.println(userDao.showOrders(name));
        } else

        if(request.getParameter("payment")!= null){
            out.println("Your orders: \n");
            out.println(userDao.showUnpaidOrders(name));
        }
        RequestDispatcher rd = request.getRequestDispatcher("user_page"); 
        rd.include(request,response);  
        out.close();  
    }  
}
