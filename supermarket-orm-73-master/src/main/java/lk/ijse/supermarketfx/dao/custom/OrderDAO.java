package lk.ijse.supermarketfx.dao.custom;

import lk.ijse.supermarketfx.dao.CrudDAO;
import lk.ijse.supermarketfx.entity.Order;

import java.sql.SQLException;



public interface OrderDAO extends CrudDAO<Order> {
    boolean existOrdersByCustomerId(String customerId) throws SQLException;
}
