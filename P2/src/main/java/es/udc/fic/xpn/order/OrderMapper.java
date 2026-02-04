package es.udc.fic.xpn.order;

public class OrderMapper {

    public static OrderDto entityToDto(Order order) {
        OrderDto orderDto = new OrderDto();

        orderDto.setId(order.getId());
        orderDto.setDate(order.getDate());
        orderDto.setQuantity(order.getQuantity());
        orderDto.setOriginWarehouseId(order.getOriginWarehouseId());
        orderDto.setDestinationWarehouseId(order.getDestinationWarehouseId());
        orderDto.setProductSku(order.getProductSku());

        return orderDto;
    }

    public static Order dtoToEntity(OrderDto orderDto) {
        Order order = new Order();

        order.setId(orderDto.getId());
        order.setDate(orderDto.getDate());
        order.setQuantity(orderDto.getQuantity());
        order.setOriginWarehouseId(orderDto.getOriginWarehouseId());
        order.setDestinationWarehouseId(orderDto.getDestinationWarehouseId());
        order.setProductSku(orderDto.getProductSku());

        return order;
    }
}
