package io.github.guilhermemessias.gamestore.modules.order.repositories;

import io.github.guilhermemessias.gamestore.modules.order.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    List<OrderEntity> findAllByClient_Id(UUID id);

    Page<OrderEntity> findAllByClient_Id(UUID id, Pageable pageable);

    boolean existsByClient_IdAndGame_Id(UUID id, UUID gameId);
}
