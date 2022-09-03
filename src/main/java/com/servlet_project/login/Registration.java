package com.servlet_project.login;
import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse; 

/*
*       The Registration page servlet 
*/

public class Registration extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  

    doPost(request, response);
          
    }  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
  
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
          
    String n=request.getParameter("username");  
    String p=request.getParameter("userpass");  
    String p2=request.getParameter("userpass2");  
    RegistrationDao validator = new RegistrationDao();
    if(validator.insert(n, p, p2)){  
        out.print("Account successfully created, you will be redirected to login page soon");  
        RequestDispatcher rd=request.getRequestDispatcher("login_page.jsp");  
        rd.include(request, response); 
    }  
    else{  
        out.print("Sorry username already exists or the passwords don't match!");
        RequestDispatcher rd=request.getRequestDispatcher("registration_page.jsp");  
        rd.include(request,response);  
    }  
          
    out.close();  
    }  
}

