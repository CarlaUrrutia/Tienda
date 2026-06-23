package com.example.cliente.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Valid
@Data
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cliente;
    @Column(name = "nombre",nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error nombre no valido")
    private String nombre;
    @Column(name = "apellido",nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error apellido no valido")
    private String apellido;
    @Column(name = "email",nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error, email  no valido")
    private String email;
}