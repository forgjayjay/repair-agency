package com.servlet_project.user.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class admin_page extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
            response.setContentType("text/html");  
            PrintWriter out = response.getWriter();  
            
            Cookie cookies[] = request.getCookies();
            if(cookies!=null){
                for(Cookie cookie : cookies ){
                    if(cookie.getName().equals("login")) {
                        doPost(request, response);
                    }
                }
            } else response.sendRedirect(request.getContextPath()+ "/Login");  
              
        out.close();      
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  

        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
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
        out.print("Welcome, to admin page, " + n);
        RequestDispatcher rd=request.getRequestDispatcher("admin_page_form.jsp"); 
        
        rd.include(request,response);  

        
        out.close();  
    }  
}

