package io.github.guilhermemessias.gamestore.modules.reports.dtos.response;

import io.github.guilhermemessias.gamestore.modules.game.enums.Genre;

public record GetGenresWithRegisteredGamesResponseDTO(
        Genre genre,
        Long totalGames) {
}
