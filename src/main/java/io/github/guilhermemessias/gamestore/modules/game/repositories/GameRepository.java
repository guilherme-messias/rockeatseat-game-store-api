package io.github.guilhermemessias.gamestore.modules.game.repositories;

import io.github.guilhermemessias.gamestore.modules.game.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameRepository extends JpaRepository<GameEntity, UUID> {
}
