package vn.HKT.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    
    @Column(nullable = false, length = 100)
    private String productName;
    
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Categories category;
    
    @Column(nullable = false)
    private BigDecimal unitPrice;
    
    @Column(nullable = false)
    private Integer quantityInStock;
    
    @Column
    private LocalDate expiryDate; //Ngày hết hạn
    
    @Column(length = 100)
    private String storageConditions; //Điều kiện bảo quản
    
    @Column
    private LocalDateTime createdDate;
    
    @OneToMany(mappedBy = "product")
    private List<OrderDetails> orderDetails;
    
    @OneToMany(mappedBy = "product")
    private List<Inventory> inventoryRecords;
}
