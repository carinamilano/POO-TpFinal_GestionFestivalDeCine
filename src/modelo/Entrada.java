package modelo;

public abstract class Entrada {
    private String codigo;
    private Funcion funcion;
    private Espectador espectador;

    public Entrada(String codigo, Funcion funcion, Espectador espectador) {
        this.codigo = codigo;
        this.funcion = funcion;
        this.espectador = espectador;
    }

    public String getCodigo() {
        return codigo;
    }
    public Funcion getFuncion() {
        return funcion;
    }
    public Espectador getEspectador() {
        return espectador;
    }

    @Override
    public String toString() {
        return "Entrada: " + codigo + " - " + funcion.getPelicula().getTitulo()
                + " - " + funcion.getFecha() + " " + funcion.getHorario();
    }
}