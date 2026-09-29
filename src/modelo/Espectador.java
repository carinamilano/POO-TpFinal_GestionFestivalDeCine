package modelo;

import java.util.ArrayList;
import java.util.List;

public class Espectador extends Persona {
    private List<Entrada> entradas;

    public Espectador(String nombre, String email, String nacionalidad) {
        super(nombre, email, nacionalidad);
        this.entradas = new ArrayList<>();
    }

    public void agregarEntrada(Entrada e) {
        entradas.add(e);
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    @Override
    public String toString() {
        return "Espectador: " + getNombre();
    }
}