package sdek.supplier.models;

import sdek.supplier.mappers.Dictionary;

import java.util.Date;

public class CanceledOrders implements Dictionary {

    private Long id;
    private Order order;
    private Date cancelDate;
    private Boolean check;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getCanceledOrder() {
        return order;
    }

    public void setCanceledOrder(Order order) {
        this.order = order;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Boolean isChecked() {
        return check;
    }

    public void setChecked(Boolean check) {
        this.check = check;
    }
}
