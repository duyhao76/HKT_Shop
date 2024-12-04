package vn.HKT.entities;

import java.time.LocalDate;
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
    private LocalDate createdDate = LocalDate.now(); // Thời gian tạo mặc định
    
    @Column
    @Builder.Default
    private LocalDate lastLogin = LocalDate.now(); // Thời gian đăng nhập mặc định

    @Column
    private String token;

    @Column
    private LocalDate expiry;

    @OneToMany(mappedBy = "user")
    private List<Orders> orders;

    @OneToMany(mappedBy = "user")
    private List<Inventory> inventoryRecords;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDate.now(); // Gán thời gian tạo khi insert
    }

    @PreUpdate
    protected void onUpdate() {
        lastLogin = LocalDate.now(); // Cập nhật thời gian đăng nhập khi có update
    }
}
