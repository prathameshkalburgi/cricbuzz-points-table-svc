package com.example.cricket_api.repository;

import com.example.cricket_api.entity.MatchesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchsRepository extends JpaRepository<MatchesEntity, Integer> {

    @Query("SELECT M FROM MatchesEntity M WHERE M.team1Id = :teamId OR M.team2Id = :teamId ORDER BY M.matchDate DESC Limit 3")
    List<MatchesEntity> findTop3ByTeam1IdDateDesc(@Param("teamId") Integer teamId);
}
