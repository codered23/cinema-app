package cinema.service;

import java.util.List;
import cinema.model.Order;
import cinema.model.ShoppingCart;
import cinema.model.User;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
