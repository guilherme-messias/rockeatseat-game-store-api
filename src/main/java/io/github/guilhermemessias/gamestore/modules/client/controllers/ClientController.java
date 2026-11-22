package io.github.guilhermemessias.gamestore.modules.client.controllers;

import io.github.guilhermemessias.gamestore.modules.client.dtos.request.CreateClientRequestDTO;
import io.github.guilhermemessias.gamestore.modules.client.dtos.response.CreateClientResponseDTO;
import io.github.guilhermemessias.gamestore.modules.client.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<CreateClientResponseDTO> createClient(@Valid @RequestBody CreateClientRequestDTO clientEntity) {
        System.out.println("Recebi uma requisição para criar um cliente com o e-mail: " + clientEntity.email());
        var result = clientService.createClient(clientEntity);

        return ResponseEntity.ok().body(new CreateClientResponseDTO(result.getId(),
                result.getName(), result.getEmail(), result.getCreatedAt()));
    }
}
