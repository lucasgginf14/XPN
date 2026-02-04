package es.udc.fic.xpn.order;

import java.util.Optional;

public interface OrderService {

    public Optional<Order> findById(Integer id);

    public Integer create(OrderDto orderDto);
}
