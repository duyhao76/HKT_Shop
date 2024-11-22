package vn.HKT.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key, auto-generated

    @Column(nullable = false, length = 32)
    private String firstname; // Required, maxLength: 32

    @Column(nullable = false, length = 32)
    private String lastname; // Required, maxLength: 32

    @Column(unique = true)
    private String slug; // Auto-generated, unique

    @Column(unique = true)
    private String idCard; // Nullable, unique

    @Column(unique = true)
    private String email; // Unique

    @Column(unique = true)
    private String phone; // Unique

    @Column(nullable = false)
    private Boolean isEmailActive = false; // Default: false

    @Column(nullable = false)
    private Boolean isPhoneActive = false; // Default: false

    @Column(nullable = false)
    private String salt; // Auto-generated

    @Column(nullable = false)
    private String hashedPassword; // Required

    @Column(nullable = false)
    private String role = "user"; // Default: "user", Enum: ["user", "admin"]

    @ElementCollection
    @CollectionTable(name = "UserAddresses", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "address", length = 200)
    private List<String> addresses; // MaxLength: 200, Limit 6 addresses

    private String avatar; // Path to avatar

    private String cover; // Path to cover

    @Column(nullable = false)
    private int point = 0; // Default: 0

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal eWallet = BigDecimal.ZERO; // Default: 0, Min: 0

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Auto-generated

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Auto-updated
}
