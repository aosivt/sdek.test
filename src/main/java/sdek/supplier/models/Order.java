package sdek.supplier.models;

import sdek.supplier.mappers.Dictionary;

public class Order implements Dictionary {
    private Long id;
    private String orderNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

}
