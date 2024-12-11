package vn.HKT.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CartItems")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    private Long id;
    
    @ManyToOne
    private Cart cart;
    
    @ManyToOne  
    private Products product;
    
    private Integer quantity;
}
