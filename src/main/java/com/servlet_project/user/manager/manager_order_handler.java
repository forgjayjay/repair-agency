package com.servlet_project.user.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class manager_order_handler extends HttpServlet {
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
        ManagerDao managerDao = new ManagerDao();
        Cookie cookies[] = request.getCookies();
        String name = null;
        if(cookies!=null){
            for(Cookie cookie : cookies ){
                if(cookie.getName().equals("login")){
                    name=cookie.getValue();
                    break;
                }

            }
        } else response.sendRedirect(request.getContextPath()+ "/Login");  
        if(name == null){
            response.sendRedirect(request.getContextPath()+ "/Login"); 
        }
        out.println("\n<h2>Welcome, to manager page, " + name + "</h2><br /><br />");

        RequestDispatcher rd=request.getRequestDispatcher("manager_page.jsp"); 
        
        rd.include(request,response);  

        if(request.getParameter("showorder")!=null) {
            
            out.println("All available orders:<br />");
            ArrayList<String> arrayString = managerDao.showAllOrdersAsc();
            for (String string : arrayString) {
                out.println(string + "<br />");
                out.println();
            }
        }

        out.close();  
    }  
}

