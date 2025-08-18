package lk.ijse.supermarketfx.dto;

import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemDTO {
    private String itemId;
    private String name;
    private int quantity;
    private double unitPrice;
}
