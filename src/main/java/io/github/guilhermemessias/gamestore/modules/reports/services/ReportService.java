package io.github.guilhermemessias.gamestore.modules.reports.services;

import io.github.guilhermemessias.gamestore.modules.game.enums.Genre;
import io.github.guilhermemessias.gamestore.modules.game.repositories.GameRepository;
import io.github.guilhermemessias.gamestore.modules.order.repositories.OrderRepository;
import io.github.guilhermemessias.gamestore.modules.reports.dtos.response.GetGenresWithRegisteredGamesResponseDTO;
import io.github.guilhermemessias.gamestore.modules.reports.dtos.response.GetSalesByGenreResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GameRepository gameRepository;

    public List<GetGenresWithRegisteredGamesResponseDTO> getGenresWithRegisteredGames() {
        List<GetGenresWithRegisteredGamesResponseDTO> genres = new ArrayList<>();

        for (Genre genre : Genre.values()) {
            Long totalGames = gameRepository.countByGenre(genre);
            if (totalGames > 0) {
                genres.add(new GetGenresWithRegisteredGamesResponseDTO(genre, totalGames));
            }
        }

        return genres;
    }

    public List<GetSalesByGenreResponseDTO> getSalesByGenre() {
        List<GetSalesByGenreResponseDTO> salesByGenre = new ArrayList<>();

        for (Genre genre : Genre.values()) {
            Long totalSales = orderRepository.countByGameGenre(genre);
            if (totalSales > 0) {
                salesByGenre.add(new GetSalesByGenreResponseDTO(genre, totalSales));
            }
        }

        return salesByGenre;
    }
}
