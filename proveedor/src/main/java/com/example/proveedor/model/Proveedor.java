package com.example.proveedor.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Valid
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "proveedor")
public class Proveedor extends RepresentationModel<Proveedor> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_proveedor;

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error nombre no valido")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error contacto no valido")
    @Column(name = "contacto", nullable = false)
    private String contacto;

    //Es un caso especial porque
}
