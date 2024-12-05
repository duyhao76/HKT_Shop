package vn.HKT.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categories {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    
    @Column(nullable = false, length = 50)
    private String categoryName;
    
    @Column(length = 200)
    private String description;
    
    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isActive;
    
    @OneToMany(mappedBy = "category")
    private List<Products> products;
}
