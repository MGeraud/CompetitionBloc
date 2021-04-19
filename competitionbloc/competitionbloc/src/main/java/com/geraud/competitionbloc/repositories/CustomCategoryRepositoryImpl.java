package com.geraud.competitionbloc.repositories;

import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.models.Competitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Mono;

import java.util.Map;

public class CustomCategoryRepositoryImpl implements CustomCategoryRepository{

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public CustomCategoryRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<Category> addNewCompetitor(String categoryId, Competitor competitor) {
        //Todo query to add new competitor to the list of a given category
        return null;
    }

    @Override
    public Mono<Category> addNewBoulder(String categoryId, String newBoulder) {
        //Todo query to add new boulder to the list of a given category
        return null;
    }

    @Override
    public Mono<Category> deleteBoulder(String categoryId, String boulderToDelete) {
        //Todo query to delete a boulder to the list of a given category
        return null;
    }

    @Override
    public Mono<Category> updateScore(String categoryId, Map<String, Integer> mapScore) {
        //Todo query to update the score list a given category
        return null;
    }
}
