package io.leofalves.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.leofalves.dsmovie.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}
