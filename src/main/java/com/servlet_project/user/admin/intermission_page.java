package com.servlet_project.user.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class intermission_page extends HttpServlet {
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
        AdminDao validator = new AdminDao();
        String n=request.getParameter("username");
        if(request.getParameter("userpass")==null){
            if(!validator.insertManager(n)) out.println("Sorry, something went wrong");
        }else {
            String p=request.getParameter("userpass"); 
            if(!validator.insertManager(n,p)) out.println("Sorry, something went wrong");
        }
        RequestDispatcher rd=request.getRequestDispatcher("admin_page");  
        rd.forward(request,response); 
        out.close(); 
    }  
}

