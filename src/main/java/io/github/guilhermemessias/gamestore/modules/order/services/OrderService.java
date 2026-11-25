package io.github.guilhermemessias.gamestore.modules.order.services;

import io.github.guilhermemessias.gamestore.modules.client.ClientEntity;
import io.github.guilhermemessias.gamestore.modules.client.repositories.ClientRepository;
import io.github.guilhermemessias.gamestore.modules.exception.DuplicateGameOrderException;
import io.github.guilhermemessias.gamestore.modules.exception.ExistingClient;
import io.github.guilhermemessias.gamestore.modules.exception.ExistingGame;
import io.github.guilhermemessias.gamestore.modules.game.GameEntity;
import io.github.guilhermemessias.gamestore.modules.game.repositories.GameRepository;
import io.github.guilhermemessias.gamestore.modules.order.OrderEntity;
import io.github.guilhermemessias.gamestore.modules.order.dtos.request.CreateOrderRequestDTO;
import io.github.guilhermemessias.gamestore.modules.order.repositories.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ClientRepository clientRepository;

    public OrderEntity createOrder(@Valid CreateOrderRequestDTO orderEntity) {
        ClientEntity client = this.clientRepository.findById(orderEntity.clientId())
                .orElseThrow(() -> new ExistingClient("Cliente não encontrado com o ID: " + orderEntity.clientId()));

        GameEntity game = this.gameRepository.findById(orderEntity.gameId())
                .orElseThrow(() -> new ExistingGame("Jogo não encontrado com o ID: " + orderEntity.gameId()));

        if (this.orderRepository.existsByClient_IdAndGame_Id(client.getId(), orderEntity.gameId())) {
            throw new DuplicateGameOrderException("O cliente já possui um pedido para este jogo.");
        }

        OrderEntity order = new OrderEntity();
        order.setClient(client);
        order.setGame(game);
        order.setPrice(game.getPrice());

        return this.orderRepository.save(order);
    }

    public Page<OrderEntity> findAll(Pageable pageable) {
        return this.orderRepository.findAll(pageable);
    }

    public Page<OrderEntity> findAllByClientId(Pageable pageable, UUID clientId) {
        ClientEntity client = this.clientRepository.findById(clientId)
                .orElseThrow(() -> new ExistingClient("Cliente não encontrado com o ID: " + clientId));

        return this.orderRepository.findAllByClient_Id(client.getId(), pageable);
    }

    public Double calculateTotalSpent(UUID clientId) {
        if (!this.clientRepository.existsById(clientId)) {
            throw new ExistingClient("Cliente não encontrado com o ID: " + clientId);
        }

        List<OrderEntity> orders = this.orderRepository.findAllByClient_Id(clientId);

        if (orders.isEmpty()) {
            return 0.0;
        }

        return orders.stream()
                .mapToDouble(OrderEntity::getPrice)
                .sum();
    }
}
