package com.receipe.api.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Ingredient {

    @NotNull
    private String name;
    private String quantity;
}
