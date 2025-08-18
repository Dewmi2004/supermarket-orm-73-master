package lk.ijse.supermarketfx.model;

import lk.ijse.supermarketfx.db.DBConnection;
import lk.ijse.supermarketfx.dto.OrderDTO;
import lk.ijse.supermarketfx.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 4/24/2025 9:32 AM
 * Project: SupermarketFX
 * --------------------------------------------
 **/

public class OrderModel {

    private final OrderDetailsModel orderDetailsModel = new OrderDetailsModel();

    public String getNextOrderId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute(
                "select order_id from orders order by order_id desc limit 1"
        );

        char tableChar = 'O';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNUmberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNUmberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableChar + "%03d", nextIdNumber);
            return nextIdString;
        }
        return tableChar + "001";
    }

    public boolean placeOrder(OrderDTO orderDTO) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean isSave = CrudUtil.execute(
                    "insert into orders values (?,?,?)",
                    orderDTO.getOrderId(),
                    orderDTO.getCustomerId(),
                    orderDTO.getDate()
            );

            if (isSave) {
                boolean isDetailsSaved = orderDetailsModel.saveOrderDetailsList(orderDTO.getCartList());
                if (isDetailsSaved) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}











