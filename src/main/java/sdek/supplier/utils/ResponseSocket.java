package sdek.supplier.utils;

import sdek.supplier.models.CanceledOrders;
import sdek.supplier.models.Dictionary;

import java.io.Serializable;

public class ResponseSocket implements Serializable {
    private String message;
    private Dictionary dictionary;
    private Actions action;
    private String numUser;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Actions getAction() {
        return action;
    }

    public void setAction(Actions action) {
        this.action = action;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public String getNumUser() {
        return numUser;
    }

    public void setNumUser(String numUser) {
        this.numUser = numUser;
    }
}
