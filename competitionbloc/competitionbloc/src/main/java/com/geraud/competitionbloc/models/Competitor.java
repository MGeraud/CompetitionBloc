package com.geraud.competitionbloc.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document (collection = "departemental")
public class Competitor {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String category;
    private Set<Integer> boulderSuccess;
}
