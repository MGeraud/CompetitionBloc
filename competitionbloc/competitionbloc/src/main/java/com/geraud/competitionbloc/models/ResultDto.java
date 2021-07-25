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
public class ResultDto {

    private String catId;
    private String competitorFName;
    private String competitorLName;
    private Set<String>  boulderDone;
}
