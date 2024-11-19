package vn.HKT.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OrderItem")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key, auto-generated

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // Reference to Order entity

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    // private Product product; // Reference to Product entity

    @ElementCollection
    @CollectionTable(name = "OrderItem_StyleValues", joinColumns = @JoinColumn(name = "order_item_id"))
    @Column(name = "style_value_id", nullable = false)
    private List<Long> styleValueIds; // Reference to StyleValue (Array of ObjectIds)

    @Column(nullable = false)
    private Integer count; // Quantity, required, min 1

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Automatically generated creation time

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Automatically updated modification time
}

