package com.receipe.api.controller;

import com.receipe.api.model.Recipe;
import com.receipe.api.model.ResponseDTO;
import com.receipe.api.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe")
    public List<Recipe> getRecipes() {
        return recipeService.getAll();
    }

    @GetMapping("/recipe/{id}")
    public Recipe getRecipeById(@PathVariable("id") String id) throws Exception{
        return recipeService.getRecipeById(id);
    }

    @PostMapping("/recipe")
    public ResponseEntity<ResponseDTO> addRecipe(@RequestBody @Validated Recipe recipe) {
        ResponseDTO response = recipeService.addRecipe(recipe);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/recipe")
    public ResponseEntity<ResponseDTO> updateRecipe(@RequestBody @Validated Recipe recipe) throws Exception {
        ResponseDTO responseDTO = recipeService.updateRecipe(recipe);
        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }

    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<ResponseDTO> deleteRecipe(@PathVariable("id") String id) throws Exception {
        ResponseDTO responseDTO = recipeService.deleteRecipeById(id);
        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }
}
