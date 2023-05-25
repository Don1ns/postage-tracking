package me.don1ns.postagetracking.exception;

public class PostOfficeNotFoundException extends RuntimeException{
    public PostOfficeNotFoundException() {
        super("Post Office is not found");
    }
}
