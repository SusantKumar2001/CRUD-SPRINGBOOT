package com.skb.exception;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String msg){
        super(msg);
    }
}
