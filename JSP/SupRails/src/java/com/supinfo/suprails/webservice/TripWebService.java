/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.suprails.webservice;

import com.supinfo.suprails.entity.Customer;
import com.supinfo.suprails.entity.CustomerOrder;
import com.supinfo.suprails.entity.Trip;
import com.supinfo.suprails.service.CustomerOrderService;
import com.supinfo.suprails.service.TripService;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Leo
 */
@WebService(serviceName = "TripWebService")
public class TripWebService {
    
    @EJB
    private TripService tripService;
    @EJB
    private CustomerOrderService customerOrderService;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "retriveAllTrips")
    public List<Trip> retriveAllTrips() {
        //TODO write your implementation code here:
      
        return  tripService.getAllTrips();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "processOrder")
    public String processOrder(@WebParam(name = "FirstName") String FirstName, @WebParam(name = "LastName") String LastName, @WebParam(name = "Email") String Email, @WebParam(name = "TripId") String TripId) {
        //TODO write your implementation code here:
        Customer customer = new Customer();
        customer.setFirstName(FirstName);
        customer.setLastName(LastName);
        customer.setEmail(Email);
        CustomerOrder customerorder = new CustomerOrder();
        customerorder.setCustomer(customer);
        Trip trip = tripService.findTripById(new Long(TripId));
        customerorder.setTrip(trip);
        customerOrderService.processCustomerOrder(customerorder);
        return "success";
    }
   
 
}
