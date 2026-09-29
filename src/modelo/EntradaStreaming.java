package modelo;

public class EntradaStreaming extends Entrada {
    private String enlaceAcceso;

    public EntradaStreaming(String codigo, Funcion funcion, Espectador espectador, String enlaceAcceso) {
        super(codigo, funcion, espectador);
        this.enlaceAcceso = enlaceAcceso;
    }

    public String getEnlaceAcceso() { return enlaceAcceso; }

    @Override
    public String toString() {
        return super.toString() + " - Enlace: " + enlaceAcceso;
    }
}