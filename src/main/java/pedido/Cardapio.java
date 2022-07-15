package pedido;

import exception.IngredientesNotFound;
import exception.PrecoInvalid;
import ingredientes.Ingrediente;
import java.util.TreeMap;

public class Cardapio {
    private TreeMap<Ingrediente,Double> precos;

    public Cardapio(){
        this.precos= new TreeMap<>();
    }

    public TreeMap<Ingrediente, Double> getPrecos(){
        return this.precos;
    }

    public void adicionarIngrediente(Ingrediente ingrediente,Double preco){
        validarPreco(preco);
        precos.put(ingrediente,preco);
    }

    public void atualizarIngrediente(Ingrediente ingrediente,Double preco){
        validarPreco(preco);
        if (precos.containsKey(ingrediente)){
            precos.replace(ingrediente, buscarPreco(ingrediente), preco);
        }
        else {
            throw new IngredientesNotFound();
        }
    }

    public void removerIngrediente(Ingrediente ingrediente) {
        if (precos.containsKey(ingrediente))
            precos.remove(ingrediente);
        else
            throw new IngredientesNotFound();
    }

    public Double buscarPreco(Ingrediente ingrediente){
        if (precos.containsKey(ingrediente))
            return precos.get(ingrediente);
        else
            throw new IngredientesNotFound();
    }

    private void validarPreco(Double preco) {
        if (preco <= 0) {
            throw new PrecoInvalid();
        }
    }

    @Override
    public String toString() {
        return this.precos.toString();
    }
}
