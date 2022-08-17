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
*       The Login page servlet 
*/

public class Login extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
  
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
    
    RequestDispatcher rd=request.getRequestDispatcher("login_page.jsp");  
    rd.include(request,response);  
          
    out.close();  
    }  
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
    
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
    


    String name=request.getParameter("username");  
    String pass=request.getParameter("userpass");  
    LoginDao validator = new LoginDao();
    if(validator.validate(name, pass)){ 
        Cookie cookies[] = request.getCookies();
        Cookie login = new Cookie("login", name);
        response.addCookie(login);
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("login")) cookie = new Cookie("login", name);
        }
        String redirectStr = validator.userType(name);
        RequestDispatcher rd=request.getRequestDispatcher(redirectStr + "_page"); 
        
        rd.forward(request,response);  
    }  
    else{  
        out.print("Sorry username or password error");  
        RequestDispatcher rd=request.getRequestDispatcher("login_page.jsp");  
        rd.include(request,response);  
    }  
          
    out.close();  
    }  
}
