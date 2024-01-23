package com.radev.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "[User]")
@Getter
@Setter
@SQLDelete(sql = "UPDATE [User] SET is_deleted = 'true', deleted_date = GETDATE() WHERE id = ?")
@Where(clause = "is_deleted = false")
public class User extends BaseEntity{
    @Column(name = "username")
    private String username;
    @Email
    @Column(name = "email")
    private String email;
    @JsonIgnore
    @Column(name = "password")
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "User_Role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
    @Column(name = "is_deleted")
    private boolean isDeleted = false;
    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    public User(Long id, LocalDateTime createdDate, String createdBy, LocalDateTime updatedDate, String updatedBy, String username, String email, String password) {
        super(id, createdDate, createdBy, updatedDate, updatedBy);
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User(Long id,String username, String email) {
        this.setId(id);
        this.username = username;
        this.email = email;
    }

    public User(Long id, LocalDateTime createdDate, String createdBy, LocalDateTime updatedDate, String updatedBy) {
        super(id, createdDate, createdBy, updatedDate, updatedBy);
    }

    public User() {
    }
}
