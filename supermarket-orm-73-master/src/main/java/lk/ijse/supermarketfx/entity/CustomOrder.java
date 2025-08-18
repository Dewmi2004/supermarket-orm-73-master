package lk.ijse.supermarketfx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomOrder {
    public String orderId;
    public Date orderDate;
    public String customerId;
    public String customerName;
    public String customerNIc;
    public String customerEmail;
    public String customerPhone;

    public List<CustomOrderDetails> orderDetailsList;
}
