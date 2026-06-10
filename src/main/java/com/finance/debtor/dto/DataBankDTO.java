package com.finance.debtor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

public class DataBankDTO {
    @NotBlank(message = "A descrição não pode ser vazia")
    private String itemBought;

    @NotNull
    @PositiveOrZero(message = "O valor deve ser positivo")
    private Float price;

    @NotBlank
    private String category;

    @NotBlank
    private String paymentMethod;

    @NotNull
    private LocalDate date;

//   Getters and Setters
    public String getItemBought() {
        return itemBought;
    }
    public void setItemBought(String itembBought) {
        this.itemBought = itembBought;
    }

    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
