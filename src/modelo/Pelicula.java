package modelo;

import java.util.ArrayList;
import java.util.List;

public class Pelicula {
    private String titulo;
    private String genero;
    private int duracion;
    private Director director;
    private List<Actor> actores;
    private List<Evaluacion> evaluaciones;
    private List<Funcion> funciones;

    public Pelicula(String titulo, String genero, int duracion, Director director) {
        this.titulo = titulo;
        this.genero = genero;
        this.duracion = duracion;
        this.director = director;
        this.actores = new ArrayList<>();
        this.evaluaciones = new ArrayList<>();
        this.funciones = new ArrayList<>();
    }

    public void agregarActor(Actor a) {
        actores.add(a);
    }

    public void agregarEvaluacion(Evaluacion e) {
        evaluaciones.add(e);
    }

    public void agregarFuncion(Funcion f) {
        funciones.add(f);
    }

    public double calcularPromedio() {
        if (evaluaciones.isEmpty()) return 0;
        double suma = 0;
        for (Evaluacion e : evaluaciones) {
            suma += e.getPuntaje();
        }
        return suma / evaluaciones.size();
    }

    public String getTitulo() {
        return titulo;
    }
    public String getGenero() {
        return genero;
    }
    public int getDuracion() {
        return duracion;
    }
    public Director getDirector() {
        return director;
    }
    public List<Actor> getActores() {
        return actores;
    }
    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }
    public List<Funcion> getFunciones() {
        return funciones;
    }

    @Override
    public String toString() {
        return titulo + " (" + genero + ", " + duracion + " min) - Dir: " + director.getNombre();
    }
}