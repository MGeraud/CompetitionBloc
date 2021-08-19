package com.geraud.competitionbloc.repositories;

import com.geraud.competitionbloc.models.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface pour la gestion de la database concernant les catégories
 * extension de SpringData gérant les BDD MongoDB en réactive programming
 * extension d'un interface personnalisé pour les requête particulières
 */
@Repository
public interface CategoryRepository extends ReactiveMongoRepository<Category , String> , CustomCategoryRepository {

}
