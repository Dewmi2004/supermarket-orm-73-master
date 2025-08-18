package lk.ijse.supermarketfx.dto;

import lombok.*;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 5/10/2025 4:11 PM
 * Project: SupermarketFX
 * --------------------------------------------
 **/

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
