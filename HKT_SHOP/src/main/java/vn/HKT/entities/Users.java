package vn.HKT.entities;

import java.time.LocalDate;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId")
    private Roles role;

    @Column(nullable = false, updatable = false)
    @Builder.Default
    private LocalDate createdDate = LocalDate.now();

    @Column
    @Builder.Default
    private LocalDate lastLogin = LocalDate.now();

    @Column
    private String token;

    @Column
    private LocalDateTime expiry;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Orders> orders;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Inventory> inventoryRecords;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastLogin = LocalDate.now();
    }
    
    public Users(String username, String password, String email, Roles role, String token, LocalDateTime expiry) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.token = token;
        this.expiry = expiry;
        this.createdDate = LocalDate.now(); // Gán giá trị mặc định
        this.lastLogin = LocalDate.now();   // Gán giá trị mặc định
    }

}

