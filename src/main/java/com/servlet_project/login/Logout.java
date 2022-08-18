package com.servlet_project.login;
import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.RequestDispatcher;  
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
    boolean login = false;
    for(Cookie cookie : cookies){
        if(cookie.getName().equals("login")) {
            cookie.setMaxAge(0);
            login = true;
        }
    }
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
    Cookie cookie = new Cookie("login", "");
    response.addCookie(cookie);
    if(login){
        
        out.println("Successfully logged out.");
        RequestDispatcher rd=request.getRequestDispatcher("Login"); 
        rd.forward(request,response); 
    } else{
        out.println("Please login first");
        RequestDispatcher rd=request.getRequestDispatcher("login_page.jsp");  
        rd.include(request,response);  
    }

    
          
    out.close();  
    }  
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
    
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
    
    Cookie[] cookies = request.getCookies();
    for(Cookie cookie : cookies){
        if(cookie.getName().equals("login")) {
            cookie.setMaxAge(0);
        }
    }
    Cookie cookie = new Cookie("login", "");
    response.addCookie(cookie);

    out.println("Successfully logged out.");
    RequestDispatcher rd=request.getRequestDispatcher("Login"); 
    rd.forward(request,response); 
          
    out.close();
    }  
}
