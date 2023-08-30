package com.canvs.customer.exception;

import com.canvs.customer.controllers.CustomerServlet;

public class CustomerServletException extends RuntimeException{
    public CustomerServletException(String msg){
        super(msg);
    }
}
