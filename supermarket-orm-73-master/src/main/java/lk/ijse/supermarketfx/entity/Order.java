package lk.ijse.supermarketfx.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/1/2025 10:28 AM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_id")
    private String id;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<OrderDetail> orderDetails;
//    private String customerId;
    @Column(name = "order_date")
    private Date orderDate;
    @ManyToOne
    @JoinColumn(name = "cus_id")
    private Customer customer;
}
