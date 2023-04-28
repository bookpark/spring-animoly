package toy.animoly.controller;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.animoly.dto.OrderDto;
import toy.animoly.entity.Order;
import toy.animoly.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @GetMapping("/api/orders")
    public List<OrderDto> orders() {
        JPAQuery<Order> orders = orderService.findAllWithItem();
        return orders.stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }
}
