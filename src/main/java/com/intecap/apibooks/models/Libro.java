package com.intecap.apibooks.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "libro")
public class Libro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    private String nombre;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
