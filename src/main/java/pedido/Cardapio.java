package pedido;

import exception.IngredientesNotFound;
import exception.PrecoInvalid;
import ingredientes.Ingrediente;
import java.util.TreeMap;

public class Cardapio {
    private final TreeMap<Ingrediente,Double> precos;

    public Cardapio(){
        this.precos= new TreeMap<>();
    }

    public TreeMap<Ingrediente, Double> getPrecos(){
        return this.precos;
    }

    public void adicionarIngrediente(final Ingrediente ingrediente,final Double preco) throws PrecoInvalid{
        validarPreco(preco);
        precos.put(ingrediente,preco);
    }

    public void atualizarIngrediente(final Ingrediente ingrediente,final Double preco) throws PrecoInvalid, IngredientesNotFound{
        validarPreco(preco);
        final var ingredienteExiste =  precos.replace(ingrediente, buscarPreco(ingrediente), preco);
        if (!ingredienteExiste)
            throw new IngredientesNotFound();
    }

    public void removerIngrediente(final Ingrediente ingrediente) throws IngredientesNotFound{
        final var ingredienteExiste =  precos.remove(ingrediente, buscarPreco(ingrediente));
        if (!ingredienteExiste)
            throw new IngredientesNotFound();
    }

    public Double buscarPreco(final Ingrediente ingrediente) throws IngredientesNotFound{
        if (precos.containsKey(ingrediente))
            return precos.get(ingrediente);
        throw new IngredientesNotFound();
    }

    private void validarPreco(final Double preco) throws PrecoInvalid{
        if (preco <= 0)
            throw new PrecoInvalid();
    }

    @Override
    public String toString() {
        return this.precos.toString();
    }
}
