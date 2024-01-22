package com.radev.project.dto.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegister {
    @NotBlank
    @Size(min = 3, max = 20)
    @Column(unique = true)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    @Column(unique = true)
    private String email;
    private List<Long> roleIds;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
