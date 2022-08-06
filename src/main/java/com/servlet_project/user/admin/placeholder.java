package com.servlet_project.user.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class placeholder extends HttpServlet {
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
        
        if(request.getParameter("manager").equals("add a manager")){
            if(request.getParameter("newuser")!=null){
                RequestDispatcher rd=request.getRequestDispatcher("manager_registration.jsp");  
                rd.forward(request,response);
            } else{
                RequestDispatcher rd=request.getRequestDispatcher("manager_update.jsp");  
                rd.forward(request,response);
            }
        }
        
        
        out.close(); 
    }  
}

