package org.example.cardapio;

public class Lanche implements ItemCardapio {
    //ATRIBUTOS
    private String nomeDoLanche;
    private double precoDoLanche;

    //INGREDIENTES
    private boolean queijo = true;
    private boolean alface = true;
    private boolean tomate = true;
    private boolean cebola = true;

    //CONSTRUTOR PARA CRIAÇÃO DOS LANCHES
    public Lanche(String nomeDoLanche, double precoDoLanche){
        setNomeDoLanche(nomeDoLanche);
        setPrecoDoLanche(precoDoLanche);
    }

    //========SETS===========
    //set dos atributos
    public void setNomeDoLanche(String nomeDoLanche) {
        if (nomeDoLanche == null || nomeDoLanche.trim().isEmpty()) {
            //não pode nenhum tipo de espaço vazio
            throw new IllegalArgumentException("O nome do lanche não pode ser em branco");
        }
        this.nomeDoLanche = nomeDoLanche.trim();
    }

    public void setPrecoDoLanche(double precoDoLanche) {
        if (precoDoLanche < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo!");
        }
        this.precoDoLanche = precoDoLanche;
    }

    //set dos ingredientes
    public void setQueijo(boolean queijo) {
        this.queijo = queijo;
    }
    public void setAlface(boolean alface) {
        this.alface = alface;
    }
    public void setTomate(boolean tomate) {
        this.tomate = tomate;
    }
    public void setCebola(boolean cebola) {
        this.cebola = cebola;
    }

    //GETS

    //get dos atributos
    @Override
    public String getDescricao() {
        String descricaoDoLanche = nomeDoLanche;

        if (!queijo) {
            descricaoDoLanche += " sem queijo;";
        }
        if (!alface) {
            descricaoDoLanche += " sem alface;";
        }
        if (!tomate) {
            descricaoDoLanche += " sem tomate;";
        }
        if (!cebola) {
            descricaoDoLanche += " sem cebola;";
        }
        return descricaoDoLanche.trim();
    }

    @Override
    public double getPreco() {
        return precoDoLanche;
    }

    //get dos ingredientes
    public boolean isQueijo() {
        return queijo;
    }
    public boolean isAlface() {
        return alface;
    }
    public boolean isTomate() {
        return tomate;
    }
    public boolean isCebola() {
        return cebola;
    }
}
