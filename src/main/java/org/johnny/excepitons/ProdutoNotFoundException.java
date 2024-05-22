package com.bd.crud.exception;

public class ProdutoNotFoundException extends RuntimeException{
    public ProdutoNotFoundException(int id){
        super("Could not found the Product with id "+ id);
    }
}