package com.geraud.competitionbloc;

import com.geraud.competitionbloc.models.Competitor;
import com.geraud.competitionbloc.repositories.CompetitorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class CompetitionblocApplication {

//	@Bean
//	CommandLineRunner competitors(CompetitorRepository competitorRepository){
//
//		return args -> {
//
//
//			competitorRepository.deleteAll()
//					.subscribe(null,null, () -> {
//						Stream.of(new Competitor( "Adam", "Ondra", "Escapade", Set.of(1,3,5,6,8,10)),
//								new Competitor( "Eve", "Lili", "Lavaur", Set.of(2,4,6,8,10)),
//								new Competitor( "Jackob", "Schubert", "Bloc a Bloc", Set.of(1,2,3,4,5)),
//								new Competitor( "Mickael", "Mawen", "Bloc a bloc", Set.of(5,9,14)),
//								new Competitor( "Tomoa", "Narazaki", "Escapade", Set.of(2,3,4,14,15))
//						)
//								.forEach(competitor -> {
//									competitorRepository.save(competitor)
//											.subscribe(System.out::println);
//								});
//					});
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(CompetitionblocApplication.class, args);
	}

}
