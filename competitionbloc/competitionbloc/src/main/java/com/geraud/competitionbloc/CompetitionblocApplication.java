package com.geraud.competitionbloc;

import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.models.Competition;
import com.geraud.competitionbloc.models.Competitor;
import com.geraud.competitionbloc.repositories.CategoryRepository;
import com.geraud.competitionbloc.repositories.CompetitionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.stream.Stream;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
public class CompetitionblocApplication {

	@Bean
	CommandLineRunner competitors(CompetitionRepository competitionRepository , CategoryRepository categoryRepository){

		return args -> {
			List<String> categories = new ArrayList<>();
			categories.add("Cadet Garcons");
			categories.add("Benjamin Filles");
			List<String> boulders = new ArrayList<>();
			boulders.add("1");
			boulders.add("3");
			boulders.add("3bis");
			Map<String,Integer> scores = new HashMap<>();
			List<Competitor> competitorListBenjFille = new ArrayList<>();
			List<Competitor> competitorListCadetGarcons = new ArrayList<>();
			competitorListCadetGarcons.add(new Competitor( "Adam", "Ondra", "Escapade"));
			competitorListBenjFille.add(new Competitor( "Eve", "Lili", "Lavaur"));
			competitorListCadetGarcons.add(new Competitor( "Jackob", "Schubert", "Bloc a Bloc"));
			competitorListCadetGarcons.add(new Competitor( "Mickael", "Mawen", "Bloc a bloc"));
			competitorListCadetGarcons.add(new Competitor("Tomoa", "Narazaki", "Escapade"));

			Competition compet = new Competition("Open2021" , "open" , "2021", categories );
			Competition fakeCompet = new Competition("Fakecompet" , "close" , "2021", categories);

			competitionRepository.deleteAll()
					.subscribe(null , null , () -> {
						Stream.of(compet , fakeCompet)
								.forEach( competition -> {
									competitionRepository.save(competition)
											.subscribe(System.out::println);
								});
					});

			categoryRepository.deleteAll()
					.subscribe(null , null , () ->{
						Stream.of(new Category( "1" , "Benjamin Fille" , competitorListBenjFille , boulders , scores , compet.getCompetitionName()+ " " + compet.getYear() ),
								new Category("2gdf" , "Cadet Garcons" , competitorListCadetGarcons , boulders ,scores ,compet.getCompetitionName()+ " " + compet.getYear()))
								.forEach(category -> {
									categoryRepository.save(category)
											.subscribe(System.out::println);
								});
					});

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(CompetitionblocApplication.class, args);
	}

}
