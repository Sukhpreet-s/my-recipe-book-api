package com.receipe.api.controller.dto;

import com.receipe.api.model.Ingredient;
import lombok.Data;

import java.util.List;

@Data
public class RecipeDTO {
    String dishName;
    String recipeInstruction;
    List<IngredientDTO> ingredients;
}
