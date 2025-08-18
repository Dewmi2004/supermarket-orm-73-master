package lk.ijse.supermarketfx.bo.custom;

import lk.ijse.supermarketfx.bo.SuperBO;
import lk.ijse.supermarketfx.dto.OrderDTO;

import java.sql.SQLException;



public interface PlaceOrderBO extends SuperBO {
    boolean placeOrder(OrderDTO dto) throws SQLException;

    String getNextId() throws SQLException;
}
