package demo.devspringboot.WebBackEnd.common.exception;

public class WBEBussinessException extends  RuntimeException{
    public WBEBussinessException(String message){
        super(message);
    }
}
