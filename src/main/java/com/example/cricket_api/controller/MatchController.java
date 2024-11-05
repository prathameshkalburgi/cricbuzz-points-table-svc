package com.example.cricket_api.controller;

import com.example.cricket_api.entity.MatchesEntity;
import com.example.cricket_api.entity.PointsTableEntity;
import com.example.cricket_api.model.MatchResultPayload;
import com.example.cricket_api.service.CricketTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/matches")
public class MatchController {

    @Autowired
    private CricketTeamService cricketTeamService;

    @PostMapping("/result")
    public ResponseEntity<String> updateMatchResult(@RequestBody MatchResultPayload matchResultPayload) {
        cricketTeamService.updateMatchesResult(matchResultPayload);
        return new ResponseEntity<>("Point Table update Successfully", HttpStatus.OK);
    }

    @GetMapping("/getPointTable")
    public ResponseEntity<List<PointsTableEntity>> getPointTable() {
        return new ResponseEntity<>(cricketTeamService.getPointTable(), HttpStatus.OK);
    }

    @GetMapping("/getMatchesByTeam/{teamId}/last3")
    public ResponseEntity<List<MatchesEntity>> getMatchesByTeamId(@PathVariable Integer teamId) {
        return new ResponseEntity<>(cricketTeamService.getMatchesByTeamId(teamId), HttpStatus.OK);
    }


}
