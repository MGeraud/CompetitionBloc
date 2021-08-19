package com.geraud.competitionbloc.controllers;


import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/categorie")
public class InscriptionController {

    CategoryService categoryService;

    public InscriptionController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public Flux<Category> getallCategories(){
        return categoryService.listAllCategories();
    }

    @PostMapping("/inscription")
    public Mono<Category> addCompetitor(@RequestBody Category category){
        return categoryService.addCompetitor(category);
    }
}
