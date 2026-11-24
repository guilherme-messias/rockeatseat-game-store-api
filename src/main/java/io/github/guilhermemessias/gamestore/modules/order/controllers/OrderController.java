package io.github.guilhermemessias.gamestore.modules.order.controllers;

import io.github.guilhermemessias.gamestore.modules.order.dtos.request.CreateOrderRequestDTO;
import io.github.guilhermemessias.gamestore.modules.order.dtos.response.CreateOrderResponseDTO;
import io.github.guilhermemessias.gamestore.modules.order.dtos.response.GetOrderResponseDTO;
import io.github.guilhermemessias.gamestore.modules.order.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<CreateOrderResponseDTO> createOrder(@Valid @RequestBody CreateOrderRequestDTO createOrderRequestDTO) {
        var result = orderService.createOrder(createOrderRequestDTO);
        return ResponseEntity.ok().body(new CreateOrderResponseDTO(result.getId(),
                result.getClient().getId(), result.getGame().getId(), result.getPrice(), result.getCreatedAt()));
    }

    @GetMapping
    public ResponseEntity<Page<GetOrderResponseDTO>> getOrders(Pageable pageable) {
        var result = orderService.findAll(pageable);
        return ResponseEntity.ok().body(result.map(order -> new GetOrderResponseDTO(order.getId(),
                order.getClient().getId(), order.getGame().getId(), order.getPrice(), order.getCreatedAt())));
    }
}
