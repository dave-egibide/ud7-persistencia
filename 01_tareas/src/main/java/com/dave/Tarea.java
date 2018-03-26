package com.dave;

import java.util.Objects;

public class Tarea {
    private String descripcion;

    public Tarea(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarea tarea = (Tarea) o;
        return Objects.equals(descripcion, tarea.descripcion);
    }

    @Override
    public int hashCode() {

        return Objects.hash(descripcion);
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
