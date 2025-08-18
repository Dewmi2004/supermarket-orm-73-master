package lk.ijse.supermarketfx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/11/2025 9:38 AM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomOrderDetails {
    public String itemId;
    public String itemName;
    public int quantity;
    public BigDecimal price;
}
