package sdek.supplier.models;

public class Order {
    private Long id;
    private String orderNum;

    public Order(){}
    public Order(Long id,String orderNum){
        this.id = id;
        this.orderNum = orderNum;
    }

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
