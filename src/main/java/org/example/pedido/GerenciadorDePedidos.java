package org.example.pedido;

import org.example.cardapio.ItemCardapio;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class GerenciadorDePedidos {
    //ATRIBUTOS
    private HashMap<Integer, Pedido> pastaComTodosOsPedidos = new HashMap<>();
    private int contadorDePedidosNaPasta = 1;

    //METODOS
    public Pedido criarUmNovoPedido(String nomeDoCliente) {
        Pedido novoPedido = new Pedido(nomeDoCliente, contadorDePedidosNaPasta);

        pastaComTodosOsPedidos.put(novoPedido.getNumeroDoPedido(), novoPedido);
        contadorDePedidosNaPasta++;

        return novoPedido;
    }

    public boolean existeUmPedido(int numeroDoPedido) {
        return pastaComTodosOsPedidos.containsKey(numeroDoPedido);
    }

    public Pedido buscarUmPedido(int numeroDoPedido) {
        return pastaComTodosOsPedidos.get(numeroDoPedido);
    }

    public void cancelarUmPedido(int numeroDoPedido){
        pastaComTodosOsPedidos.remove(numeroDoPedido);
    }

    public HashSet<String> getTodosOsLanchesDoDiaSemRepetir(){
        HashSet<String> combinadoDosPedidosRepetidos = new HashSet<>();

        //para cada pedido dentro da pasta, busque todos os itens dentro dos itens do pedido e guarde dentro do hashset.
        for (Pedido pedido : pastaComTodosOsPedidos.values()){
            for (ItemCardapio itemCardapio : pedido.getItensDoPedido()){
                combinadoDosPedidosRepetidos.add(itemCardapio.getDescricao());
            }
        }
        return combinadoDosPedidosRepetidos;
    }

    //REVISAR DESTA PARTE
    public Collection<Pedido> getTodosOsPedidosDoFichario() {
        return pastaComTodosOsPedidos.values();
    }

    public double calcularTotalDoDia() {
        double totalDoDia = 0;

        for (Pedido novoPedido : pastaComTodosOsPedidos.values()) {
            totalDoDia += novoPedido.calcularOTotalDoPedido();
        }
        return totalDoDia;
    }


}
