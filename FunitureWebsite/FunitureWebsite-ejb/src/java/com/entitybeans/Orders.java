/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entitybeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BAOTHI
 */
@Entity
@Table(name = "Orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByOrderID", query = "SELECT o FROM Orders o WHERE o.orderID = :orderID"),
    @NamedQuery(name = "Orders.findByOrderDate", query = "SELECT o FROM Orders o WHERE o.orderDate = :orderDate"),
    @NamedQuery(name = "Orders.findByDeliveryDate", query = "SELECT o FROM Orders o WHERE o.deliveryDate = :deliveryDate"),
    @NamedQuery(name = "Orders.findByShipAddress", query = "SELECT o FROM Orders o WHERE o.shipAddress = :shipAddress"),
    @NamedQuery(name = "Orders.findByUnitPrice", query = "SELECT o FROM Orders o WHERE o.unitPrice = :unitPrice"),
    @NamedQuery(name = "Orders.findByQuantity", query = "SELECT o FROM Orders o WHERE o.quantity = :quantity")})
public class Orders implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OrderID")
    private Integer orderID;
    @Column(name = "OrderDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Column(name = "DeliveryDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;
    @Size(max = 255)
    @Column(name = "ShipAddress")
    private String shipAddress;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Note")
    private String note;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "UnitPrice")
    private BigDecimal unitPrice;
    @Column(name = "Quantity")
    private Integer quantity;
    @Size(max = 50)
    @Column(name = "Status")
    private String status;
   @JoinColumn(name = "DeliveryID", referencedColumnName = "DeliveryID")
    @ManyToOne
    private Deliveries deliveryID;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "orders1")
    private Orders orders;
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Orders orders1;
    @JoinColumn(name = "PaymentID", referencedColumnName = "PaymentID")
    @ManyToOne
    private Payments paymentID;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    @ManyToOne
    private Users userID;
   

    public Orders() {
    }

    public Orders(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
 public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Deliveries getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(Deliveries deliveryID) {
        this.deliveryID = deliveryID;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Orders getOrders1() {
        return orders1;
    }

    public void setOrders1(Orders orders1) {
        this.orders1 = orders1;
    }

    public Payments getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Payments paymentID) {
        this.paymentID = paymentID;
    }

    public Users getUserID() {
        return userID;
    }

    public void setUserID(Users userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderID != null ? orderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.orderID == null && other.orderID != null) || (this.orderID != null && !this.orderID.equals(other.orderID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entitybeans.Orders[ orderID=" + orderID + " ]";
    }

   

   

   
    
}
