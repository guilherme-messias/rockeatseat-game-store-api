package io.github.guilhermemessias.gamestore.modules.game.dtos.response;

import io.github.guilhermemessias.gamestore.modules.game.enums.Genre;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateGameResponseDTO(UUID id, String title, Genre genre, double price, LocalDateTime createdAt) {
}
