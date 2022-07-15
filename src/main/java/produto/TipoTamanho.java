package produto;

public enum TipoTamanho {
    P (0), M(0.3), G(0.5);
    public final double multiplicador;

    TipoTamanho(final double multiplicador) {
        this.multiplicador = multiplicador;
    }
}
