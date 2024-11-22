package vn.HKT.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "StoreLevel")
public class StoreLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id; // Primary key, auto-generated

    @Column(name = "name", unique = true, nullable = false, length = 32)
    private String name; // Required, unique, maxLength: 32

    @Column(name = "min_point", nullable = false)
    private int minPoint; // Required, unique

    @Column(name = "discount", nullable = false, precision = 18, scale = 2)
    private BigDecimal discount; // Required, Discount for StoreLevel

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false; // Default: false for soft-delete

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Auto-generated creation timestamp

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Auto-updated update timestamp
}
