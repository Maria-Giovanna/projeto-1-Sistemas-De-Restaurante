package org.example.pedido;

import java.util.Collection;
import java.util.HashMap;

public class GerenciadorDePedidos {
    //ATRIBUTOS
    private HashMap<Integer,Pedido> pastaComTodosOsPedidos = new HashMap<>();
    private int contadorDePedidosNaPasta = 1;

    //METODOS
    public Pedido criarUmNovoPedido(String nomeDoCliente){
        Pedido novoPedido = new Pedido(nomeDoCliente, contadorDePedidosNaPasta);

        pastaComTodosOsPedidos.put(novoPedido.getNumeroDoPedido(), novoPedido);
        contadorDePedidosNaPasta++;

        return novoPedido;
    }

    public boolean existeUmPedido(int numeroDoPedido){
        return pastaComTodosOsPedidos.containsKey(numeroDoPedido);
    }

    public Pedido buscarUmPedido(int numeroDoPedido){
        return pastaComTodosOsPedidos.get(numeroDoPedido);
    }

    //REVISAR DESTA PARTE
    public Collection<Pedido> getTodosOsPedidosDoFichario(){
        return pastaComTodosOsPedidos.values();
    }

    public double calcularTotalDoDia(){
        double totalDoDia = 0;

        for (Pedido novoPedido : pastaComTodosOsPedidos.values()){
            totalDoDia += novoPedido.calcularOTotalDoPedido();
        }
        return totalDoDia;
    }


}
