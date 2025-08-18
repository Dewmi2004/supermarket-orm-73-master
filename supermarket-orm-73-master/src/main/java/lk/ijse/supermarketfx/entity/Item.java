package lk.ijse.supermarketfx.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "item_code")
public class Item {
    @Id
    private String id;
    @Column(length = 100)
    private String name;
    @Column(precision = 10, scale = 2)
    private int quantity;
//    private double price;
    @Column(name = "unit_price", precision =  10, scale = 2)
    private BigDecimal price;
}
