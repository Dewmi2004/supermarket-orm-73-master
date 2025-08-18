package lk.ijse.supermarketfx.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data // - getter, setter, to_String
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String nic;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(length = 15)
    private String phone;
}
