/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.suprails.service;

import com.supinfo.suprails.dao.CustomerOrderDao;
import com.supinfo.suprails.entity.CustomerOrder;
import java.util.Properties;
import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Leo
 */
@Stateless
public class CustomerOrderService {

    @EJB
    private CustomerOrderDao customerOrderDao;

    public CustomerOrder processCustomerOrder(CustomerOrder customerOrder) {
        customerOrderDao.addCustomerOrder(customerOrder);
  
        return customerOrder;
    }

    @Asynchronous
    public Future<String> sendEmail(CustomerOrder customerOrder) {
        String status;
        try {
            System.out.println(customerOrder.getId() + customerOrder.getCustomer().getLastName());
            Thread.sleep(5000);
            status = "sent";
        } catch (Exception e) {
            status = "error";
        }
        return new AsyncResult<String>(status);
    }

}
