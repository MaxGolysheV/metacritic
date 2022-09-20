package com.example.metacritic.repo.Games;

import com.example.metacritic.Models.Games.GameGenre;
import com.example.metacritic.Models.Games.Games;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GamesRepository extends JpaRepository<Games, Long> {


    List<Games> findByGameName(String gameName);


    Games findById(long id);

    List<Games> findByGameNameContains(String gameName);

}
