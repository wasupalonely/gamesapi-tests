package com.example.gamesapi.gamesapi.services;

import java.util.List;

import com.example.gamesapi.gamesapi.models.entities.Game;

public interface GameService {
    List<Game> findAll();

    Game findGameById(String id);

    Game create(Game game);

    Game update(String id, Game game);

    boolean delete(String id);
}
