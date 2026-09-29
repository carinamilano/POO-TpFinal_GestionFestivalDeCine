package modelo;

public class EntradaPresencial extends Entrada {
    private int butaca;

    public EntradaPresencial(String codigo, Funcion funcion, Espectador espectador, int butaca) {
        super(codigo, funcion, espectador);
        this.butaca = butaca;
    }

    public int getButaca() { return butaca; }

    @Override
    public String toString() {
        return super.toString() + " - Butaca: " + butaca;
    }
}