package com.receipe.api.service;

import com.receipe.api.model.Recipe;
import com.receipe.api.model.ResponseDTO;
import com.receipe.api.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeService (RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeById(String id) throws Exception {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(!recipe.isPresent()) {
            throw new Exception();
        }
        return recipe.get();
    }

    public ResponseDTO addRecipe(Recipe recipe) {
        recipeRepository.insert(recipe);
        return new ResponseDTO(HttpStatus.OK, "Success");
    }

    public ResponseDTO updateRecipe(Recipe newRecipe) throws Exception {
        Optional<Recipe> foundRecipe = recipeRepository.findById(newRecipe.getId());
        if(!foundRecipe.isPresent()) {
            throw new Exception("Recipe not found!"); //create RecipeNotExistException and throw that
        }
        recipeRepository.save(newRecipe);
        return new ResponseDTO(HttpStatus.OK, "Success");
    }

    public ResponseDTO deleteRecipeById(String id) throws Exception {
        Optional<Recipe> foundRecipe = recipeRepository.findById(id);
        if(!foundRecipe.isPresent()) {
            throw new Exception("Recipe not found");
        }
        recipeRepository.deleteById(id);
        return new ResponseDTO(HttpStatus.OK, "Success");
    }
}
