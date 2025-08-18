package lk.ijse.supermarketfx.dto.tm;

import lombok.*;



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
