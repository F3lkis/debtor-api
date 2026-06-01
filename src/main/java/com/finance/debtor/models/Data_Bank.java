package com.finance.debtor.models;

import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDate;

@Entity
public class Data_Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ProductId;

    @Column
    private String itemBought;

    @Column
    private float price;

    @Column
    private String category;

    @Column
    private LocalDate date;

    @Column
    private String paymentMethod;


//    List connection
    @ManyToOne
    @JoinColumn(name = "user_id") // Cria a coluna de chave estrangeira
    private User user;

//    Quantity pannel

    @Column
    private int activeCategories;

    @Column
    private int realeases;

//    Getters and Setters

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public long getProductId(){
        return ProductId;
    }
    public void setProductId(long id){
        this.ProductId = id;
    }

    public String getItemBought(){
        return itemBought;
    }
    public void setItemBought(String itemBought){
        this.itemBought = itemBought;
    }

    public float getPrice(){
        return price;
    }
    public void setPrice(float price){
        this.price = price;
    }

    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }

    public LocalDate getDate(){
        return date;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }

    public String getPaymentMethod(){
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }

    public int getActiveCategories(){
        return activeCategories;
    }
    public void setActiveCategories(int activeCategories){
        this.activeCategories = activeCategories;
    }

    public int getRealeases(){
        return realeases;
    }
    public void setRealeses(int realeases){
        this.realeases = realeases;
    }

}
