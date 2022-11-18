package com.forg.user.standard_user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.forg.dbmanager.Order;
import com.forg.login.LoginDao;

public class user_order_handler extends HttpServlet {
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
            if(validator.validate(name, pass)) doPost(request, response);
            else response.sendRedirect(request.getContextPath()+ "/Login");
        } else response.sendRedirect(request.getContextPath()+ "/Login");   
              
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
            RequestDispatcher orderDispatcher;
            for (Map.Entry<Order, String> order : orderMap.entrySet()) {
                out.println(order.getValue());
                request.setAttribute("orderID", order.getKey());
                request.getSession().setAttribute("orderID", order.getKey());
                this.getServletConfig()
                    .getServletContext()
                    .setAttribute("orderID",  order.getKey());
                if(!order.getKey().isReviewed() && order.getKey().getCompleted()){
                    orderDispatcher = request.getRequestDispatcher("reviewCraftsman.jsp");
                    orderDispatcher.include(request, response);
                }
                out.println("<br /><br />");
            }  
        }
        if(request.getParameter("showorder_payment")!= null){
            out.println("Your unpaid orders: <br />");
            out.println();
            HashMap<Order, String> orderMap =  userDao.showUnpaidOrders(name);
            RequestDispatcher orderDispatcher;
            for (Map.Entry<Order, String> order : orderMap.entrySet()) {
                out.println(order.getValue());
                request.setAttribute("orderID", order.getKey());
                request.getSession().setAttribute("orderID", order.getKey());
                this.getServletConfig()
                    .getServletContext()
                    .setAttribute("orderID",  order.getKey());
                orderDispatcher = request.getRequestDispatcher("paymentObject.jsp");
                orderDispatcher.include(request, response); 
                if(!order.getKey().isReviewed() && order.getKey().getCompleted()){
                    orderDispatcher = request.getRequestDispatcher("reviewCraftsman.jsp");
                    orderDispatcher.include(request, response);
                }
                out.println("<br /><br />");
            }       
            
        }
        if(request.getParameter("paid")!=null){
            try {
                if(userDao.updateOrderPaymentStatus(
                    Integer.valueOf(request.getParameter("id")),
                    Double.valueOf(request.getParameter("cost"))
                ))
                out.println("Order " + request.getParameter("id") + " is paid for!");
                else out.println("Something went wrong!");
            } catch (Exception e) {
                System.out.println(e);
                out.println("Something went wrong!");
            }
            
        }
        if(request.getParameter("review") != null){
            try {
                int craftsmanID = userDao.getCraftsmanId(Integer.valueOf(request.getParameter("id")));
                if(userDao.orderReview(craftsmanID, Integer.valueOf(request.getParameter("rating")))){
                    out.println("Success!");
                }
            } catch (Exception e) {
                System.out.println(e);
                out.println("Something went wrong!");
            }
        }
        out.close();  
    }  
}

