package servicios;

import modelo.*;
import excepciones.EntidadNoEncontradaException;

import java.util.ArrayList;
import java.util.List;

public class PeliculaServicio {
    private List<Pelicula> peliculas;
    private List<Director> directores;
    private List<Actor> actores;
    private List<Jurado> jurados;
    private List<Evaluacion> evaluaciones;

    public PeliculaServicio() {
        this.peliculas = new ArrayList<>();
        this.directores = new ArrayList<>();
        this.actores = new ArrayList<>();
        this.jurados = new ArrayList<>();
        this.evaluaciones = new ArrayList<>();
    }

    public void registrarDirector(String nombre, String email, String nacionalidad) {
        Director d = new Director(nombre, email, nacionalidad);
        directores.add(d);
    }

    public void registrarActor(String nombre, String email, String nacionalidad) {
        Actor a = new Actor(nombre, email, nacionalidad);
        actores.add(a);
    }

    public void registrarPelicula(String titulo, String genero, int duracion, String nombreDirector) {
        Director director = buscarDirector(nombreDirector);
        Pelicula p = new Pelicula(titulo, genero, duracion, director);
        director.agregarPelicula(p);
        peliculas.add(p);
    }

    public void asociarActorAPelicula(String nombreActor, String tituloPelicula) {
        Actor actor = buscarActor(nombreActor);
        Pelicula pelicula = buscarPelicula(tituloPelicula);
        actor.agregarPelicula(pelicula);
        pelicula.agregarActor(actor);
    }

    public void registrarJurado(String nombre, String email, String nacionalidad) {
        Jurado j = new Jurado(nombre, email, nacionalidad);
        jurados.add(j);
    }
    public void agregarJurado(Jurado j) {
        jurados.add(j);
    }

    public void registrarEvaluacion(String nombreJurado, String tituloPelicula, double puntaje) {
        Jurado jurado = buscarJurado(nombreJurado);
        Pelicula pelicula = buscarPelicula(tituloPelicula);
        Evaluacion e = new Evaluacion(puntaje, pelicula, jurado);
        jurado.agregarEvaluacion(e);
        pelicula.agregarEvaluacion(e);
        evaluaciones.add(e);
    }

    public Pelicula determinarGanadora(Seccion seccion) {
        return seccion.determinarGanadora();
    }

    public Director buscarDirector(String nombre) {
        for (Director d : directores) {
            if (d.getNombre().equalsIgnoreCase(nombre)) return d;
        }
        throw new EntidadNoEncontradaException("Director", "nombre: " + nombre);
    }

    public Actor buscarActor(String nombre) {
        for (Actor a : actores) {
            if (a.getNombre().equalsIgnoreCase(nombre)) return a;
        }
        throw new EntidadNoEncontradaException("Actor", "nombre: " + nombre);
    }

    public Jurado buscarJurado(String nombre) {
        for (Jurado j : jurados) {
            if (j.getNombre().equalsIgnoreCase(nombre)) return j;
        }
        throw new EntidadNoEncontradaException("Jurado", "nombre: " + nombre);
    }

    public Pelicula buscarPelicula(String titulo) {
        for (Pelicula p : peliculas) {
            if (p.getTitulo().equalsIgnoreCase(titulo)) return p;
        }
        throw new EntidadNoEncontradaException("Pelicula", "titulo: " + titulo);
    }

    public List<Pelicula> getPeliculas() { return peliculas; }
    public List<Director> getDirectores() { return directores; }
    public List<Actor> getActores() { return actores; }
    public List<Jurado> getJurados() { return jurados; }
    public List<Evaluacion> getEvaluaciones() { return evaluaciones; }
}