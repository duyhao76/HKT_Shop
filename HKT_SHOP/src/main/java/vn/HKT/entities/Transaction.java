package vn.HKT.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Transaction") // Đặt tên bảng dạng số nhiều
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // ObjectId tương đương với Long trong JPA

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user; // Liên kết với bảng User

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "store_id", nullable = false)
	private Store store; // Liên kết với bảng Store

	@Column(name = "is_up", nullable = false)
	private Boolean isUp; // Giao dịch nạp/rút

	@Column(name = "amount", nullable = false, precision = 19, scale = 2)
	private BigDecimal amount; // Số tiền giao dịch

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt; // Thời gian tạo

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt; // Thời gian cập nhật
}
