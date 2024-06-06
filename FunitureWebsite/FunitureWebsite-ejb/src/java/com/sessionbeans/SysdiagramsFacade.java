/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Sysdiagrams;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BAOTHI
 */
@Stateless
public class SysdiagramsFacade extends AbstractFacade<Sysdiagrams> implements SysdiagramsFacadeLocal {

    @PersistenceContext(unitName = "FunitureWebsite-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SysdiagramsFacade() {
        super(Sysdiagrams.class);
    }
    
}
