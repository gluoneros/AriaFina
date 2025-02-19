package com.ariafina.crud_tareas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La fecha de nacimiento es obligatoria.")
    @Past(message = "La fecha de nacimiento debe ser en el pasado.")
    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;


    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    @Column(nullable = false)
    private Boolean activo;

    @NotNull(message = "La dependencia es obligatoria.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Dependencia dependencia;

    @NotNull(message = "El perfil es obligatorio.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Perfil perfil;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Fila> filas;

    // Constructores
    public Usuario() {}

    public Usuario(Date fechaNacimiento, String nombre, Boolean activo, Dependencia dependencia, Perfil perfil) {
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.activo = activo;
        this.dependencia = dependencia;
        this.perfil = perfil;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public Boolean getActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }
    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public Perfil getPerfil() {
        return perfil;
    }
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<Fila> getFilas() {
        return filas;
    }
    public void setFilas(List<Fila> filas) {
        this.filas = filas;
    }
}
