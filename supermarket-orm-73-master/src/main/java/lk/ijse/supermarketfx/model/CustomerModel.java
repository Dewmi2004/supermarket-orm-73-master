package lk.ijse.supermarketfx.model;

import lk.ijse.supermarketfx.db.DBConnection;
import lk.ijse.supermarketfx.dto.CustomerDTO;
import lk.ijse.supermarketfx.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class CustomerModel {
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        PreparedStatement pst = connection.prepareStatement("select * from customer");
//        ResultSet resultSet = pst.executeQuery();
        ResultSet resultSet = CrudUtil.execute("select * from customer");

        ArrayList<CustomerDTO> list = new ArrayList<>();

        while (resultSet.next()) {
            CustomerDTO customerDTO = new CustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );

            list.add(customerDTO);
        }
        return list;
    }

    public String getNextId() throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "select customer_id from customer order by customer_id desc limit 1";
//        PreparedStatement pst = connection.prepareStatement(sql);

//        ResultSet resultSet = pst.executeQuery();
        ResultSet resultSet = CrudUtil.execute("select customer_id from customer order by customer_id desc limit 1");
        char tableChar = 'C'; // Use any character Ex:- customer table for C, item table for I
        if (resultSet.next()) {
            String lastId = resultSet.getString(1); // "C004"
            String lastIdNUmberString = lastId.substring(1); // "004"
            int lastIdNumber = Integer.parseInt(lastIdNUmberString); // 4
            int nextIdNumber = lastIdNumber + 1; // 5
            String nextIdString = String.format(tableChar + "%03d", nextIdNumber); // "C005"
            return nextIdString;
        }
        return tableChar + "001";
    }

    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//// insert into customer values ('C001','Bob','1111111111111','bob@gmail.com','077777777')
//// insert into customer values (?,?,?,?,?)
//// set ? marks for column count Ex: 4 col -> (?,?,?,?)
//        String sql = "insert into customer values (?,?,?,?,?)";
//        PreparedStatement pst = connection.prepareStatement(sql);

// set values for ? marks
//        pst.setString(1, customerDTO.getCustomerId());
//        pst.setString(2, customerDTO.getName());
//        pst.setString(3, customerDTO.getNic());
//        pst.setString(4, customerDTO.getEmail());
//        pst.setString(5, customerDTO.getPhone());
//
//        int i = pst.executeUpdate();
//        boolean isSave = i > 0;
//        return isSave;
        return CrudUtil.execute(
                "insert into customer values (?,?,?,?,?)",
                customerDTO.getCustomerId(),
                customerDTO.getName(),
                customerDTO.getNic(),
                customerDTO.getEmail(),
                customerDTO.getPhone()
        );
    }

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException {
        return CrudUtil.execute(
                "update customer set name=?, nic=?, email=?, phone=? where customer_id=?",
                customerDTO.getName(),
                customerDTO.getNic(),
                customerDTO.getEmail(),
                customerDTO.getPhone(),
                customerDTO.getCustomerId()
        );
    }

    public boolean deleteCustomer(String customerId) throws SQLException {
        return CrudUtil.execute(
                "delete from customer where customer_id=?",
                customerId
        );
    }

    public ArrayList<String> getAllCustomerIds() throws SQLException {
        ResultSet rst = CrudUtil.execute(
                "select customer_id from customer"
        );
        ArrayList<String> list = new ArrayList<>();
        while (rst.next()) {
            String id = rst.getString(1);
            list.add(id);
        }
        return list;
    }

    public String findNameById(String customerId) throws SQLException {
        ResultSet rst = CrudUtil.execute(
                "select name from customer where customer_id=?",
                customerId
        );
        if (rst.next()) {
            return rst.getString(1);
        }
        return "";
    }
}
