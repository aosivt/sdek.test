package sdek.supplier.models;

import sdek.supplier.mappers.Dictionary;

import java.util.Date;

public class CanceledOrders implements Dictionary {

    private Long id;
    private Order order;
    private Date cancelDate;

    public CanceledOrders(){}
    public CanceledOrders(Long id, Order order){
        this.id = id;
        this.order = order;
        cancelDate = new Date();
    }
    public CanceledOrders(Order order){
        this.id = order.getId();
        this.order = order;
        cancelDate = new Date();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    }
