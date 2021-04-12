package com.geraud.competitionbloc.repositories;

import com.geraud.competitionbloc.models.Competitor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitorRepository extends ReactiveMongoRepository<Competitor, String> {
}
