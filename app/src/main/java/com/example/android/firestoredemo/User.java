package com.example.android.firestoredemo;

public class User {

    private String name;
    private String email;
    private String referral;
    private int balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public User(String name, String email, String referral, int balance) {
        this.name = name;
        this.email = email;
        this.referral = referral;
        this.balance = balance;
    }

    public User() {
    }

}
