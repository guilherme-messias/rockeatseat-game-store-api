package io.github.guilhermemessias.gamestore.modules.game.services;

import io.github.guilhermemessias.gamestore.modules.game.GameEntity;
import io.github.guilhermemessias.gamestore.modules.game.dtos.request.CreateGameRequestDTO;
import io.github.guilhermemessias.gamestore.modules.game.enums.Genre;
import io.github.guilhermemessias.gamestore.modules.game.repositories.GameRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public GameEntity createGame(@Valid CreateGameRequestDTO gameEntity) {
        return this.gameRepository.save(new GameEntity(gameEntity));
    }

    public Page<GameEntity> findAll(Pageable pageable) {
        return this.gameRepository.findAll(pageable);
    }

    public GameEntity findById(UUID id) {
        return this.gameRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Jogo não encontrado com o ID: " + id));
    }

    public List<GameEntity> findByTitle(String titulo) {
        List<GameEntity> games = this.gameRepository.findByTitleContainingIgnoreCase(titulo);
        if (games.isEmpty()) {
            throw new EntityNotFoundException("Nenhum jogo encontrado com o título: " + titulo);
        }
        return games;
    }

    public List<GameEntity> findByGenre(Genre genero) {
        List<GameEntity> games = this.gameRepository.findByGenre(genero);
        if (games.isEmpty()) {
            throw new EntityNotFoundException("Nenhum jogo encontrado com o gênero: " + genero);
        }
        return games;
    }
}
