package io.github.guilhermemessias.gamestore.modules.client.controllers;

import io.github.guilhermemessias.gamestore.modules.client.dtos.request.CreateClientRequestDTO;
import io.github.guilhermemessias.gamestore.modules.client.dtos.response.CreateClientResponseDTO;
import io.github.guilhermemessias.gamestore.modules.client.dtos.response.GetClientResponseDTO;
import io.github.guilhermemessias.gamestore.modules.client.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<Page<GetClientResponseDTO>> getClients(Pageable pageable) {
        var result = clientService.findAll(pageable);

        return ResponseEntity.ok().body(result.map(client -> new GetClientResponseDTO(client.getId(),
                client.getName(), client.getEmail(), client.getCreatedAt())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetClientResponseDTO> getClientById(@PathVariable UUID id) {
        var result = clientService.findById(id);
        return ResponseEntity.ok().body(new GetClientResponseDTO(result.getId(),
                result.getName(), result.getEmail(), result.getCreatedAt()));
    }
}
