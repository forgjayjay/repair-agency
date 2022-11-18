package com.forg.login;
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
    Cookie cookie = new Cookie("login", "");
    RequestDispatcher rd=request.getRequestDispatcher("login_page.jsp");  
    response.addCookie(cookie);
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
        
        String redirectStr = validator.userType(name);
        Cookie loginCookie = new Cookie("login", name);
        Cookie passCookie = new Cookie("pass", pass);

        response.addCookie(loginCookie);
        response.addCookie(passCookie);
        response.sendRedirect(request.getContextPath() +"/"+ redirectStr+ "_page");
          
    }  
    else{  
        out.print("Sorry username or password error");  
        RequestDispatcher rd=request.getRequestDispatcher("login_page.jsp");  
        rd.include(request,response);  
    }  
          
    out.close();  
    }  
}
