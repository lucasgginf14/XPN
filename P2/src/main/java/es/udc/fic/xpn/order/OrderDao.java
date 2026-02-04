package es.udc.fic.xpn.order;

import java.util.Optional;

public interface OrderDao {

    public Integer create(Order order);

    public Optional<Order> findById(Integer id);
}
