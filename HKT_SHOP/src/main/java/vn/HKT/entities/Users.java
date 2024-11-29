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
    
    @Column
    private LocalDateTime createdDate;
    
    @Column
    private LocalDateTime lastLogin;
    
    @OneToMany(mappedBy = "user")
    private List<Orders> orders;
    
    @OneToMany(mappedBy = "user")
    private List<Inventory> inventoryRecords;
}
