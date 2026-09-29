package servicios;

import modelo.*;
import excepciones.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EntradaServicio {
    private List<Funcion> funciones;
    private List<Espectador> espectadores;
    private List<Entrada> entradas;
    private int contadorCodigo;

    public EntradaServicio() {
        this.funciones = new ArrayList<>();
        this.espectadores = new ArrayList<>();
        this.entradas = new ArrayList<>();
        this.contadorCodigo = 1;
    }

    public void registrarEspectador(String nombre, String email, String nacionalidad) {
        Espectador e = new Espectador(nombre, email, nacionalidad);
        espectadores.add(e);
    }

    public void programarFuncionPresencial(Pelicula pelicula, Sala sala, LocalDate fecha, LocalTime horario) {
        if (fecha.isBefore(LocalDate.now())) {
            throw new FestivalException("No se puede programar una función en una fecha pasada.");
        }
        for (Funcion f : funciones) {
            if (f instanceof FuncionPresencial) {
                FuncionPresencial fp = (FuncionPresencial) f;
                if (fp.getSala().getNombre().equalsIgnoreCase(sala.getNombre())
                        && fp.getFecha().equals(fecha)
                        && fp.getHorario().equals(horario)) {
                    throw new FuncionNoDisponibleException("ya existe una funcion en esa sala, fecha y horario.");
                }
            }
        }
        FuncionPresencial funcion = new FuncionPresencial(fecha, horario, pelicula, sala);
        pelicula.agregarFuncion(funcion);
        funciones.add(funcion);
    }

    public void programarFuncionStreaming(Pelicula pelicula, String enlace, int capacidad, LocalDate fecha, LocalTime horario) {
        if (fecha.isBefore(LocalDate.now())) {
            throw new FestivalException("No se puede programar una función en una fecha pasada.");
        }
        FuncionStreaming funcion = new FuncionStreaming(fecha, horario, pelicula, enlace, capacidad);
        pelicula.agregarFuncion(funcion);
        funciones.add(funcion);
    }

    public EntradaPresencial venderEntradaPresencial(String nombreEspectador, FuncionPresencial funcion, int numeroButaca) {
        if (numeroButaca < 1 || numeroButaca > funcion.getSala().getCantidadButacas()) {
            throw new FestivalException("Número de butaca inválido. Debe estar entre 1 y " + funcion.getSala().getCantidadButacas());
        }
        if (!funcion.verificarDisponibilidad()) {
            throw new CapacidadExcedidaException(funcion.getPelicula().getTitulo());
        }
        if (!funcion.butacaDisponible(numeroButaca)) {
            throw new ButacaOcupadaException(numeroButaca);
        }
        Espectador espectador = buscarEspectador(nombreEspectador);
        String codigo = "EP-" + contadorCodigo++;
        EntradaPresencial entrada = new EntradaPresencial(codigo, funcion, espectador, numeroButaca);
        funcion.ocuparButaca(numeroButaca);
        espectador.agregarEntrada(entrada);
        entradas.add(entrada);
        return entrada;
    }

    public EntradaStreaming venderEntradaStreaming(String nombreEspectador, FuncionStreaming funcion) {
        if (!funcion.verificarDisponibilidad()) {
            throw new CapacidadExcedidaException(funcion.getPelicula().getTitulo());
        }
        Espectador espectador = buscarEspectador(nombreEspectador);
        String codigo = "ES-" + contadorCodigo++;
        EntradaStreaming entrada = new EntradaStreaming(codigo, funcion, espectador, funcion.getEnlace());
        funcion.agregarEspectador();
        espectador.agregarEntrada(entrada);
        entradas.add(entrada);
        return entrada;
    }

    public Espectador buscarEspectador(String nombre) {
        for (Espectador e : espectadores) {
            if (e.getNombre().equalsIgnoreCase(nombre)) return e;
        }
        throw new EntidadNoEncontradaException("Espectador", "nombre: " + nombre);
    }

    public Funcion buscarFuncion(String tituloPelicula, LocalDate fecha) {
        for (Funcion f : funciones) {
            if (f.getPelicula().getTitulo().equalsIgnoreCase(tituloPelicula) && f.getFecha().equals(fecha)) {
                return f;
            }
        }
        throw new EntidadNoEncontradaException("Funcion", "pelicula: " + tituloPelicula);
    }

    public List<Funcion> getFunciones() {
        return funciones;
    }
    public List<Espectador> getEspectadores() {
        return espectadores;
    }
    public List<Entrada> getEntradas() {
        return entradas;
    }
}