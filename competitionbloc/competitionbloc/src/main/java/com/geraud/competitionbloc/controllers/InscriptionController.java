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

    /**
     * Endpoint renvoyant le flux des différentes catégories présentes en database
     * @return la liste de toutes les catégories
     */
    @GetMapping("/all")
    public Flux<Category> getallCategories(){
        return categoryService.listAllCategories();
    }

    /**
     * Endpoint permetant l'inscription d'un compétiteur à une catégorie
     * @param category à laquelle doit être ajouté le compétiteur
     * @return
     */
    @PostMapping("/inscription")
    public Mono<Category> addCompetitor(@RequestBody Category category){
        return categoryService.addCompetitor(category);
    }
}
