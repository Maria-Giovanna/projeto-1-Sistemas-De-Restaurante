package org.example.app;

import org.example.cardapio.*;
import org.example.pedido.GerenciadorDePedidos;
import org.example.pedido.Pedido;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class RestauranteFacade {
    //ATRIBUTO NECESSÁRIO
    private GerenciadorDePedidos gerenciador = new GerenciadorDePedidos();

    //PEDIDO
    public Pedido criarPedido(String nomeDoCliente){
        return gerenciador.criarUmNovoPedido(nomeDoCliente);
    }
    public boolean existeUmPedido(int numeroDoPedido){return gerenciador.existeUmPedido(numeroDoPedido);}
    public Pedido buscarOPedido(int numeroDoPedido){
        return gerenciador.buscarUmPedido(numeroDoPedido);
    }
    public void cancelarOPedido(int numeroDoPedido) {
        gerenciador.cancelarUmPedido(numeroDoPedido);
    }
    public void adicionarItemNoPedido(Pedido pedido, ItemCardapio item){
        pedido.adicionarItemAoPedido(item);
    }
    public void removerItemDoPedido(Pedido pedido,int posicaoNaComanda){
        pedido.removerItemDoPedido(posicaoNaComanda);

    }


    //MONTAGEM DO ITEM;
    public Lanche criarLanche(int opcaoDeLanche){
        switch (opcaoDeLanche){
            case 1:
                return LancheFactory.xBurger();
            case 2:
                return LancheFactory.xSalada();
            case 3:
                return LancheFactory.xBacon();
            case 4:
                return LancheFactory.xBig();
            default:
                throw new IllegalArgumentException("Essa opção não existe no cardápio!\nSelecione uma das opções no menu: 1, 2, 3 ou 4");
        }
    }
    public void retirarOuDevolverIngrediente(Lanche lanche, int opcaoDeRetirarOIngrediente){
        switch (opcaoDeRetirarOIngrediente){
            case 1:
                lanche.setQueijo(!lanche.isQueijo());
                break;
            case 2:
                lanche.setAlface(!lanche.isAlface());
                break;
            case 3:
                lanche.setTomate(!lanche.isTomate());
                break;
            case 4:
                lanche.setCebola(!lanche.isCebola());
                break;
            default:
                throw new IllegalArgumentException("Esse ingrediente não existe!\nEscolha uma das opções disponivel: 1 (queijo), 2 (alface), 3 (tomate) ou 4 (cebola)");
        }
    }
    public ItemCardapio colocarAdicional(ItemCardapio item, int opcaoDeAdicional){
        switch (opcaoDeAdicional){
            case 1:
                return new ComCheddar(item);
            case 2:
                return new ComBaconExtra(item);
            default:
                throw new IllegalArgumentException("Esse adicional não existe!\nEscolha ou 1 ou 2 para adicionar ao seu pedido");
        }
    }


    //FECHAMENTO
    public Collection<Pedido> getPedidosRecebidosNoDia(){
        return gerenciador.getTodosOsPedidosDoFichario();
    }
    public double calcularTotalDoDia(){
        return gerenciador.calcularTotalDoDia();
    }
    public HashSet<String> getTodosOsLanchesDoDiaSemRepetir(){
        return gerenciador.getTodosOsLanchesDoDiaSemRepetir();
    }
}
