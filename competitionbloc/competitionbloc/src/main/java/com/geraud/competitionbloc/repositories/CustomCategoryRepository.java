package com.geraud.competitionbloc.repositories;

import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.models.Competitor;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface CustomCategoryRepository {


    void addNewBoulder(Category category);
    Mono<Category> deleteBoulder(Category category);
    Mono<Category> updateScore(String categoryId, Map<String, Integer> mapScore);

}
