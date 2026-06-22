package com.kishore.issue_management.entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.kishore.issue_management.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String name;
    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @JsonIgnore
    @Column(nullable = false)
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;


}