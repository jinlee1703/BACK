package com.wefood.back.global;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * class: Message.
 *
 * @author JBumLee
 * @version 2024/08/10
 */

@Getter
@Setter
public class Message<T> {
    private int statusCode;
    private String message;
    private T data;
    public Message (int statusCode, String message,T data){
        this.message =message;
        this.statusCode = statusCode;
        this.data=data;
    }
    public Message (int statusCode, String message){
        this(statusCode,message,null);
    }
}