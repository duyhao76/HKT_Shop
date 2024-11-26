package vn.HKT.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Order") // Nên đặt tên bảng là Orders để tránh trùng từ khóa SQL
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id; // _id: Primary key, auto-generated

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user; // userId: Required, reference to User

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "store_id", nullable = false)
	private Store store; // storeId: Required, reference to Store

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "delivery_id", nullable = false)
	private Delivery delivery; // deliveryId: Required, reference to Delivery

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "commission_id", nullable = false)
	private Commission commission; // commissionId: Required, reference to Commission

	@Column(name = "address", nullable = false)
	private String address; // address: Required

	@Column(name = "phone", nullable = false)
	private String phone; // phone: Required

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	@Builder.Default
	private OrderStatus status = OrderStatus.NOT_PROCESSED; // status: Enum with default value

	@Column(name = "is_paid_before", nullable = false)
	@Builder.Default
	private boolean isPaidBefore = false; // isPaidBefore: Default to false

	@Column(name = "amount_from_user", nullable = false, precision = 10, scale = 2)
	private BigDecimal amountFromUser; // amountFromUser: Required, min 0

	@Column(name = "amount_from_store", nullable = false, precision = 10, scale = 2)
	private BigDecimal amountFromStore; // amountFromStore: Required, min 0

	@Column(name = "amount_to_store", nullable = false, precision = 10, scale = 2)
	private BigDecimal amountToStore; // amountToStore: Required, min 0

	@Column(name = "amount_to_gd", nullable = false, precision = 10, scale = 2)
	private BigDecimal amountToGD; // amountToGD: Required, min 0

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	@Builder.Default
	private Date createdAt = new Date(); // createdAt: Auto-generated

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	@Builder.Default
	private Date updatedAt = new Date(); // updatedAt: Auto-updated

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}

	// Enum for Order Status
	public enum OrderStatus {
		NOT_PROCESSED("not processed"), PROCESSING("processing"), SHIPPED("shipped"), DELIVERED("delivered"),
		CANCELLED("cancelled");

		private final String value;

		OrderStatus(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
}
