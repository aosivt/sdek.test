package sdek.supplier.mappers;

import sdek.supplier.models.Order;

import java.util.Set;

public interface CanceledOrdersMapper extends Dictionary {
    public Set<Order> getCanceledOrders();
}
