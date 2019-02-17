package sdek.supplier.utils;

import sdek.supplier.models.Dictionary;

import java.io.Serializable;

public class RequestSocket implements Serializable {
    private String orderNum;
    private String numUser;
    private Actions action;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getNumUser() {
        return numUser;
    }

    public void setNumUser(String numUser) {
        this.numUser = numUser;
    }

    public Actions getAction() {
        return action;
    }

    public void setAction(Actions action) {
        this.action = action;
    }
}
