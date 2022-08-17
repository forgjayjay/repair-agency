package com.servlet_project.user.craftsman;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.servlet_project.dbmanager.constants;

public class craftsman_order_handler extends HttpServlet {
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
        
        if(request.getParameter("show")!= null){
            out.println("Your orders: \n");
            out.println(craftsmanDao.showOrders(name));
            RequestDispatcher rd = request.getRequestDispatcher("order_status_update.jsp"); 
            rd.include(request,response); 
        } else if (request.getParameter("id") != null){
            String id = request.getParameter("id");
            if(request.getParameter("finished") != null){
                craftsmanDao.updateOrder(constants.ORDER_STATUS_DONE, Integer.valueOf(id));

            } else{
                craftsmanDao.updateOrder(constants.ORDER_STATUS_WORKING, Integer.valueOf(id));

            }
        }
        RequestDispatcher reqdisp = request.getRequestDispatcher("craftsman_page"); 
        reqdisp.include(request,response);  
         
        out.close();  
    }  
}

