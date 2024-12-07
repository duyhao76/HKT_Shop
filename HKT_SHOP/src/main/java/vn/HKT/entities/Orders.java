package vn.HKT.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;
    
    @Column
    private LocalDate orderDate;
    
    @Column
    private BigDecimal totalAmount;
    
    @Column(length = 20)
    private String status;
    
    @Column(length = 50)
    private String paymentMethod;
    
    @Column(length = 200)
    private String shippingAddress;
    
    @Column(length = 200)
    private String note;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;
    
    @Transient
    private String username;
}
