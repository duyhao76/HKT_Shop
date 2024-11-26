package vn.HKT.entities;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Category")
public class Delivery {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
    private Long id;  // _id: Primary key, auto-generated
	
	@Column(name = "name", nullable = false, unique = true, length = 100)
    private String name; // Tên delivery (required, unique, maxLength 100)
	
	@Column(name = "description", nullable = false, length = 1000)
    private String description; // Mô tả về delivery (required, maxLength 1000)
	
	@Column(name = "price", nullable = false)
	@Min(value = 0, message = "Giá phải lớn hơn 0")
    private double price; // Đơn giá (required, min 0)
	
	@Column(name = "is_deleted", nullable = false)
	@Builder.Default
    private boolean isDeleted = false; // Dùng cho soft-delete (default: false)
	
	@Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt; // Thời gian khởi tạo (auto-generated)

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt; // Thời gian cập nhật (auto-updated)
}
