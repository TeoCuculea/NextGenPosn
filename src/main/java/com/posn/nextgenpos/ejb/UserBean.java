package com.posn.nextgenpos.ejb;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */



import com.posn.nextgenpos.common.UserDetails;
import com.posn.nextgenpos.entity.User;
import com.posn.nextgenpos.service.PositionInterceptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.*;

/**
 *
 * @author teodo
 */
@Stateless
@Table(name="USERS")
public class UserBean {

    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Interceptors(PositionInterceptor.class)
    public int createUser(String username, String email, String passwordSha256, String position, boolean Validate)
    {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordSha256);
        user.setPosition(position);
        user.setValidate(Validate);
        
        em.persist(user);
        em.flush();
        int userId = user.getId();
        return userId;

    }
    public List<UserDetails> getAllUsers() {
        LOG.info("getAllUsers");

        try {
            Query query = em.createQuery("SELECT u FROM User u");
            List<User> users = (List<User>) query.getResultList();
            return copyUserToDetails(users);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    
    public Collection<String> findUsername(Collection<Integer> userIds)
    {
        LOG.info("findUsernames");
        List<String> usernames = (List<String>) em.createQuery("SELECT u.username FROM User u where u.id IN ?1")
              .setParameter(1,userIds)
              .getResultList();
        return usernames;
    }

    @Interceptors(PositionInterceptor.class)
    public int validateAccount(Integer userId) {
        LOG.info("Validarea");
        User user = em.find(User.class, userId);
        user.setValidate(true);
        em.persist(user);
        em.flush();
        int localUserId = user.getId();
        return localUserId;
    }
    
    private List<UserDetails> copyUserToDetails(List<User> users) {
        List<UserDetails> detailsList = new ArrayList<>();

        for (User user : users) {
            UserDetails userDetails = user.clone();
            detailsList.add(userDetails);
        }

        return detailsList;
    }

}
