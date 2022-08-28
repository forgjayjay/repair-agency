package com.servlet_project.user.craftsman;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.servlet_project.login.LoginDao;
public class craftsman_page extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        
        Cookie cookies[] = request.getCookies();
        String name ="", pass="";
        if(cookies!=null){
            for(Cookie cookie : cookies ){
                if(cookie.getName().equals("login")) {
                    name = cookie.getValue();
                }
                if(cookie.getName().equals("pass")) {
                    pass = cookie.getValue();
                }
            }
            LoginDao validator = new LoginDao();
            if(validator.validate(name, pass) && validator.userType(name).equals("craftsman")) doPost(request, response);
            else response.sendRedirect(request.getContextPath()+ "/Login");
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
                if(cookie.getName().equals("login")) {
                    n=cookie.getValue();
                }
            }
        } else response.sendRedirect(request.getContextPath()+ "/Login");  
        if(n == null){
            response.sendRedirect(request.getContextPath()+ "/Login"); 
        }
        
        out.println("\n<h2>Welcome, to craftsman page, " + n + "</h2>\n");
        RequestDispatcher rd = request.getRequestDispatcher("craftsman_page.jsp"); 
        rd.include(request,response);  

        
        out.close();  
    }  
}
//craftsman_page