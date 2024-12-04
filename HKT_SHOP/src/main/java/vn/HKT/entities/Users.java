package vn.HKT.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(unique = true, length = 100)
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "roleId")
    private Roles role;
    
    @Column(nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdDate = LocalDateTime.now(); // Thời gian tạo mặc định
    
    @Column
    @Builder.Default
    private LocalDateTime lastLogin = LocalDateTime.now(); // Thời gian đăng nhập mặc định

    @Column
    private String token;

    @Column
    private LocalDateTime expiry;

    @OneToMany(mappedBy = "user")
    private List<Orders> orders;

    @OneToMany(mappedBy = "user")
    private List<Inventory> inventoryRecords;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now(); // Gán thời gian tạo khi insert
        if (role == null) { // Nếu role chưa được chỉ định
            EntityManager em = Persistence.createEntityManagerFactory("HKT_PU").createEntityManager();
            role = em.find(Roles.class, 1L); // Lấy role với ID = 1 từ DB
        }
    }


    @PreUpdate
    protected void onUpdate() {
        lastLogin = LocalDateTime.now(); // Cập nhật thời gian đăng nhập khi có update
    }
}
