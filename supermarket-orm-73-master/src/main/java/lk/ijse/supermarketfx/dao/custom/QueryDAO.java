package lk.ijse.supermarketfx.dao.custom;

import lk.ijse.supermarketfx.dao.SuperDAO;
import lk.ijse.supermarketfx.entity.CustomOrder;

import java.sql.SQLException;
import java.util.List;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/1/2025 1:15 PM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

public interface QueryDAO extends SuperDAO {
    List<CustomOrder> findFullOrderDataByCustomerId(String customerId) throws SQLException;
}

// fetch customer id for orders with order details