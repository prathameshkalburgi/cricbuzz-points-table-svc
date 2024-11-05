package com.example.cricket_api.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Data
@Table(schema = "PUBLIC", name = "MATCHES")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class MatchesEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATCHES_SEQ")
//    @SequenceGenerator(name = "MATCHES_SEQ", sequenceName = "MATCHES_SEQ", allocationSize = 1)
    private Integer matchId;

    @Column(name = "TEAM1_ID")
    private Integer team1Id;

    @Column(name = "TEAM2_ID")
    private Integer team2Id;

    @Column(name = "WINNING_TEAM_ID")
    private Integer winningTeamId;

    @Column(name = "MATCH_DATE")
    private LocalDate matchDate;

    @Column(name = "IS_MATCHES_DRAWN")
    private Boolean isMatchesDrawn;
}
