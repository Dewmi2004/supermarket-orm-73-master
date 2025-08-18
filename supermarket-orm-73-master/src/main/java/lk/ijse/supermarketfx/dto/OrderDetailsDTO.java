package lk.ijse.supermarketfx.dto;

import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailsDTO {
    private String orderId;
    private String itemId;
    private int qty;
    private double price;
}
