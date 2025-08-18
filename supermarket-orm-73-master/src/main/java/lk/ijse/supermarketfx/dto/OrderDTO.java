package lk.ijse.supermarketfx.dto;

import lombok.*;

import java.sql.Date;
import java.util.ArrayList;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {
    private String orderId;
    private String customerId;
    private Date date;
    private ArrayList<OrderDetailsDTO> cartList;
}
