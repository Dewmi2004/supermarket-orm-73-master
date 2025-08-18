package lk.ijse.supermarketfx.dao.custom;

import lk.ijse.supermarketfx.dao.SuperDAO;
import lk.ijse.supermarketfx.entity.CustomOrder;

import java.sql.SQLException;
import java.util.List;



public interface QueryDAO extends SuperDAO {
    List<CustomOrder> findFullOrderDataByCustomerId(String customerId) throws SQLException;
}

// fetch customer id for orders with order details