package com.receipe.api.model;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

public class Ingredient {

    @NotNull
    private String name;
    private String quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
