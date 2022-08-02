package com.servlet_project.login;
import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse; 
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
          
    String n=request.getParameter("username");  
    String p=request.getParameter("userpass");  
    LoginDao validator = new LoginDao();
    if(validator.validate(n, p)){  
        // RequestDispatcher rd=request.getRequestDispatcher("Welcome");  
        // rd.forward(request,response);  
        response.sendRedirect(request.getContextPath() + "/Welcome?username="+n);
    }  
    else{  
        out.print("Sorry username or password error");  
        RequestDispatcher rd=request.getRequestDispatcher("login_page.jsp");  
        rd.include(request,response);  
    }  
          
    out.close();  
    }  
}
