package lk.ijse.supermarketfx.model;

import lk.ijse.supermarketfx.dto.OrderDetailsDTO;
import lk.ijse.supermarketfx.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;



public class OrderDetailsModel {
    private final ItemModel itemModel = new ItemModel();

    public boolean saveOrderDetailsList(ArrayList<OrderDetailsDTO> cartList) throws SQLException {
        for (OrderDetailsDTO orderDetailsDTO : cartList) {
            boolean isDetailsSaved = saveOrderDetails(orderDetailsDTO);
            if (!isDetailsSaved) {
                return false;
            }

            boolean isUpdated = itemModel.reduceQty(orderDetailsDTO);
            if (!isUpdated) {
                return false;
            }

            // other tables
        }
        return true;
    }

    private boolean saveOrderDetails(OrderDetailsDTO dto) throws SQLException {
        return CrudUtil.execute(
                "insert into order_details values (?,?,?,?)",
                dto.getOrderId(),
                dto.getItemId(),
                dto.getQty(),
                dto.getPrice()
        );
    }
}
