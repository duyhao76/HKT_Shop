package vn.HKT.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;
    
    @ManyToOne
    @JoinColumn(name = "productId")
    private Products product;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;
    
    @Column(nullable = false)
    private Integer quantity;
    
    @Column(nullable = false)
    private BigDecimal unitPrice;
    
    @Column
    private LocalDateTime importDate;
    
    @Column(length = 200)
    private String note;
}
