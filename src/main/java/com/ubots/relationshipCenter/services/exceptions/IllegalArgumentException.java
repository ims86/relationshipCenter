package com.ubots.relationshipCenter.services.exceptions;

public class IllegalArgumentException extends RuntimeException{

    public IllegalArgumentException(Object msg){
        super((String) msg);
    }
}
