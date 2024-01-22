package com.radev.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "[Role]")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity{
    @Column(length = 20)
    private String name;
    public Role(Long id) {
        super(id);
    }
}
