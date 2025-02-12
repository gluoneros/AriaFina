package com.ariafina.crud_tareas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre de la tarea no puede estar vacío.")
    @Size(max = 100, message = "El nombre de la tarea no puede superar los 100 caracteres.")
    private String nombre;

    public Tarea() {}

    public Tarea(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
