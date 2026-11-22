package io.github.guilhermemessias.gamestore.modules.client.services;

import io.github.guilhermemessias.gamestore.modules.client.ClientEntity;
import io.github.guilhermemessias.gamestore.modules.client.dtos.request.CreateClientRequestDTO;
import io.github.guilhermemessias.gamestore.modules.client.exception.ExistingClient;
import io.github.guilhermemessias.gamestore.modules.client.repositories.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public ClientEntity createClient(@Valid CreateClientRequestDTO clientEntity) {
        System.out.println("estou pesquisando se já existe um cliente com o e-mail: " + clientEntity.email());
        ClientEntity existingClient = this.clientRepository.findByEmail(clientEntity.email());

        if  (existingClient != null) {
            throw new ExistingClient("Já existe um cliente com o mesmo e-mail.");
        }

        System.out.println("não existe um cliente com o e-mail: " + clientEntity.email() + ", então vou criar um novo cliente");

        return this.clientRepository.save(new ClientEntity(clientEntity));
    }
}
