package exception;

public class ItemNotFound extends IllegalArgumentException {
    private static final String MSG = "Item nao existe no pedido.";

    public ItemNotFound() {
        super(MSG);
    }
}
