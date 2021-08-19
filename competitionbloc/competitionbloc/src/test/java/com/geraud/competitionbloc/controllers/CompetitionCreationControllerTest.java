package com.geraud.competitionbloc.controllers;


import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.models.Competition;
import com.geraud.competitionbloc.service.CategoryService;
import com.geraud.competitionbloc.service.CompetitionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import static org.mockito.Mockito.times;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompetitionCreationControllerTest {

    @MockBean
    private CompetitionService competitionService;
    @MockBean
    private CategoryService categoryService;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    @WithMockUser( authorities = { "gestion" })
    void getAllCompetitions() {

        Competition competition = new Competition("1" , "comp" , "2021" , List.of("categorie une"));
        Flux<Competition> competitions = Flux.just(competition);

        Mockito.when(competitionService.listAll())
                .thenReturn(competitions);

        webTestClient.get().uri("/competition/all")
                .header(HttpHeaders.ACCEPT, "application/json")

                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Competition.class);

        Mockito.verify(competitionService, times(1)).listAll();
    }


    @Test
    @WithMockUser( authorities = { "juge" })
    void nonAuthorisedUserOnListAllCompetitions() {

        webTestClient.get().uri("/competition/all")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isForbidden();
    }


    @Test
    @WithMockUser( authorities = { "gestion" })
    void createCompetition() {
        Competition competition = new Competition("1" , "comp" , "2021" , List.of("categorie une"));
        Mockito.when(competitionService.saveCompetition(competition))
                .thenReturn(Mono.just(competition));

        webTestClient.post().uri("/competition/creation")
                .header(HttpHeaders.ACCEPT, "application/json")
                .bodyValue(competition)
                .exchange()
                .expectStatus().isCreated()
                .expectBodyList(Competition.class);
    }


    @Test
    @WithMockUser(authorities = { "gestion" })
    void addboulder() {
        Category category = new Category("id1" , null, null, List.of("abc"),null , null);
        Mockito.when(categoryService.addBoulder(category)).thenReturn(Mono.just(category));

        webTestClient.post().uri("/competition/add")
                .header(HttpHeaders.ACCEPT, "application/json")
                .bodyValue(category)
                .exchange()
                .expectStatus().isCreated()
                .expectBodyList(Category.class);
    }

    @Test
    @WithMockUser( authorities = { "juge" })
    void nonAuthorisedUserOnCreateCompetitions() {

        webTestClient.get().uri("/competition/creation")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isForbidden();

    }

    @Test
    @WithMockUser( authorities = { "juge" })
    void nonAuthorisedUserOnAddboulder() {

        webTestClient.get().uri("/competition/add")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isForbidden();

    }
}