package org.example.pedido;

import org.example.cardapio.Lanche;

import java.util.ArrayList;

public class Pedido {

    //ATRIBUTOS
    private String nomeDoCliente;
    private int numeroDoPedido;
    private ArrayList<Lanche> itensDoPedido;

    //SETTERS (numero do pedido e itens serão ESCOLHIDOS, n setados pela pessoa)
    public void setNomeDoCliente(String nomeDoCliente) {
        if (nomeDoCliente == null || nomeDoCliente.trim().isEmpty()) {
            //não pode nenhum tipo de espaço vazio
            throw new IllegalArgumentException("O nome do cliente não pode ser em branco");
        } else if (nomeDoCliente.trim().length()<3) {
            throw new IllegalArgumentException("O nome do cliente precisa ter pelo menos 3 letras");
        }else{
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

    public ArrayList<Lanche> getItensDoPedido() {
        return itensDoPedido;
    }

    //CONSTRUTOR
    public Pedido(String nomeDoCliente, int numeroDoPedido) {
        setNomeDoCliente(nomeDoCliente);
        this.numeroDoPedido = numeroDoPedido;
        this.itensDoPedido = new ArrayList<>();
    }

    //METODOS
    public void adicionarItemAoPedido(Lanche lanche) {
        itensDoPedido.add(lanche);
    }

    public double calcularOTotalDoPedido() {
        double totalDoPedido = 0;

        for (int i = 0; i < itensDoPedido.size(); i++) {
            totalDoPedido += itensDoPedido.get(i).getPreco();
        }
        return totalDoPedido;
    }
}
