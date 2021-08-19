package com.geraud.competitionbloc.controllers;

import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.models.ResultDto;
import com.geraud.competitionbloc.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/juges")
public class JudgeController {

    CategoryService categoryService;

    public JudgeController (CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Endpoint réservé aux juges afin de valider les blocs réussis par le compétiteur
     * @param resultDto DTO contenant l'id de la catégorie à laquelle appartient le compétiteur, le nom et prénom de ce dernier
     *                  ainsi aue le bloc réussi
     * @return les datas de la catégorie du compétiteur
     */
    @PostMapping("/validation")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Category> validBoulder(@RequestBody ResultDto resultDto){

        return categoryService.addBoulderSucces(resultDto);
    }
}
