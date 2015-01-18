/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.dao;

import javax.ejb.Local;
import com.supinfo.supsms.entity.User;
/**
 *
 * @author 15729_000
 */
@Local
public interface UserDao {
    public User addNewUser(User user);
}
