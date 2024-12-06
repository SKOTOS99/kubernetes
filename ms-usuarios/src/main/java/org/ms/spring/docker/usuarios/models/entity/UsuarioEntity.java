package org.ms.spring.docker.usuarios.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "usuarios")
@Setter
@Getter
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String email;

    private String password;

}
