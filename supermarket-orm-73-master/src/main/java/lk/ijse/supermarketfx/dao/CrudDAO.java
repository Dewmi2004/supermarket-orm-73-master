package lk.ijse.supermarketfx.dao;

import lk.ijse.supermarketfx.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/1/2025 11:03 AM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

public interface CrudDAO<T> extends SuperDAO {
    List<T> getAll() throws SQLException;

    String getLastId() throws SQLException;

    boolean save(T t) throws SQLException;

    boolean update(T t) throws SQLException;

    boolean delete(String id) throws SQLException;

    List<String> getAllIds() throws SQLException;

    Optional<T> findById(String id) throws SQLException;
}
