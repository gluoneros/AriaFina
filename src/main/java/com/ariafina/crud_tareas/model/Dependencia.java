package com.ariafina.crud_tareas.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Dependencia {
    FABRICA, CONSULTORIA, DESARROLLO, ADMINISTRACION;

    @JsonCreator
    public static Dependencia fromString(String value) {
        for (Dependencia d : Dependencia.values()) {
            if (d.name().equalsIgnoreCase(value)) {
                return d;
            }
        }
        throw new IllegalArgumentException("Valor no válido para Dependencia: " + value);
    }

    @JsonValue
    public String toJson() {
        return name().toUpperCase(); // O cambia a .toLowerCase() si prefieres
    }
}