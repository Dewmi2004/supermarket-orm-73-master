package lk.ijse.supermarketfx.bo.util;

import lk.ijse.supermarketfx.dto.CustomerDTO;
import lk.ijse.supermarketfx.entity.Customer;


// modal mapper
public class EntityDTOConverter {
    public CustomerDTO getCustomerDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(customer.getId());
        dto.setNic(customer.getNic());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(dto.getPhone());
        return dto;
    }

    public Customer getCustomer(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.getCustomerId());
        customer.setNic(dto.getNic());
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        return customer;
    }
}
