package com.canvs.customer.exception;

public class BaseDAOException extends RuntimeException{
    public BaseDAOException(String msg){
        super(msg);
    }
}
