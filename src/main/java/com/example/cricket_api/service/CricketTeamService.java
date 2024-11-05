package com.example.cricket_api.service;

import com.example.cricket_api.entity.MatchesEntity;
import com.example.cricket_api.entity.PointsTableEntity;
import com.example.cricket_api.model.MatchResultPayload;
import com.example.cricket_api.repository.CricketTeamRepository;
import com.example.cricket_api.repository.MatchsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CricketTeamService {

    @Autowired
    private CricketTeamRepository cricketTeamRepository;

    @Autowired
    private MatchsRepository matchsRepository;

    public void updateMatchesResult(MatchResultPayload matchResultPayload) {
        updateMatchesTable(matchResultPayload);
        updatePointsTable(matchResultPayload);
    }

    public void updateMatchesTable(MatchResultPayload matchResultPayload) {
        MatchesEntity match = new MatchesEntity();
        match.setMatchId(matchResultPayload.getMatchId());
        match.setWinningTeamId(matchResultPayload.getWinningTeamId());
        match.setTeam1Id(matchResultPayload.getTeam1().getId());
        match.setTeam2Id(matchResultPayload.getTeam2().getId());
        match.setMatchDate(matchResultPayload.getMatchDate());
        match.setIsMatchesDrawn(matchResultPayload.getIsDraw());
        matchsRepository.save(match);
    }

    public void updatePointsTable(MatchResultPayload matchResultPayload) {
        PointsTableEntity team1 = cricketTeamRepository.findById(matchResultPayload.getTeam1().getId())
                .orElseThrow(() -> new RuntimeException("Team not found"));
        PointsTableEntity team2 = cricketTeamRepository.findById(matchResultPayload.getTeam2().getId())
                .orElseThrow(() -> new RuntimeException("Team not found"));
        if(matchResultPayload.getIsDraw()) {
            team1.setTotalPoints(team1.getTotalPoints() + 1);
            team2.setTotalPoints(team2.getTotalPoints() + 1);
            team1.setMatchesDrawn(team1.getMatchesDrawn() + 1);
            team2.setMatchesDrawn(team2.getMatchesDrawn() + 1);
        }
        else{
           if(matchResultPayload.getWinningTeamId().equals(team1.getId())){
               team1.setTotalPoints(team1.getTotalPoints() + 2);
               team1.setMatchesWon(team1.getMatchesWon() + 1);
               team2.setMatchesLost(team2.getMatchesLost() + 1);
               team1.setNetRunRate(team1.getNetRunRate() + matchResultPayload.getNrrDelta());
               team2.setNetRunRate(team2.getNetRunRate() - matchResultPayload.getNrrDelta());
           }
           else{
               team2.setTotalPoints(team2.getTotalPoints() + 2);
               team2.setMatchesWon(team2.getMatchesWon() + 1);
               team1.setMatchesLost(team1.getMatchesLost() + 1);
               team2.setNetRunRate(team2.getNetRunRate() + matchResultPayload.getNrrDelta());
               team1.setNetRunRate(team1.getNetRunRate() - matchResultPayload.getNrrDelta());
           }
        }
        team1.setTotalMatchsPlayed(team1.getTotalMatchsPlayed() + 1);
        team2.setTotalMatchsPlayed(team2.getTotalMatchsPlayed() + 1);
        
        cricketTeamRepository.save(team1);

    }

    public List<PointsTableEntity>  getPointTable() {
        return cricketTeamRepository.findAllSortedByTotalPoints();
    }

    public List<MatchesEntity>  getMatchesByTeamId(Integer teamId) {
        return matchsRepository.findTop3ByTeam1IdDateDesc(teamId);
    }
}
