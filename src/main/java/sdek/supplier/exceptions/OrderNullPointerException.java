package sdek.supplier.exceptions;

public class OrderNullPointerException extends NullPointerException {

    public OrderNullPointerException(){
        super("Order is empty");
    }

}
