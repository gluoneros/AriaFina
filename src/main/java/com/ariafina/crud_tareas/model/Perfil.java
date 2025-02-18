package com.ariafina.crud_tareas.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Perfil {
    OPERADOR, ADMINISTRADOR, CONSULTA;

    @JsonCreator
    public static Perfil fromString(String value) {
        for (Perfil p : Perfil.values()) {
            if (value.equalsIgnoreCase(p.name())) {
                return p;
            }
        }
        throw new IllegalArgumentException("Valor no válido para Perfil: " + value);
    }

    @JsonValue
    public String toJson() {
        return name().toUpperCase();
    }
}
