package sdek.supplier.models;

import java.util.Date;

public class CanceledOrders implements Dictionary{

    private Long id;
    private Long orderId;
    private Order order;
    private Date cancelDate;

    public CanceledOrders(){}
    public CanceledOrders(Long id){
        this.id = id;
        orderId = id;
        cancelDate = new Date();
    }

    public CanceledOrders(Long id, Order order){
        this.id = id;
        this.order = order;
        cancelDate = new Date();
    }
    public CanceledOrders(Order order){
        this.order = order;
        orderId = order.getId();
        cancelDate = new Date();
    }




    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
