package vn.HKT.entities;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UserFollowProduct")
public class UserFollowProduct {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // _id: Primary key, auto-generated
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId; // Id của user follow (required, ref: User)

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product productId; // Id của product được follow (required, ref: Product)

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt; // Thời gian khởi tạo (auto-generated)

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt; // Thời gian cập nhật (auto-updated)
}
