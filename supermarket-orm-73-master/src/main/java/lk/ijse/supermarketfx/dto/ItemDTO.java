package lk.ijse.supermarketfx.dto;

import lombok.*;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 5/10/2025 2:54 PM
 * Project: SupermarketFX
 * --------------------------------------------
 **/

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
