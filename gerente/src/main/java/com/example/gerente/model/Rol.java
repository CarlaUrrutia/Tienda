package com.example.gerente.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "rol")
public class Rol {
    
    @NotNull(message = "El id no debe ser nulo")
    @NotEmpty(message = "el id no debe ser nulo")
    @Min(value = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rol;
    @Column(name = "nombre",nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 50, message = "Error nombre no valido")
    private String nombre_rol;


}
