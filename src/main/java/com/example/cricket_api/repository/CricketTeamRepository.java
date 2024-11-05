package com.example.cricket_api.repository;

import com.example.cricket_api.entity.PointsTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CricketTeamRepository extends JpaRepository<PointsTableEntity, Integer> {

    @Query("SELECT C FROM PointsTableEntity C ORDER BY C.totalPoints DESC")
    List<PointsTableEntity> findAllSortedByTotalPoints();
}
