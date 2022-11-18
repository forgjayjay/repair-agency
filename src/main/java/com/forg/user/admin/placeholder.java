package com.forg.user.admin;

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
        Cookie cookies[] = request.getCookies();
        String w = null;
        if(cookies!=null){
            for(Cookie cookie : cookies ){
                if(cookie.getName().equals("login")){
                    w=cookie.getValue();
                    break;
                }

            }
        } else response.sendRedirect(request.getContextPath()+ "/Login");  
        if(w == null){
            response.sendRedirect(request.getContextPath()+ "/Login"); 
        }
        if(request.getParameter("manager") != null){
            if(request.getParameter("newuser")!=null){
                RequestDispatcher rd=request.getRequestDispatcher("manager_registration.jsp");  
                rd.forward(request,response);
            } else{
                RequestDispatcher rd=request.getRequestDispatcher("manager_update.jsp");  
                rd.forward(request,response);
            }
        } else{
            if(request.getParameter("newuser")!=null){
                RequestDispatcher rd=request.getRequestDispatcher("craftsman_registration.jsp");  
                rd.forward(request,response);
            } else{
                RequestDispatcher rd=request.getRequestDispatcher("craftsman_update.jsp");  
                rd.forward(request,response);
            }
        }
        
        
        out.close(); 
    }  
}

