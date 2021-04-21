package com.geraud.competitionbloc.controllers;


import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
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
}
