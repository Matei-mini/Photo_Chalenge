package com.example.demo.repository;

import com.example.demo.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
    boolean existsByVoterIdAndPictureId(Integer voterId, Integer pictureId);
    @Query("SELECT SUM(v.value) FROM Vote v WHERE v.picture.id = :pictureId")
    Integer sumValueByPictureId(@Param("pictureId") Integer pictureId);
}