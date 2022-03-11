package io.leofalves.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.leofalves.dsmovie.entities.Score;
import io.leofalves.dsmovie.entities.ScorePK;

public interface ScoreRepository extends JpaRepository<Score, ScorePK>{

}
