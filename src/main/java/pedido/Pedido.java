package pedido;

import exception.ItemNotFound;
import java.util.*;

public class Pedido{

    private int id;
    private  ArrayList<ItemPedido> itens;
    private Cliente cliente;

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

    public double calcularTotal(Cardapio cardapio) {
        return itens.stream()
                .map(itemPedido -> {
                    final var shake = itemPedido.getShake();
                    final var precoBase = cardapio.buscarPreco(shake.getBase());
                    Double precoAdicionais = 0.0;
                    if (shake.getAdicionais() != null) {
                        precoAdicionais = shake.getAdicionais().stream().map(cardapio::buscarPreco).mapToDouble(v -> v).sum();
                    }
                    final var acrescimo = shake.getTipoTamanho().multiplicador * precoBase;
                    return (precoBase + acrescimo + precoAdicionais) * itemPedido.getQuantidade();
                })
                .reduce(0.0, Double::sum);
    }

    public void adicionarItemPedido(ItemPedido itemPedidoAdicionado){
        for (ItemPedido itemPedido : itens) {
            if (itemPedido.getShake() == itemPedidoAdicionado.getShake()){
                itemPedido.setQuantidade(itemPedidoAdicionado.getQuantidade() + itemPedido.getQuantidade());
                return;
            }
            else {
                itens.add(itemPedidoAdicionado);
                return;
            }
        }
       itens.add(itemPedidoAdicionado);
    }

    public void removeItemPedido(ItemPedido itemPedidoRemovido) {
        for (ItemPedido itemPedido : itens) {
            var shakeItem = itemPedido.getShake();
            var shakeRemover = itemPedidoRemovido.getShake();
            if (shakeItem.equals(shakeRemover)){
                if (itemPedido.getQuantidade() == 1) {
                    itens.remove(itemPedidoRemovido);
                    return;
                }
                else {
                    itemPedido.setQuantidade(itemPedido.getQuantidade() - 1);
                    return;
                }
            }
        }
        throw new ItemNotFound();
    }

    @Override
    public String toString() {
        return this.itens + " - " + this.cliente;
    }
}
