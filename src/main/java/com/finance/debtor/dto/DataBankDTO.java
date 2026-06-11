package com.finance.debtor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataBankDTO {
    @Schema(description = "Descrição do item comprado", example = "Café", required = true)
    @NotBlank(message = "A descrição não pode ser vazia")
    private String itemBought;

    @Schema(description = "Preço do item comprado", example = "10.50", required = true)
    @NotNull
    @PositiveOrZero(message = "O valor deve ser positivo")
    private Float price;

    @Schema(description = "Categoria do item comprado", example = "Alimentação", required = true)
    @NotBlank
    private String category;

    @Schema(description = "Método de pagamento", example = "Cartão de Crédito", required = true)
    @NotBlank
    private String paymentMethod;

    @Schema(description = "Data do lançamento", example = "2023-01-01", required = true)
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
