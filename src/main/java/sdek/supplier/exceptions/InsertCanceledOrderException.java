package sdek.supplier.exceptions;

public class InsertCanceledOrderException extends NullPointerException{

    public InsertCanceledOrderException(){
        super("error working DB");
    }

}
