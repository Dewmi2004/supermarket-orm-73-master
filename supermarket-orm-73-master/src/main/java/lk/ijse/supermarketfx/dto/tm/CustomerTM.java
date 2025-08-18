package lk.ijse.supermarketfx.dto.tm;

import lombok.*;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 4/24/2025 12:46 PM
 * Project: SupermarketFX
 * --------------------------------------------
 **/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerTM {
    private String id;
    private String name;
    private String nic;
    private String email;
    private String phone;
}
