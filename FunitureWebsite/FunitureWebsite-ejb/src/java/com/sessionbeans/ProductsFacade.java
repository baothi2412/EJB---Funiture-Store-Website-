/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Products;
import com.entitybeans.Subcategories;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BAOTHI
 */
@Stateless
public class ProductsFacade extends AbstractFacade<Products> implements ProductsFacadeLocal {

    @PersistenceContext(unitName = "FunitureWebsite-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductsFacade() {
        super(Products.class);
    }
     @Override
    public Products find(Object id) {
        return em.find(Products.class, id);
    }
    public List<Products> findBySubcategoryId(Subcategories subcategory) {
    return em.createQuery("SELECT p FROM Products p WHERE p.subcategoryID = :subcategory", Products.class)
             .setParameter("subcategory", subcategory)
             .getResultList();
}



}
