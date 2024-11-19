package vn.HKT.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data // (Bao gồm: getter, setter, ToString, equal, hashcode)
@NoArgsConstructor
@AllArgsConstructor
@Builder // Cách hoạt động gần giống với contructor không tham số nhưng clean và xịn hơn
@Entity
@Table(name = "Review")
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id; // _id: Primary key, auto-generated

	@Column(name = "user_id", nullable = false)
	private String userId; // Id của user review (required, ref: User)

	@Column(name = "product_id", nullable = false)
	private String productId; // Id của product được review (required, ref: Product)

	@Column(name = "store_id", nullable = false)
	private String storeId; // Id của store được review (required, ref: Store)

	@Column(name = "order_id", nullable = false)
	private String orderId; // Id của order được review (required, ref: Order)

	@Column(name = "content", nullable = false, length = 1000)
	private String content; // Nội dung review (required, maxLength 1000)

	@Column(name = "stars", nullable = false)
	private int stars; // Số sao (required, min 0, max 5)

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Date createdAt; // Thời gian khởi tạo

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt; // Thời gian cập nhật

}
