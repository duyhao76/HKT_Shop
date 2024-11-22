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
@Table(name = "UserLevel")
public class UserLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // Primary key, auto-generated

    @Column(name = "name", nullable = false, unique = true, length = 32)
    private String name; // User level name (Unique, required, maxLength 32)

    @Column(name = "min_point", nullable = false, unique = true)
    private int minPoint; // Minimum point required for this user level (Unique, required)

    @Column(name = "discount", nullable = false, precision = 10, scale = 2)
    private BigDecimal discount; // Discount for this user level (Required)

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private boolean isDeleted = false; // Soft-delete flag (Default: false)

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Auto-generated creation time

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Auto-updated modification time
}
