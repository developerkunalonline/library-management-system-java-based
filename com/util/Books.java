package com.util;

public class Books {
    
    public int id;
    public String name;
    public boolean isAvilabal = true;
    
    public Books(int id,String name){
        this.id = id;
        this.name = name;
    }
    public Books(int id,String name,int avalavlity){
        this.id = id;
        this.name = name;
        if(avalavlity == 1){
            isAvilabal=true;
        }
        else {isAvilabal = false;}
    }
    @Override
    public String toString(){
        return "\nid --> "+id+" name --> "+name+"\n";
    }

}
