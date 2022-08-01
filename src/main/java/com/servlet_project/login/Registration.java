package com.servlet_project.login;
import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse; 
public class Registration extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
  
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
          
    String n=request.getParameter("username");  
    String p=request.getParameter("userpass");  
    RegistrationDao validator = new RegistrationDao();
    if(validator.insert(n, p)){  
        out.print("Account successfully created");  
        RequestDispatcher rd=request.getRequestDispatcher("login_page.jsp");  
        rd.forward(request,response);  
    }  
    else{  
        out.print("Sorry username already exists");  
        RequestDispatcher rd=request.getRequestDispatcher("registration_page.jsp");  
        rd.include(request,response);  
    }  
          
    out.close();  
    }  
}

