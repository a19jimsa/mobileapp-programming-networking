package com.example.networking;

public class Mountain {
    private String name;
    private int height;
    public Mountain(String name, int height){
        this.name = name;
        this.height = height;
    }
    @Override
    public String toString(){
        return name + " " +height;
    }
}
