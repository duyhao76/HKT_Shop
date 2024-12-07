package vn.HKT.entities;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "OrderDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
	@EmbeddedId
    private OrderDetailId id;
    
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderId")
    private Orders order;
    
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId")
    private Products product;
    
    @Column(nullable = false)
    private Integer quantity;
    
    @Column(nullable = false)
    private BigDecimal unitPrice;
    
    @Transient
    private String productname;
}
