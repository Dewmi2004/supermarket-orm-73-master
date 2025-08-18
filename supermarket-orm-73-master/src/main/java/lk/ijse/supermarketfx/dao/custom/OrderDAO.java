package lk.ijse.supermarketfx.dao.custom;

import lk.ijse.supermarketfx.dao.CrudDAO;
import lk.ijse.supermarketfx.entity.Order;

import java.sql.SQLException;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/1/2025 12:44 PM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

public interface OrderDAO extends CrudDAO<Order> {
    boolean existOrdersByCustomerId(String customerId) throws SQLException;
}
