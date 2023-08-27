package com.receipe.api.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDTO {
    String id;
    String dishName;
    String recipeInstruction;
    List<IngredientDTO> ingredients;
    List<String> images;
}
