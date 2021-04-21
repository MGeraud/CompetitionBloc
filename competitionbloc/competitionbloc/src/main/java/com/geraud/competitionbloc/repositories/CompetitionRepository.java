package com.geraud.competitionbloc.repositories;

import com.geraud.competitionbloc.models.Competition;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CompetitionRepository extends ReactiveMongoRepository<Competition , String> {
}
