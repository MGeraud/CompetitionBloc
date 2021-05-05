package com.geraud.competitionbloc.repositories;

import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.models.Competitor;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;

import java.util.Map;

public class CustomCategoryRepositoryImpl implements CustomCategoryRepository{

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public CustomCategoryRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }


    @Override
    public Mono<Category> addNewBoulder(Category category) {
        Query query = new Query(Criteria.where("_id").is(category.getId()));
        Update update = new Update().addToSet("boulders").each(category.getBoulders());
        return reactiveMongoTemplate.findAndModify(query,update,Category.class);
    }

    @Override
    public Mono<Category> deleteBoulder(Category category) {

        Query query = new Query(Criteria.where("_id").is(category.getId()));
        Update update = new Update().pull("boulders" , category.getBoulders().get(0));

        return reactiveMongoTemplate.findAndModify(query,update,Category.class);
    }

    @Override
    public Mono<Category> updateScore(String categoryId, Map<String, Integer> mapScore) {
        //Todo query to update the score list a given category
        return null;
    }
}
