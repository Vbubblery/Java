/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.suprails.web.servlet;

import com.supinfo.suprails.entity.Customer;
import com.supinfo.suprails.entity.Trip;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.suprails.service.TripService;
import com.supinfo.suprails.service.CustomerOrderService;
import com.supinfo.suprails.entity.*;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Leo
 */
@WebServlet(name = "BuyTripServlet", urlPatterns = {"/trips/buytrip"})
public class BuyTripServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @EJB
    private TripService tripService;
    @EJB
    private CustomerOrderService customerOrderService;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
       String idParam = request.getParameter("id");
       Long tripId = Long.valueOf(idParam);
       
       Trip trip = tripService.findTripById(tripId);
        HttpSession session = request.getSession();
        session.setAttribute("trip", trip);
        request.getRequestDispatcher("/jsp/customerpart.jsp").forward(request, response);
        
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
    
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        
        HttpSession session = request.getSession();
        Trip trip = (Trip)session.getAttribute("trip");
        
        Customer customer = new Customer();
        customer.setFirstName(firstname);
        customer.setLastName(lastname);
        customer.setEmail(email);
        
        
        CustomerOrder customerorder = new CustomerOrder();
        customerorder.setCustomer(customer);
        customerorder.setTrip(trip);
        customerOrderService.processCustomerOrder(customerorder);
        response.sendRedirect(getServletContext().getContextPath()+"/trips");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
