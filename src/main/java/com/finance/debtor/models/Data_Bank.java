package com.finance.debtor.models;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Data_Bank {

    @Schema(description = "Identificador único do produto", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ProductId;

    @Column
    @Schema(description = "Descrição do item comprado", example = "Café", required = true)
    private String itemBought;

    @Column
    @Schema(description = "Preço do item comprado", example = "10.50", required = true)
    private float price;

    @Column
    @Schema(description = "Categoria do item comprado", example = "Alimentação", required = true)
    private String category;

    @Column
    @Schema(description = "Data do lançamento", example = "2023-01-01", required = true)
    private LocalDate date;

    @Column
    @Schema(description = "Método de pagamento", example = "Cartão de Crédito", required = true)
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
