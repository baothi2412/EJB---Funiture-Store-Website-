/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Orders;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BAOTHI
 */
@Stateless
public class OrdersFacade extends AbstractFacade<Orders> implements OrdersFacadeLocal {

    @PersistenceContext(unitName = "FunitureWebsite-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }
     public void saveOrder(Orders order) {
        em.persist(order);
    }
    
}
