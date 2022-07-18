package pedido;

import exception.ItemNotFound;
import java.util.*;

public class Pedido{

    private final int id;
    private final ArrayList<ItemPedido> itens;
    private final Cliente cliente;

    public Pedido(int id, ArrayList<ItemPedido> itens,Cliente cliente){
        this.id = id;
        this.itens=itens;
        this.cliente=cliente;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public int getId(){
        return this.id;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public double calcularTotal(final Cardapio cardapio) {
        return itens.stream()
                .map(itemPedido -> {
                    final var shake = itemPedido.getShake();
                    final var adiocinais = shake.getAdicionais();
                    final var precoBase = cardapio.buscarPreco(shake.getBase());
                    final var precoAdicionais = adiocinais.stream().map(cardapio::buscarPreco).reduce(0.0, Double::sum);
                    final var acrescimoBase = shake.getTipoTamanho().multiplicador * precoBase;
                    return (precoBase + acrescimoBase + precoAdicionais) * itemPedido.getQuantidade();
                })
                .reduce(0.0, Double::sum);
    }

    public void adicionarItemPedido(final ItemPedido itemPedidoAdicionado){
        var shakeIndex = itens.indexOf(itemPedidoAdicionado);

        if (shakeIndex != -1) {
            var shake = itens.get(shakeIndex);
            shake.setQuantidade(itemPedidoAdicionado.getQuantidade() + shake.getQuantidade());
        }
        else
            itens.add(itemPedidoAdicionado);
    }

    public void removeItemPedido(final ItemPedido itemPedidoRemovido) throws ItemNotFound {
        var shakeIndex = itens.indexOf(itemPedidoRemovido);

        if (shakeIndex != -1) {
            var shake = itens.get(shakeIndex);
            if (shake.getQuantidade() == 1)
                itens.remove(itemPedidoRemovido);
            else
                shake.setQuantidade(shake.getQuantidade() - 1);
            return;
        }
        throw new ItemNotFound();
    }

    @Override
    public String toString() {
        return this.itens + " - " + this.cliente;
    }
}
