/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbeans;

import com.entitybeans.Payments;
import com.sessionbeans.PaymentsFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author BAOTHI
 */
@Named(value = "paymentMB")
@RequestScoped
public class PaymentMB {

    @EJB
    private PaymentsFacadeLocal paymentsFacade;

    private Payments payment;

    public PaymentMB() {
        payment = new Payments();
    }

    public String addPayments() {
        System.out.println("ID: " + payment.getPaymentID());
        if (payment.getPaymentID()== null) {
            paymentsFacade.create(payment);
        } else {
            paymentsFacade.edit(payment);
        }
        return "payment";
    }

    public String deletePayments(int id) {
        paymentsFacade.remove(paymentsFacade.find(id));
        return "payment";
    }

    public String findPaymentbyID(int id) {
        payment = paymentsFacade.find(id);
        return "payment";
    }

    public List<Payments> showAllPayments() {
        return paymentsFacade.findAll();
    }

    public String resetForm() {
        payment = null;
        return "payment";
    }

    public Payments getPayment() {
        return payment;
    }

    public void setPayment(Payments payment) {
        this.payment = payment;
    }
    

}
