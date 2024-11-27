package vn.HKT.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Style")
public class Style {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id; // _id: Primary key, auto-generated
	
	@Column(name = "name", nullable = false, unique = true, length = 32)
	private String name; // Required, maxLength: 32, unique
	
	@ManyToMany
    @JoinTable(
        name = "style_category", // Tên bảng liên kết
        joinColumns = @JoinColumn(name = "style_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
	@Builder.Default
    private List<Category> categories = new ArrayList<>(); // Tập các Category liên kết

	@OneToMany(mappedBy = "style", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<StyleValue> styleValues = new ArrayList<>(); // Thêm mối quan hệ với StyleValue
	
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
