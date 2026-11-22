package io.github.guilhermemessias.gamestore.modules.game.repositories;

import io.github.guilhermemessias.gamestore.modules.game.GameEntity;
import io.github.guilhermemessias.gamestore.modules.game.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GameRepository extends JpaRepository<GameEntity, UUID> {
    List<GameEntity> findByTitleContainingIgnoreCase(String titulo);

    List<GameEntity> findByGenre(Genre genero);

    GameEntity findTopByOrderByPriceDesc();
}
