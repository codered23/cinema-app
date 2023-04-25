package cinema.dao;

import java.util.List;
import cinema.model.Order;
import cinema.model.User;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrdersHistory(User user);
}
