package com.geraud.competitionbloc.controllers;

import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.models.ResultDto;
import com.geraud.competitionbloc.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/juges")
public class JudgeController {
    CategoryRepository categoryRepository;

    public JudgeController (CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/validation")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Category> validBoulder(@RequestBody ResultDto resultDto){
        System.out.println(resultDto);
        return categoryRepository.validBoulder(resultDto);
    }
}
