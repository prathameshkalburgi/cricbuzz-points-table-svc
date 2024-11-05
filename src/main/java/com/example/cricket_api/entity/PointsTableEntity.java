package com.example.cricket_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "PUBLIC", name = "POINTS_TABLE")
public class PointsTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Cricket_Teams_Seq")
    @SequenceGenerator(name = "Cricket_Teams_Seq", sequenceName = "CRICKET_TEAMS_SEQ", allocationSize = 1)
    private Integer id;

    @Column(name = "TEAM_NAME")
    private String teamName;

    @Column(name = "TOTAL_MATCHS_PLAYED")
    private Integer totalMatchsPlayed;

    @Column(name = "MATCHES_WON")
    private Integer matchesWon;

    @Column(name = "MATCHES_LOST")
    private Integer matchesLost;

    @Column(name = "MATCHES_DRAWN")
    private Integer matchesDrawn;

    @Column(name = "TOTAL_POINTS")
    private Integer totalPoints;

    @Column(name = "NET_RUN_RATE")
    private Double netRunRate;

}
