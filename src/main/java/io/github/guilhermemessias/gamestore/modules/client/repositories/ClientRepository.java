package io.github.guilhermemessias.gamestore.modules.client.repositories;

import io.github.guilhermemessias.gamestore.modules.client.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {
    ClientEntity findByEmail(String email);
}
