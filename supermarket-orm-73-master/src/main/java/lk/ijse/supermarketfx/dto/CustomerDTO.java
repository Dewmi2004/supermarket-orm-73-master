package lk.ijse.supermarketfx.dto;

import lombok.*;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 4/24/2025 9:42 AM
 * Project: SupermarketFX
 * --------------------------------------------
 **/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDTO {
    private String customerId;
    private String name;
    private String nic;
    private String email;
    private String phone;
}
