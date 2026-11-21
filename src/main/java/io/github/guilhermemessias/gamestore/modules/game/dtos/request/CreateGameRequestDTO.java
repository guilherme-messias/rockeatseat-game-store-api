package io.github.guilhermemessias.gamestore.modules.game.dtos.request;

import io.github.guilhermemessias.gamestore.modules.game.enums.Genre;

public record CreateGameRequestDTO(String title, Genre genre, double price) {
}
