package com.geraud.competitionbloc.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Competitor {

    private String firstname;
    private String lastname;
    private String club;
    private Set<String> boulderSuccess;

    public Competitor(String firstname, String lastname, String club) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.club = club;
    }

}
