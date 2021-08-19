package com.geraud.competitionbloc.repositories;

import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.models.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Implémentation de l'interface de requetes personnalisées pour BDD MongoDB driver réactive
 */
public class CustomCategoryRepositoryImpl implements CustomCategoryRepository{

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public CustomCategoryRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    /**
     * Ajout des blocs pour une catégorie précise
     * @param category catégorie comprenant sont set de blocs à mettre à jour en BDD
     * @return la catégorie modifiée
     */
    @Override
    public Mono<Category> addNewBoulder(Category category) {
        Query query = new Query(Criteria.where("_id").is(category.getId()));
        Update update = new Update().addToSet("boulders").each(category.getBoulders());
        return reactiveMongoTemplate.findAndModify(query,update,Category.class);
    }
    /**
     * Retrait des blocs pour une catégorie précise
     * @param category catégorie comprenant sont set de blocs à mettre à jour en BDD
     * @return la catégorie modifiée
     */
    @Override
    public Mono<Category> deleteBoulder(Category category) {

        Query query = new Query(Criteria.where("_id").is(category.getId()));
        Update update = new Update().pull("boulders" , category.getBoulders().get(0));

        return reactiveMongoTemplate.findAndModify(query,update,Category.class);
    }


    @Override
    public Mono<Category> updateScore(String categoryId, Map<String, Integer> mapScore) {
        //Todo on next project :  query to update the score list a given category waiting for rules
        return null;
    }

    /**
     * Ajout des compétiteurs pour une catégorie précise
     * @param category catégorie comprenant sont set de compétiteurs à mettre à jour en BDD
     * @return la catégorie modifiée
     */
    @Override
    public Mono<Category> addNewCompetitor(Category category) {
        Query query = new Query(Criteria.where("_id").is(category.getId()));
        Update update = new Update().addToSet("competitors").each(category.getCompetitors());
        return reactiveMongoTemplate.findAndModify(query,update,Category.class);
    }

    /**
     * Ajout de bloc validés par le compétiteur
     * @param resultDto DTO comprenant l'id de la catégorie à laquelle appartient le compétiteur, nom et prénom de celui-ci, bloc à valider
     * @return la catégorie modifiée
     */
    @Override
    public Mono<Category> validBoulder(ResultDto resultDto) {
        Query query = new Query(new Criteria().andOperator(
                Criteria.where("_id").is(resultDto.getCatId()),
                Criteria.where("competitors.firstname").is(resultDto.getCompetitorFName()),
                Criteria.where("competitors.lastname").is(resultDto.getCompetitorLName())
        ));
        Update update = new Update().addToSet("competitors.$.boulderSuccess").each(resultDto.getBoulderDone());
        return reactiveMongoTemplate.findAndModify(query,update,Category.class);

    }
}
