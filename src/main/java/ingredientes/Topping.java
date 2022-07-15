package ingredientes;

public class Topping implements Adicional,Comparable<Ingrediente> {
    private final TipoTopping tipoTopping;

    public Topping(final TipoTopping tipoTopping) {
        this.tipoTopping = tipoTopping;
    }

    public TipoTopping getTipoTopping(){
        return this.tipoTopping;
    }

    @Override
    public int compareTo(final Ingrediente ingrediente) {
        return this.obterTipo().toString().compareTo(ingrediente.obterTipo().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topping)) return false;
        Topping topping = (Topping) o;
        return tipoTopping == topping.tipoTopping;
    }

    @Override
    public int hashCode() {
        return tipoTopping.hashCode();
    }

    @Override
    public String toString() {
        return this.tipoTopping.toString();
    }

    @Override
    public Enum obterTipo() {
        return this.tipoTopping;
    }
}
