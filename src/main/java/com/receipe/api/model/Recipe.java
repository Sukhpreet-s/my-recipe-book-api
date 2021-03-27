package com.receipe.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class Recipe {

    @Id
    private String id;
    @NotNull
    private String dishName;
    @NotNull
    private String recipeInstruction;
    private List<Ingredient> ingredients;

    public Recipe(@NotNull String dishName, @NotNull String recipeInstruction, List<Ingredient> ingredients) {
        this.dishName = dishName;
        this.recipeInstruction = recipeInstruction;
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + "\nDish name: " + this.dishName + "\nRecipe instruction: " + this.recipeInstruction;
    }
}
