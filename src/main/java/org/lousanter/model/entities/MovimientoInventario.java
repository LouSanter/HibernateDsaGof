package org.lousanter.model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class MovimientoInventario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String tipoMovimiento;
    private int cantidad;

    private String motivo;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

}
