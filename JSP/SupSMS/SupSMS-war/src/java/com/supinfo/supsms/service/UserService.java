/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.service;

import com.supinfo.supsms.dao.UserDao;
import com.supinfo.supsms.entity.User;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author 15729_000
 */
@Stateless
public class UserService {
    @EJB
    private UserDao userDao;
    public User processUserDao(User user){
        userDao.addNewUser(user);
        return user;
}
}
