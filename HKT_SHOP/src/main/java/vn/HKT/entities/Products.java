package vn.HKT.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
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
    private LocalDate createdDate;
    
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean isActive;
    
    @Column(columnDefinition = "nvarchar(max)")
    private String imgPath;
    
    @OneToMany(mappedBy = "product")
    private List<OrderDetails> orderDetails;    
    
    public Products(Long productId, String productName, BigDecimal unitPrice, Integer quantityInStock, 
            LocalDate expiryDate, String storageConditions, LocalDate createdDate, Boolean isActive, 
            String imgPath) {
		this.productId = productId;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.quantityInStock = quantityInStock;
		this.expiryDate = expiryDate;
		this.storageConditions = storageConditions;
		this.createdDate = createdDate;
		this.isActive = isActive;
		this.imgPath = imgPath;
    }
}
