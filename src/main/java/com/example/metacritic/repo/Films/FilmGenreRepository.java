package com.example.metacritic.repo.Films;

import com.example.metacritic.Models.Films.FilmGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmGenreRepository extends JpaRepository<FilmGenre,Long> {


    FilmGenre findById(long id);
    List<FilmGenre> findByGenreName(String genreName);

    List<FilmGenre> findByGenreNameContains(String genreName);
}
