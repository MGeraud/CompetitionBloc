package com.geraud.competitionbloc.repositories;

import com.geraud.competitionbloc.models.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends ReactiveMongoRepository<Category , String> , CustomCategoryRepository {

}
