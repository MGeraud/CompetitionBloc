package com.geraud.competitionbloc.repositories;

import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.models.ResultDto;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * interface pour les requêtes personnalisées en réactive
 */
public interface CustomCategoryRepository {


    Mono<Category> addNewBoulder(Category category);
    Mono<Category> deleteBoulder(Category category);
    Mono<Category> updateScore(String categoryId, Map<String, Integer> mapScore);
    Mono<Category> addNewCompetitor(Category category);
    Mono<Category> validBoulder(ResultDto resultDto);

}
