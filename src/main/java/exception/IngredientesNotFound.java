package exception;

public class IngredientesNotFound extends IllegalArgumentException {
    private static final String MSG = "Ingrediente nao existe no cardapio.";

    public IngredientesNotFound() {
        super(MSG);
    }
}
