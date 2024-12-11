package vn.HKT.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    
    @ManyToOne
    private Users user;
    
    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;
}
