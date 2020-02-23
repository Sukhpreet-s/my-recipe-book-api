package com.receipe.api.model;

import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Recipe {

    @Id
    private String id;
    @NotNull
    private String dishName;
    @NotNull
    private String recipeInstruction;
    private List<Ingredient> ingredients;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getRecipeInstruction() {
        return recipeInstruction;
    }

    public void setRecipeInstruction(String recipeInstruction) {
        this.recipeInstruction = recipeInstruction;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Recipe(String id, @NotNull String dishName, @NotNull String recipeInstruction, List<Ingredient> ingredients) {
        this.id = id;
        this.dishName = dishName;
        this.recipeInstruction = recipeInstruction;
        this.ingredients = ingredients;
    }
//    @Override
//    public String toString() {
//        return "Id: " + this.id + "\nDish name: " + this.dishName + "\nRecipe instruction: " + this.recipeInstruction;
//    }
}
