package lk.ijse.supermarketfx.bo.custom;

import lk.ijse.supermarketfx.bo.SuperBO;
import lk.ijse.supermarketfx.bo.exception.DuplicateException;
import lk.ijse.supermarketfx.bo.exception.InUseException;
import lk.ijse.supermarketfx.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;



public interface CustomerBO extends SuperBO {
    List<CustomerDTO> getAllCustomer() throws SQLException;

    void saveCustomer(CustomerDTO dto) throws DuplicateException, Exception;

    void updateCustomer(CustomerDTO dto) throws SQLException;

    boolean deleteCustomer(String id) throws InUseException, Exception;

    String getNextId() throws SQLException;
}
