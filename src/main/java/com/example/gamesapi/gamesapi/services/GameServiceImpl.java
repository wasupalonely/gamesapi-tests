package com.example.gamesapi.gamesapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gamesapi.gamesapi.models.entities.Game;
import com.example.gamesapi.gamesapi.repositories.GameRepository;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> findAll() {
        return (List<Game>) gameRepository.findAll();
    }

    @Override
    public Game create(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public boolean delete(String id) {
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Game findGameById(String id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.orElse(null);
    }

    @Override
    public Game update(String id, Game gameDetails) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isPresent()) {
            Game existingGame = optionalGame.get();
            if (gameDetails.getName() != null) {
                existingGame.setName(gameDetails.getName());
            }
            if (gameDetails.getDescription() != null) {
                existingGame.setDescription(gameDetails.getDescription());
            }
            if (gameDetails.getGenre() != null) {
                existingGame.setGenre(gameDetails.getGenre());
            }
            if (gameDetails.getAuthor() != null) {
                existingGame.setAuthor(gameDetails.getAuthor());
            }
            return gameRepository.save(existingGame);
        }
        return null;
    }
}
