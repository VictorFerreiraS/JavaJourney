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

    public void adicionarIngrediente(final Ingrediente ingrediente,final Double preco){
        validarPreco(preco);
        precos.put(ingrediente,preco);
    }

    public void atualizarIngrediente(final Ingrediente ingrediente,final Double preco){
        validarPreco(preco);
        if (precos.containsKey(ingrediente))
            precos.replace(ingrediente, buscarPreco(ingrediente), preco);
        else
            throw new IngredientesNotFound();
    }

    public void removerIngrediente(final Ingrediente ingrediente) {
        if (precos.containsKey(ingrediente))
            precos.remove(ingrediente);
        else
            throw new IngredientesNotFound();
    }

    public Double buscarPreco(final Ingrediente ingrediente){
        if (precos.containsKey(ingrediente))
            return precos.get(ingrediente);
        else
            throw new IngredientesNotFound();
    }

    private void validarPreco(final Double preco) {
        if (preco <= 0)
            throw new PrecoInvalid();
    }

    @Override
    public String toString() {
        return this.precos.toString();
    }
}
