package vn.HKT.entities;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "StyleValue")
public class StyleValue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id; // _id: Primary key, auto-generated
	
	@Column(name = "name", nullable = false, unique = true, length = 32)
	private String name; // Required, maxLength: 32, unique

	@ManyToOne  // Quan hệ nhiều StyleValue thuộc một Style
    @JoinColumn(name = "style_id", nullable = false) // Tạo foreign key liên kết với bảng Style
    private Style style; // Tham chiếu tới Style
	
	@Column(name = "is_deleted", nullable = false)
	@Builder.Default
    private Boolean isDeleted = false; // Soft delete, mặc định là false
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date createdAt; // Thời gian khởi tạo, tự động tạo
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date updatedAt; // Thời gian cập nhật, tự động cập nhật
}