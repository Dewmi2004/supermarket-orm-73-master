package lk.ijse.supermarketfx.model;

import lk.ijse.supermarketfx.dto.ItemDTO;
import lk.ijse.supermarketfx.dto.OrderDetailsDTO;
import lk.ijse.supermarketfx.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 4/24/2025 9:31 AM
 * Project: SupermarketFX
 * --------------------------------------------
 **/

public class ItemModel {
    public ArrayList<String> getAllItemIds() throws SQLException {
        ResultSet rst = CrudUtil.execute(
                "select item_id from item"
        );
        ArrayList<String> list = new ArrayList<>();
        while (rst.next()) {
            String id = rst.getString(1);
            list.add(id);
        }
        return list;
    }

    public ItemDTO findById(String itemId) throws SQLException {
        ResultSet rst = CrudUtil.execute(
                "select * from item where item_id=?",
                itemId
        );

        if (rst.next()) {
            return new ItemDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            );
        }
        return null;
    }

    public boolean reduceQty(OrderDetailsDTO orderDetailsDTO) throws SQLException {
        return CrudUtil.execute(
                "update item set quantity = quantity-? where item_id=?",
                orderDetailsDTO.getQty(),
                orderDetailsDTO.getItemId()
        );
    }
}
