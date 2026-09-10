package org.skypro.skyshop.exception;

public class NoSuchProductException extends RuntimeException{

    public NoSuchProductException(String id){
        super("Продукта с id: " + id + " не существует");
    }
}
