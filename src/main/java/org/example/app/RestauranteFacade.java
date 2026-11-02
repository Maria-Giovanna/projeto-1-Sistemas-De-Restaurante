package org.example.app;

import org.example.cardapio.*;
import org.example.pedido.GerenciadorDePedidos;
import org.example.pedido.Pedido;
import java.util.Collection;

public class RestauranteFacade {
    //ATRIBUTO NECESSÁRIO
    private GerenciadorDePedidos gerenciador = new GerenciadorDePedidos();

    //METODO DE ESCOLHA PARA FUNCIONAR O SISTEMA
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

    //METODOS
    public Pedido criarPedido(String nomeDoCliente){
        return gerenciador.criarUmNovoPedido(nomeDoCliente);
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

    public void adicionarItemNoPedido(Pedido pedido, ItemCardapio item){
        pedido.adicionarItemAoPedido(item);
    }

    public Collection<Pedido> getPedidosRecebidosNoDia(){
        return gerenciador.getTodosOsPedidosDoFichario();
    }

    public double calcularTotalDoDia(){
        return gerenciador.calcularTotalDoDia();
    }

}
