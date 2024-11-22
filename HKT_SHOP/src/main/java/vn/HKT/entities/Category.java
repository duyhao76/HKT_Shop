package vn.HKT.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String _id; // Primary key, auto-generated

    @Column(unique = true, nullable = false, length = 32)
    private String name; // Required, unique, maxLength: 32

    @Column(unique = true)
    private String slug; // Auto-generated, unique

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId; // Nullable, ref: Category

    private String image; // Image URL for the category

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false; // Default: false for soft-delete

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Auto-generated creation timestamp

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Auto-updated update timestamp
}
