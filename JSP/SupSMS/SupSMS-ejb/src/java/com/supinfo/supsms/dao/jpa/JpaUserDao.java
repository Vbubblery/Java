/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.dao.jpa;

import com.supinfo.supsms.dao.UserDao;
import com.supinfo.supsms.entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 15729_000
 */
@Stateless
public class JpaUserDao implements UserDao{
    @PersistenceContext
    private EntityManager em;
    @Override
    public User addNewUser(User user) {
        em.persist(user);
        return user;
    }
    
}
