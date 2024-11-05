package com.example.cricket_api.model;


import lombok.*;

import java.time.LocalDate;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MatchResultPayload {
    private Integer matchId;
    private CricketTeam team1;
    private CricketTeam team2;
    private Integer winningTeamId;
    private LocalDate matchDate;
    private Boolean isDraw;
    private Double nrrDelta;
}
