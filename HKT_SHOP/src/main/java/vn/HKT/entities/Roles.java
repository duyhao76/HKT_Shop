package vn.HKT.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    
    @Column(nullable = false, length = 50)
    private String roleName;
    
    @Column(length = 200)
    private String description;
    
    @OneToMany(mappedBy = "role")
    private List<Users> users;
}
