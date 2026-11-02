package org.example.app;

import org.example.cardapio.Lanche;
import org.example.cardapio.LancheFactory;
import org.example.pedido.GerenciadorDePedidos;
import org.example.pedido.Pedido;

import java.util.Collection;
import java.util.HashMap;

public class RestauranteFacade {
    //ATRIBUTO NECESSÁRIO
    private GerenciadorDePedidos gerenciador = new GerenciadorDePedidos();

    //METODO DE ESCOLHA PARA FUNCIONAR O SISTEMA
    private Lanche criarLanche(int opcaoDeLanche){
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

    public void adicionarLancheNoPedido(Pedido pedido, int opcaoDeLanche){
        //variavel para guardar a escolha do cliente
        Lanche lancheEscolhido = criarLanche(opcaoDeLanche);

        //adicionamos na comanda a escolha do cliente
        pedido.adicionarItemAoPedido(lancheEscolhido);
    }

    public Collection<Pedido> getPedidosRecebidosNoDia(){
        return gerenciador.getTodosOsPedidosDoFichario();
    }

    public double calcularTotalDoDia(){
        return gerenciador.calcularTotalDoDia();
    }

}
