package sdek.supplier.exceptions;

public class CanceledOrderExistException extends Exception {

    public CanceledOrderExistException(){
        super("Canceled order exist inside queue");
    }

}
