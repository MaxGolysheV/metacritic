package com.example.metacritic.repo.Films;

import com.example.metacritic.Models.Films.FilmStudios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmStudiosRepository extends JpaRepository<FilmStudios,Long> {


    FilmStudios findById(long id);

    List<FilmStudios> findByFilmStudioName(String name);
    List<FilmStudios> findByFilmStudioNameContains(String name);
}
