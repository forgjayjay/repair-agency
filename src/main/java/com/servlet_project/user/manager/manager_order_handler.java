package com.servlet_project.user.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class manager_order_handler extends HttpServlet {
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
            ArrayList<String> arrayString;
            if(request.getParameter("desc") == null) {
                arrayString = managerDao.showAllOrdersAsc();
            }
            else{ 
                arrayString = managerDao.showAllOrdersDesc();
            }
            out.println("All available orders:<br />");
            for (String string : arrayString) {
                out.println(string + "<br />");
                out.println();
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
            if(request.getParameter("price")!=null){
                try {
                    int craftsmanID = Integer.valueOf(request.getParameter("craftsmanID"));
                    double price = Double.valueOf(request.getParameter("price"));
                    if(managerDao.priceOrder(price, craftsmanID)){
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

