package lk.ijse.supermarketfx.dao.custom;

import lk.ijse.supermarketfx.dao.CrudDAO;
import lk.ijse.supermarketfx.entity.Item;

import java.sql.SQLException;



public interface ItemDAO extends CrudDAO<Item> {
    boolean reduceQuantity(String id, int qty) throws SQLException;
}
