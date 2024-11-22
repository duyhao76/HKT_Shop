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
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private String id; // Primary key, auto-generated

    @Column(name = "name", unique = true, nullable = false, length = 32)
    private String name; // Required, unique, maxLength: 32

    @Column(name = "slug", unique = true)
    private String slug; // Auto-generated, unique

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory; // Nullable, ref: Category (parent)

    @Column(name = "image")
    private String image; // Image URL for the category

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
