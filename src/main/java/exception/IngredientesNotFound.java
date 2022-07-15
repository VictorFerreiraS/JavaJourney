package exception;

public class IngredientesNotFound extends IllegalArgumentException {
    private static final String MESSAGE = "Ingrediente nao existe no cardapio.";

    public IngredientesNotFound() {
        super(MESSAGE);
    }
}
