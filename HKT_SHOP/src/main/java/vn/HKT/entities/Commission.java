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
@Table(name = "Commission")
public class Commission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id; // Primary key, auto-generated

    @Column(name = "name", unique = true, nullable = false, length = 32)
    private String name; // Commission name, unique, required, maxLength: 32

    @Column(name = "cost", nullable = false, precision = 18, scale = 2)
    @Builder.Default
    private BigDecimal cost = BigDecimal.ZERO; // Cost of the commission, required, min: 0

    @Column(name = "description", nullable = false, length = 3000)
    private String description; // Description, required, maxLength: 3000

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false; // Soft delete flag, default: false

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Auto-generated creation time

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Auto-updated modification time
}
