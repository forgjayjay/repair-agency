package com.servlet_project.user.craftsman;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.servlet_project.dbmanager.Order;
import com.servlet_project.dbmanager.Constants;
import com.servlet_project.login.LoginDao;

public class craftsman_order_handler extends HttpServlet {
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
        CraftsmanDao craftsmanDao = new CraftsmanDao();
        Cookie cookies[] = request.getCookies();
        String name = null;
        if(cookies!=null){
            for(Cookie cookie : cookies ){
                if(cookie.getName().equals("login")){
                    name=cookie.getValue();
                    break;
                }

            }
        } else response.sendRedirect(request.getContextPath()+ "/Login");  
        if(name == null){
            response.sendRedirect(request.getContextPath()+ "/Login"); 
        }
        out.println("\n<h2>Welcome, to craftsman page, " + name + "</h2>\n");

        RequestDispatcher rd = request.getRequestDispatcher("craftsman_page.jsp"); 
        rd.include(request,response); 
        if(request.getParameter("show")!= null){
            

            out.println("Your assigned orders: <br />");
            out.println();
            HashMap<Order, String> orderMap = craftsmanDao.showOrders(name);
            for (Map.Entry<Order, String> order : orderMap.entrySet()) {
                out.println(order.getValue());
                if(order.getValue().equals("No assigned orders are present")) break;
                request.setAttribute("orderID", order.getKey());
                request.getSession().setAttribute("orderID", order.getKey());
                this.getServletConfig()
                    .getServletContext()
                    .setAttribute("orderID",  order.getKey());
                RequestDispatcher orderDispatcher = request.getRequestDispatcher("order_status_update.jsp");
                orderDispatcher.include(request, response); 
                out.println("<br /><br />");
            }       

        } else if (request.getParameter("orderID") != null){
            String id = request.getParameter("orderID");
            if(request.getParameter("finished") != null){
                try {
                    if(craftsmanDao.updateOrder(Constants.ORDER_STATUS_DONE, Integer.valueOf(id), name)){
                        out.println("Successfully updated!");
                    }else out.println("Something went wrong!");
                } catch (Exception e) {
                    out.println("<br/ >Something went wrong! Maybe try entering correct order id!<br/ >");
                }
            } else{
                try {
                    if(craftsmanDao.updateOrder(Constants.ORDER_STATUS_WORKING, Integer.valueOf(id), name)){
                        out.println("Successfully updated!");
                    }else out.println("Something went wrong!");
                } catch (Exception e) {
                    out.println("<br/ >Something went wrong! Maybe try entering correct order id!<br/ >");
                }
            }
        }
        out.close();  
    }  
}

