package vn.HKT.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CartItem") // Đặt tên bảng dạng số nhiều
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Primary key, auto-generated

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart; // Reference to Cart entity

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product; // Reference to Product entity

	@ElementCollection
	@CollectionTable(name = "CartItem_StyleValues", joinColumns = @JoinColumn(name = "cart_item_id"))
	@Column(name = "style_value_id", nullable = false)
	private List<Long> styleValueIds; // List of selected style values (e.g., size, color)

	@Column(nullable = false)
	private int count; // Quantity of the product (min: 1)

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt; // Automatically generated creation time

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt; // Automatically updated modification time
}
