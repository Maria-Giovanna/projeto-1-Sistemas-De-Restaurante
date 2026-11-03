package org.example.pedido;

import org.example.cardapio.ItemCardapio;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Pedido {

    //ATRIBUTOS
    private String nomeDoCliente;
    private int numeroDoPedido;
    private ArrayList<ItemCardapio> itensDoPedido;

    //CONSTRUTOR
    public Pedido(String nomeDoCliente, int numeroDoPedido) {
        setNomeDoCliente(nomeDoCliente);
        this.numeroDoPedido = numeroDoPedido;
        this.itensDoPedido = new ArrayList<>();
    }

    //SETTERS (numero do pedido e itens serão ESCOLHIDOS, n setados pela pessoa)
    public void setNomeDoCliente(String nomeDoCliente) {
        if (nomeDoCliente == null || nomeDoCliente.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente não pode ser em branco");
        }else if (!nomeDoCliente.trim().matches("[\\p{L} ]+")) {
            throw new IllegalArgumentException("O nome do cliente só pode ter letras");
        } else if (nomeDoCliente.trim().length()<3) {
            throw new IllegalArgumentException("O nome do cliente precisa ter pelo menos 3 letras");
        } else{
            this.nomeDoCliente = nomeDoCliente.trim();
        }
    }

    //GETTERS
    public String getNomeDoCliente() {
        return nomeDoCliente;
    }

    public int getNumeroDoPedido() {
        return this.numeroDoPedido;
    }

    public ArrayList<ItemCardapio> getItensDoPedido() {
        return itensDoPedido;
    }

    //METODOS
    public boolean estaVazio(){
        return itensDoPedido.isEmpty();
    }

    public void adicionarItemAoPedido(ItemCardapio itemDoCardapio) {
        itensDoPedido.add(itemDoCardapio);
    }

    public void removerItemDoPedido(int posicaoNaComanda){
        int indiceNaLista = posicaoNaComanda - 1;
        if (indiceNaLista <0 || indiceNaLista >= itensDoPedido.size()){
            throw new IllegalArgumentException("Esse item não existe na comanda");
        }
        itensDoPedido.remove(indiceNaLista);
    }

    public double calcularOTotalDoPedido() {
        double totalDoPedido = 0;

        for (int i = 0; i < itensDoPedido.size(); i++) {
            totalDoPedido += itensDoPedido.get(i).getPreco();
        }
        return totalDoPedido;
    }
}
