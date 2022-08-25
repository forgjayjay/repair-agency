package com.servlet_project.user.standard_user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.servlet_project.dbmanager.Order;

public class user_order_handler extends HttpServlet {
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
        UserDao userDao = new UserDao();
        Cookie cookies[] = request.getCookies();
        String name = null;
        if(cookies!=null){
            for(Cookie cookie : cookies ){
                if(cookie.getName().equals("login")){
                    name=cookie.getValue();
                }

            }
        } else response.sendRedirect(request.getContextPath()+ "/Login");  
        if(name == null){
            response.sendRedirect(request.getContextPath()+ "/Login"); 
        }
        out.println("\n<h2>Welcome, to user page, " + name + "</h2>\n");

        RequestDispatcher rd=request.getRequestDispatcher("user_page.jsp"); 
        
        rd.include(request,response);  

        if(request.getParameter("neworder")!=null) {
            
            if(userDao.insertOrder(name)) out.println("Order successfully created");
            else out.println("Sorry, something went wrong");

            
        }
        if(request.getParameter("showorder")!= null){
            out.println("Your orders: \n<br />");
            out.println();
            HashMap<Order, String> orderMap =  userDao.showOrders(name);
            for (String string : orderMap.values()) {
                out.println(string + "<br />");
                out.println();
            }
        }
        if(request.getParameter("showorder_payment")!= null){
            out.println("Your unpaid orders: <br />");
            out.println();
            HashMap<Order, String> orderMap =  userDao.showUnpaidOrders(name);
            for (Map.Entry<Order, String> entry : orderMap.entrySet()) {
                
                out.println(entry.getValue());
                // Cookie cookie = new Cookie(
                //     "orderID" + Integer.toString(entry.getKey().getId()),
                //     Integer.toString(entry.getKey().getId()
                // ));
                // response.addCookie(cookie);
                request.setAttribute("orderID", entry.getKey().getId());
                
                out.println("<br /><br />");
            }       
            RequestDispatcher orderDispatcher = request.getRequestDispatcher("payment.jsp");
            orderDispatcher.include(request, response); 
        }
        if(request.getParameter("paid")!=null){
            try {
                if(userDao.updateOrderPaymentStatus(
                    Integer.valueOf(request.getParameter("id")),
                    Double.valueOf(request.getParameter("ammount"))
                ))
                out.println("Order " + request.getParameter("id") + " is paid for!");
                else out.println("Something went wrong!");
            } catch (Exception e) {
                System.out.println(e);
                out.println("Something went wrong!");
            }
            
        }
        if(request.getParameter("review") != null){
            out.println("Leave a review : <br />");
        }
        out.close();  
    }  
}

