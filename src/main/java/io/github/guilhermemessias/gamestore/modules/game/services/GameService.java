package io.github.guilhermemessias.gamestore.modules.game.services;

import io.github.guilhermemessias.gamestore.modules.game.GameEntity;
import io.github.guilhermemessias.gamestore.modules.game.dtos.request.CreateGameRequestDTO;
import io.github.guilhermemessias.gamestore.modules.game.repositories.GameRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public GameEntity createGame(@Valid CreateGameRequestDTO gameEntity) {
        return this.gameRepository.save(new GameEntity(gameEntity));
    }
}
