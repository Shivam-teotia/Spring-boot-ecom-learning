package com.ecommerce.sb_ecom.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "role_id")
    private Long roleId;

    @ToString.Exclude
    @Enumerated(EnumType.STRING)
    @Column(length = 20,name = "role_name")
    private AppRole roleName;

    public Role(AppRole roleName) {
        this.roleName = roleName;
    }
}