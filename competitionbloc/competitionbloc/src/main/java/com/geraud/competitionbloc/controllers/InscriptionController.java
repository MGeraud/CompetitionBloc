package com.geraud.competitionbloc.controllers;


import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/categorie")
public class InscriptionController {

    CategoryRepository categoryRepository;

    public InscriptionController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/all")
    public Flux<Category> getallCategories(){
        return categoryRepository.findAll();
    }

    @PostMapping("/inscription")
    public Mono<Category> addCompetitor(@RequestBody Category category){
        return categoryRepository.addNewCompetitor(category);
    }
}
