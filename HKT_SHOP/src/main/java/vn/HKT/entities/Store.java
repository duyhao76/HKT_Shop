package vn.HKT.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String _id; // Primary key, auto-generated

    @Column(unique = true, nullable = false, length = 100)
    private String name; // Required, maxLength: 100, unique

    @Column(nullable = false, length = 1000)
    private String bio; // Required, maxLength: 1000

    @Column(unique = true)
    private String slug; // Auto-generated, unique

    @ManyToOne
    @JoinColumn(name = "ownerId", nullable = false)
    private User owner; // Reference to User entity (owner of the store)

    @ElementCollection
    @CollectionTable(name = "StoreStaffs", joinColumns = @JoinColumn(name = "store_id"))
    @Column(name = "staff_id")
    private List<String> staffIds = new ArrayList<>(); // List of staff IDs (ObjectId - reference to User)

    @Column(nullable = false)
    @Builder.Default
    private Boolean isActive = false; // Default: false

    @Column(nullable = false)
    @Builder.Default
    private Boolean isOpen = false; // Default: false

    private String avatar; // Path to avatar

    private String cover; // Path to cover

    @ElementCollection
    @CollectionTable(name = "StoreFeaturedImages", joinColumns = @JoinColumn(name = "store_id"))
    @Column(name = "image_url")
    private List<String> featuredImages = new ArrayList<>(); // List of featured images (URLs)

    @ManyToOne
    @JoinColumn(name = "commissionId")
    private Commission commission; // Reference to Commission entity

    @Column(nullable = false)
    @Builder.Default
    private int point = 0; // Default: 0

    // Đảm bảo rating trong khoảng 0 đến 5
    @Column(nullable = false)
    @Builder.Default
    private double rating = 3.0; // Default: 3.0, Min: 0, Max: 5

    @Column(nullable = false, precision = 18, scale = 2)
    @Builder.Default
    private BigDecimal eWallet = BigDecimal.ZERO; // Default: 0

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Auto-generated

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Auto-updated

    // Custom setter to ensure rating is within range [0, 5]
    public void setRating(double rating) {
        if (rating < 0) {
            this.rating = 0;
        } else if (rating > 5) {
            this.rating = 5;
        } else {
            this.rating = rating;
        }
    }
}
