package com.receipe.api.controller;

import com.receipe.api.common.utils.ModelMapperUtil;
import com.receipe.api.controller.dto.RecipeDTO;
import com.receipe.api.model.Ingredient;
import com.receipe.api.model.Recipe;
import com.receipe.api.model.ResponseDTO;
import com.receipe.api.service.RecipeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping(value = "/recipe")
public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> getRecipes() {
        return recipeService.getAll();
    }

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable("id") String id) throws Exception{
        return recipeService.getRecipeById(id);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addRecipe(@RequestBody @Validated RecipeDTO dto) {
        ModelMapper mapper = ModelMapperUtil.getInstance();
        Recipe recipe = mapper.map(dto, Recipe.class);

        ResponseDTO response = recipeService.addRecipe(recipe);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateRecipe(@RequestBody @Validated RecipeDTO dto) throws Exception {
        ModelMapper mapper = ModelMapperUtil.getInstance();
        Recipe recipe = mapper.map(dto, Recipe.class);

        ResponseDTO responseDTO = recipeService.updateRecipe(recipe);
        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteRecipe(@PathVariable("id") String id) throws Exception {
        ResponseDTO responseDTO = recipeService.deleteRecipeById(id);
        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }
}
