package com.receipe.api.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDTO {
    String dishName;
    String recipeInstruction;
    List<IngredientDTO> ingredients;
}
