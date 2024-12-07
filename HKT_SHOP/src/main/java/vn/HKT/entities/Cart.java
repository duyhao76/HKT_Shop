package vn.HKT.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Cart")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cart {
	@Id
    private Long cartId;
    
    @ManyToOne
    private Users user;
    
    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;
}
