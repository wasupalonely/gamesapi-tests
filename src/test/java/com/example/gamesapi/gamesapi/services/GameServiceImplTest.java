package com.example.gamesapi.gamesapi.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.gamesapi.gamesapi.models.entities.Game;
import com.example.gamesapi.gamesapi.repositories.GameRepository;


public class GameServiceImplTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameServiceImpl gameService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Game game1 = new Game("Game1", "Description1", "Genre1", "Author1");
        Game game2 = new Game("Game2", "Description2", "Genre2", "Author2");
        when(gameRepository.findAll()).thenReturn(Arrays.asList(game1, game2));

        List<Game> games = gameService.findAll();
        assertEquals(2, games.size());
        assertEquals("Game1", games.get(0).getName());
        assertEquals("Game2", games.get(1).getName());
    }

    @Test
    public void testFindGameById() {
        Game game = new Game("Game1", "Description1", "Genre1", "Author1");
        when(gameRepository.findById("1")).thenReturn(Optional.of(game));

        Game foundGame = gameService.findGameById("1");
        assertNotNull(foundGame);
        assertEquals("Game1", foundGame.getName());
    }

    @Test
    public void testCreateGame() {
        Game game = new Game("Game1", "Description1", "Genre1", "Author1");
        when(gameRepository.save(game)).thenReturn(game);

        Game createdGame = gameService.create(game);
        assertNotNull(createdGame);
        assertEquals("Game1", createdGame.getName());
    }

    @Test
    public void testDeleteGame() {
        when(gameRepository.existsById("1")).thenReturn(true);

        boolean isDeleted = gameService.delete("1");
        assertTrue(isDeleted);
        verify(gameRepository, times(1)).deleteById("1");
    }

    @Test
    public void testUpdateGame() {
        Game existingGame = new Game("Game1", "Description1", "Genre1", "Author1");
        Game updatedGame = new Game("Game2", "Description2", "Genre2", "Author2");
        when(gameRepository.findById("1")).thenReturn(Optional.of(existingGame));
        when(gameRepository.save(existingGame)).thenReturn(existingGame);

        Game result = gameService.update("1", updatedGame);
        assertNotNull(result);
        assertEquals("Game2", result.getName());
    }
}

