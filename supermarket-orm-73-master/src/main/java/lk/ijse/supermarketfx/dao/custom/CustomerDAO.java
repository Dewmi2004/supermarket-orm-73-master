package lk.ijse.supermarketfx.dao.custom;

import lk.ijse.supermarketfx.dao.CrudDAO;
import lk.ijse.supermarketfx.dto.CustomerDTO;
import lk.ijse.supermarketfx.entity.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;



public interface CustomerDAO extends CrudDAO<Customer> {
    List<Customer> search(String text) throws SQLException;

    //     existsByNic -> boolean
    Optional<Customer> findCustomerByNic(String nic) throws SQLException;

    boolean existsCustomerByPhoneNumber(String phoneNumber) throws SQLException;
}
