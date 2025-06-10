package org.example.Model;

public class Customer{

     private int Id;
     private String user;
     private int moneyInCents;
     private String password;
     private  String realName;
     private  String email;

    public Customer(int i, String ss, int i1, String password) {
        this.Id = i;
        this.user = ss;
        this.moneyInCents = i1;
        this.password = password;
    }

    public  int getId(){
         return Id;
     }
    public String getUser(){
        return user;
    }
    public int getMoneyInCents(){
        return moneyInCents;
    }
    public String getPassword(){
        return password;
    }
    public String getRealName(){
        return realName;
    }

    public void setUser(String user){
        this.user = user;
    }
    public void setMoneyInCents(int moneyInCents){
        this.moneyInCents = moneyInCents;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

/*
    public Customer(int i, String ss, int i1) {
        this.Id = i;
        this.user = ss;
        this.moneyInCents = i1;
    }
 */