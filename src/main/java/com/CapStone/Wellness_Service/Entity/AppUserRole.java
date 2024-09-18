package com.CapStone.Wellness_Service.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppUserRole {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String name;
    public AppUserRole(String name){
        this.name = name;
    }


}
