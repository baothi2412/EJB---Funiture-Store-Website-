/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbeans;

import com.entitybeans.Orders;
import com.sessionbeans.DeliveriesFacadeLocal;
import com.mbeans.CartProcessMB;
import com.sessionbeans.OrdersFacadeLocal;
import com.sessionbeans.PaymentsFacadeLocal;
import com.sessionbeans.UsersFacadeLocal;
import com.sessionbeansprocess.CartSBLocal;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author BAOTHI
 */
@Named(value = "orderMB")
@RequestScoped
public class OrderMB {

    @EJB
    private DeliveriesFacadeLocal deliveriesFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    @EJB
    private PaymentsFacadeLocal paymentsFacade;

    @EJB
    private UsersFacadeLocal usersFacade;

    @Inject
    private CartProcessMB cartProcessMB;
    @Inject
    private LoginMB loginMB;
    private int userID;
    private int paymentID;
    private int deliveryID;
    private Date currentDate;
    private Orders order;
    private int sumProduct;
    private Double sumCart;

    public String addOrders() {
        if (order.getOrderID() == null) {
            // Kiểm tra xem khách hàng đã đăng nhập chưa
            if (loginMB.getLoggedInUsername() == null) {
                // Nếu chưa đăng nhập, hiện thông báo
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Bạn chưa đăng nhập ", "Bạn chưa đăng nhập."));
                return null; // Không điều hướng, chỉ hiện thông báo
            }

            // Khách hàng đã đăng nhập, tiếp tục thêm đơn hàng
            sumProduct = cartProcessMB.sumProductCart();
            sumCart = cartProcessMB.sumCart();
            currentDate = new Date();

            order.setOrderDate(currentDate);
            order.setDeliveryID(deliveriesFacade.find(deliveryID));
            order.setPaymentID(paymentsFacade.find(paymentID));
            order.setUserID(usersFacade.find(userID));
            order.setUnitPrice(BigDecimal.valueOf(sumCart));
            order.setQuantity(sumProduct);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DAY_OF_MONTH, 3);
            Date deliveryDate = calendar.getTime();
            order.setDeliveryDate(deliveryDate);

            ordersFacade.create(order);
        } else {
            Orders oldOrder = ordersFacade.find(order.getOrderID());

            // Cập nhật các giá trị không thay đổi
            sumProduct = cartProcessMB.sumProductCart();
            sumCart = cartProcessMB.sumCart();
            currentDate = new Date();
 order.setShipAddress(oldOrder.getShipAddress());
        order.setNote(oldOrder.getNote());
            // Cập nhật các thuộc tính của Order mới
            order.setOrderDate(currentDate);
            order.setDeliveryID(oldOrder.getDeliveryID());
            order.setPaymentID(oldOrder.getPaymentID());
            order.setUserID(oldOrder.getUserID());
            order.setUnitPrice(oldOrder.getUnitPrice());
            order.setQuantity(oldOrder.getQuantity());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DAY_OF_MONTH, 3);
            Date deliveryDate = calendar.getTime();
            order.setDeliveryDate(deliveryDate);

            // Lưu lại thông tin mới của Order
            ordersFacade.edit(order);
            return "order";
        }

        return "Inf";
    }

    public String resetForm() {
        order = null;
        return "order";
    }

    public String deleteOrder(int id) {
        ordersFacade.remove(ordersFacade.find(id));
        return "order";
    }

    public String findOrderbyID(int id) {
        order = ordersFacade.find(id);
        return "order";
    }

    public OrderMB() {
        currentDate = new Date();
        order = new Orders();
    }

    public List<Orders> showAllOrders() {
        return ordersFacade.findAll();
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public int getSumProduct() {
        return sumProduct;
    }

    public void setSumProduct(int sumProduct) {
        this.sumProduct = sumProduct;
    }

    public Double getSumCart() {
        return sumCart;
    }

    public void setSumCart(Double sumCart) {
        this.sumCart = sumCart;
    }

}
