package lk.ijse.supermarketfx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomOrderDetails {
    public String itemId;
    public String itemName;
    public int quantity;
    public BigDecimal price;
}
