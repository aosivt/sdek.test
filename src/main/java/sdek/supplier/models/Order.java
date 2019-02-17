package sdek.supplier.models;

public class Order extends Dictionary{

    private String orderNum;

    public Order(){}
    public Order(Long id,String orderNum){
        this.id = id;
        this.orderNum = orderNum;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

}
