package com.geraud.competitionbloc.service;

import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.models.Competition;
import com.geraud.competitionbloc.models.ResultDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface CategoryService {

    void createCategories(Competition competition);
    Flux<Category> listAllCategories();
    Mono<Category> addBoulder(Category category);
    Mono<Category> deleteBoulder(Category category);
    Mono<Category> addCompetitor(Category category);
    Mono<Category> addBoulderSucces(ResultDto resultDto);
}
