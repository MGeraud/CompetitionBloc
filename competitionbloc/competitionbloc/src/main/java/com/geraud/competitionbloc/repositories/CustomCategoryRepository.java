package com.geraud.competitionbloc.repositories;

import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.models.Competitor;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface CustomCategoryRepository {

    Mono<Category> addNewCompetitor(String categoryId , Competitor competitor);
    Mono<Category> addNewBoulder(String categoryId , String newBoulder);
    Mono<Category> deleteBoulder(String categoryId , String boulderToDelete);
    Mono<Category> updateScore(String categoryId, Map<String, Integer> mapScore);

}
