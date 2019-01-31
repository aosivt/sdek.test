package sdek.supplier.mappers;

import sdek.supplier.models.Order;

public interface OrderMapper extends Dictionary {
    Order addCanceledOrder(Long id);
}
