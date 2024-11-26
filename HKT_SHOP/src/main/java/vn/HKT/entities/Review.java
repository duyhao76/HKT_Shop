package vn.HKT.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data // Bao gồm getter, setter, toString, equal, hashcode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Review")
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id; // _id: Primary key, auto-generated

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false) // Khóa ngoại tới User
	private User user; // Id của user review (required, ref: User)

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "product_id", nullable = false) // Khóa ngoại tới Product
	private Product product; // Id của product được review (required, ref: Product)

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "store_id", nullable = false) // Khóa ngoại tới Store
	private Store store; // Id của store được review (required, ref: Store)

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "order_id", nullable = false) // Khóa ngoại tới Order
	private Order order; // Id của order được review (required, ref: Order)

	@Column(name = "content", nullable = false, length = 1000)
	private String content; // Nội dung review (required, maxLength 1000)

	@Column(name = "stars", nullable = false)
	private int stars; // Số sao (required, min 0, max 5)

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt; // Thời gian khởi tạo

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt; // Thời gian cập nhật

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
		updatedAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}
}
