package exception;

public class PrecoInvalid extends IllegalArgumentException{
    private static final String MSG = "Preco invalido.";

    public PrecoInvalid() {
        super(MSG);
    }
}
