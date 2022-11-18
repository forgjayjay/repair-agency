package com.forg.user.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.forg.dbmanager.Order;
import com.forg.login.LoginDao;

public class manager_order_handler extends HttpServlet {
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
            if(validator.validate(name, pass) && validator.userType(name).equals("manager")) doPost(request, response);
            else response.sendRedirect(request.getContextPath()+ "/Login");
        } else response.sendRedirect(request.getContextPath()+ "/Login");   
        out.close();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  

        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        ManagerDao managerDao = new ManagerDao();
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
        out.println("\n<h2>Welcome, to manager page, " + name + "</h2><br /><br />");

        RequestDispatcher rd=request.getRequestDispatcher("manager_page.jsp"); 
        
        rd.include(request,response);  
        String login=request.getParameter("username");
        String realname=request.getParameter("name");
        if(request.getParameter("showorder")!=null) {
            HashMap<Order, String> orderMap;
            if(request.getParameter("desc") == null) {
                orderMap = managerDao.showAllOrdersAsc();
            }
            else{ 
                orderMap = managerDao.showAllOrdersDesc();
            }
            out.println("All available orders:<br />");
            for (Map.Entry<Order, String> order : orderMap.entrySet()) {
                out.println(order.getValue());
                request.setAttribute("orderID", order.getKey());
                request.getSession().setAttribute("orderID", order.getKey());
                this.getServletConfig()
                    .getServletContext()
                    .setAttribute("orderID",  order.getKey());
                out.println("<br /><br />");
            }    
        }
        if(request.getParameter("appoint")!=null) {
            RequestDispatcher reqdisp = request.getRequestDispatcher("appoint_craftsman.jsp");
            reqdisp.include(request, response);
            if(request.getParameter("craftsmanID")!=null){
                try {
                    int craftsmanID = Integer.valueOf(request.getParameter("craftsmanID"));
                    int orderID = Integer.valueOf(request.getParameter("orderID"));
                    if(managerDao.appointCraftsman(craftsmanID, orderID)){
                        out.println("Craftsman successfully updated");
                    }else out.println("Something went wrong!");
                } catch (Exception e) {
                    out.println("Something went wrong!");
                }
            }
                
        }
        if(request.getParameter("price")!=null) {
            RequestDispatcher reqdisp = request.getRequestDispatcher("order_price.jsp");
            reqdisp.include(request, response);
            if(request.getParameter("cost")!=null){
                try {
                    int orderID = Integer.valueOf(request.getParameter("orderID"));
                    double price = Double.valueOf(request.getParameter("cost"));
                    if(managerDao.priceOrder(price, orderID)){
                        out.println("Price successfully updated");
                    }else out.println("Something went wrong!");
                } catch (Exception e) {
                    out.println("Something went wrong!");
                }
            }
        }
        if(request.getParameter("craftsman")!=null) {
            RequestDispatcher reqdisp = request.getRequestDispatcher("craftsman_update_manager.jsp");
            reqdisp.include(request, response);
            if(login!=null && realname!=null){
                if(managerDao.updateCraftsman(login, realname)){
                    out.println("Craftsman successfully updated");
                }else out.println("Something went wrong!");
            }
        }
        out.close();  
    }  
}

