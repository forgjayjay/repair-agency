package com.forg.login;
import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

/*
*       The Logout page servlet 
*/

public class Logout extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  

    Cookie[] cookies = request.getCookies();
    for(Cookie cookie : cookies){
        if(cookie.getName().equals("login")) {
            cookie =new Cookie("login", "");
            cookie.setMaxAge(0);
        }
    }
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
    response.sendRedirect(request.getContextPath() + "/Login");
  
          
    out.close();  
    }  
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
    
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
    
    Cookie[] cookies = request.getCookies();
    for(Cookie cookie : cookies){
        if(cookie.getName().equals("login")) {
            cookie =new Cookie("login", "");
            cookie.setMaxAge(0);
        }
    }
    response.sendRedirect(request.getContextPath() + "/Login");
    out.close();
    }  
}
