package lk.ijse.supermarketfx.dto;

import lombok.*;


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
