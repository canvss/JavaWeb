package com.canvs.customer.exception;

import java.io.File;
import java.util.Date;

public class DateException extends Exception{
    static final long serialVersionUID = -3387516323124229948L;
    public DateException(){}
    public DateException(String message){
        super(message);
    }
}
