package io.github.guilhermemessias.gamestore.modules.reports.controllers;

import io.github.guilhermemessias.gamestore.modules.reports.dtos.response.GetGenresWithRegisteredGamesResponseDTO;
import io.github.guilhermemessias.gamestore.modules.reports.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/generos")
    public List<GetGenresWithRegisteredGamesResponseDTO> getGenresWithRegisteredGames() {
        return reportService.getGenresWithRegisteredGames();
    }
}
